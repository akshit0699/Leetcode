package Leetcode2023.StringOperations;

import java.util.HashSet;

/**
 * Input: "abbcccd"
 * Output: 5
 * Explanation: The original string length is 7, but the longest palindromes are
 * {"cbcbc", "bcccb"}. 'a' and 'd' were not used.
 * 
 * 
 * There is some basic maths and pattern observation at work here.
 * It is obvious that if a lone letter it can not become a part of palindrome.
 * As and when you keep getting pairs -> You have to do a +2 to your result.
 * 
 * Start putting in a hashSet, the moment you see already existing element, remove it mark as a pair.
 * If at the end you have some letter left in the set, you can take only one of these, just take that and move fwd.
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {

        // Gives O(1) lookup hence is cool to use.
        HashSet<Character> setOfAlphabets = new HashSet<Character>();
        Integer longestPalindromeLength = 0;
        for (Character c : s.toCharArray()) {
            if (setOfAlphabets.contains(c)) {
                setOfAlphabets.remove(c);
                longestPalindromeLength += 2;
            } else {
                setOfAlphabets.add(c);
            }
        }

        if (setOfAlphabets.isEmpty()) {
            return longestPalindromeLength;
        }
        return longestPalindromeLength + 1;

    }
}
