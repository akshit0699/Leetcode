package Leetcode2023.DynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can
 * be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * 
 * What is the best I can do?
 * I will have to go through the string letter by letter.
 * and all times keep checking the wordDict. [Maybe i will use some data
 * structure to query it fast]
 * 
 * Lets try greedy.
 * I will start from first place.. and try to form the longest word. O(n) ->
 * Longest first word.
 * Then I will try to iterate the remaining word O(n - first_word_len)
 * and keep going expecting that I find the last word in the end.
 * 
 * now it might be a case that the dictionary is ["le", "leet", "etcode"]
 * -> I was greedy and i chose -> "leet"
 * -> Now I can not even find code. and i return false...
 * Even though it was possible to return "le"+"etcode".
 * 
 * leetcode
 * 
 * I will start to go through the string. When I find the first match I will ask
 * my recursive function to
 * tell me hey can you find all the remaining string words from the dictionary?
 * (which will again follow the same pattern, recursively)
 * -> If yes, the return a global yes
 * -> If no, move ahead and try to find the next possible match.
 * 
 * What are my base cases?
 * - If the string length is 0 return true.
 * What's the duplicate work that I am doing?
 * - looking up the dictionary? Cant change that
 * - A subsection of word... if it is maybe repeating then we can know in
 * advance that this can be formed from the dictinoary
 * 
 * So normal recursive would be: |dictionary|^len(string)
 * If we can do some sort of memoization and cut down the extra trees - We can
 * reach to: O(len(s)*|dictionary|)
 * 
 * Lets break it down in the sub-problems and see how the sub-problems relate.
 * 
 * Question to ask: Can we extend a successfull decomposition??
 * If the decomp was successful for an index - extend from there considering
 * different points in the array
 * to be the end and then see if we can extend to them.
 * 
 * Can we decompose out from "a"
 * 
 * app
 * Are we decompasable from a? -> if yes then that means we just have to
 * decompose "pp". but no we are not.
 * Are we decomposable from ap? -> Yes - That means we just have to decompose
 * "p", now Does the dictionary have p to complete the decomposition
 * ending here? No.
 * Does the dictionary have "app" itself? -> No. So we can say: For the
 * substring ending at this index we can not do a success decomp.
 * 
 * 
 * appl
 * 
 * Extend off "" -> Does the dictionary have "app" itself? -> No. So we can say:
 * For the substring ending at this index we can not do a success decomp.
 * Are we decompasable from a? -> if yes then that means we just have to
 * decompose "ppl". but no we are not.
 * Are we decomposable from ap? -> Yes - That means we just have to decompose
 * "pl", now Does the dictionary have p to complete the decomposition
 * ending here? No.
 * Are we decomposable from app? -> No.
 * 
 * apple
 * Can we extend off ""
 * -> If Yes - Does the dictionary have remainder string i.e. apple?
 * -> If No - skip this and move to next (if any)
 * 
 * Does the dictionary have remainder string?
 * -> If Yes - Short circut and set as true
 * -> If No - skip this and move to next (if any)
 * 
 * This gices O(n^2)
 * aaaaaaa
 * ["aaaa","aaa"]
 */
class WordBreak {

    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<>();
        myList.add("ap");
        myList.add("ple");
        boolean answer = wordBreak("apple", myList);
        System.out.println(answer);
    }

    private static boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0)
            return true;
        HashSet<String> dictWords = new HashSet<>();
        wordDict.forEach(w -> dictWords.add(w));
        return isDecomposibleWithDPApproach(s, wordDict);


        // int[] memo = new int[s.length()];
        // Arrays.fill(memo, -1);
        // return isDecomposibleRecursionWithMemo(s, dictWords, 0, memo);
        // return isDecomposibleRecursiveApproach(s, dictWords, 0);
        // return isDecomposibleRecursiveApproach2(s, dictWords);

    }

    private static boolean isDecomposibleWithDPApproach(String s, List<String> wordDict) {
        int stringLength = s.length();
        boolean[] isDecomposible = new boolean[stringLength+1];
        isDecomposible[0] = true;

        //apple=5
        // 1 0 0 0 0 0
        // 0 1 2 3 4 5
        // Try all the positions in string

        // I will break the problem "apple" into smaller sub-problems
        // Follow a top-down dp formula to have an answer for smaller strings and extend that to full string
        for(int endPos=1;endPos<=stringLength;endPos++) {
                // Attempt at extending from all positions behind me
                for(int startPos=0; startPos<endPos; startPos++) {
                    // Considering this to be the start, see if we can extend
                    // we take small substrings of the word
                    String currentWord = s.substring(startPos, endPos);
                    // we can extend only if everything behind me is decompsible and the remaining string is in dictionary
                    if(isDecomposible[startPos] && wordDict.contains(currentWord)) {
                        isDecomposible[endPos] = true;
                        break;
                    } 
            }
        }

        return isDecomposible[stringLength];
    }

    private static boolean isDecomposibleRecursionWithMemo(String s, HashSet<String> dictWords,
            int startIdx, int[] memo) {
        int stringLength = s.length();
        // If I have reached the end of string then it is decomposible for sure.
        if (stringLength == startIdx)
            return true;

        if (memo[startIdx] != -1)
            return true;

        for (int i = startIdx; i < stringLength; i++) {
            String currentSubstring = s.substring(startIdx, i + 1);
            // I found this substring, if everything just ahead of me decomposible too?
            if (dictWords.contains(currentSubstring) && isDecomposibleRecursiveApproach(s, dictWords, i + 1)) {
                memo[startIdx] = 1;
                return true;
            }
        }

        return false;
    }

    private static boolean isDecomposibleRecursiveApproach(String s, HashSet<String> dictWords, int startIdx) {
        int stringLength = s.length();
        // If I have reached the end of string then it is decomposible for sure.
        if (stringLength == startIdx)
            return true;

        for (int i = startIdx; i < stringLength; i++) {
            String currentSubstring = s.substring(startIdx, i + 1);
            // I found this substring, if everything just ahead of me decomposible too?
            if (dictWords.contains(currentSubstring) && isDecomposibleRecursiveApproach(s, dictWords, i + 1)) {
                return true;
            }
        }

        return false;
    }

    // This is a greedy approach where are assuming the first match to work for us
    private static boolean isDecomposibleRecursiveApproach2(String s, HashSet<String> dictWords) {
        int stringLength = s.length();
        if (stringLength == 0)
            return true;
        for (int i = 0; i < stringLength; i++) {
            String currentSubstring = s.substring(0, i + 1);
            if (dictWords.contains(currentSubstring)
                    && isDecomposibleRecursiveApproach2(s.substring(i + 1, stringLength), dictWords)) {
                return true;
            }
        }

        return false;
    }
}