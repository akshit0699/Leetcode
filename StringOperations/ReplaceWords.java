package Leetcode2023.StringOperations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *  INITIAL THOUGHTS -> What is the best that we can do for this scenario?
 *  TWO LINEAR DATA STRUCTURES -> Cache some results so that maybe I can have result in linear time?
 *  CAN I REDUCE MY WORK BY DOING A CONSTANT TIME LOOKUP? -> This would help me preventing multiple array iterations.
 * 
 *  PRE-COMPUTATIONS are useful. PRE-LOADING -> Set/HashTable.
 * 
 *  Note: There is also a trie based implementation for this, we will get to it in a bit, for now this is good.
 * 
 */
class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        // dictionary = ["cat","bat","rat"]
        //   sentence = "the cattle was rattled by the battery"

        // Output: "the cat was rat by the bat"


        // cat -> []
        // bat -> []
        // rat -> [] 


        /*
        prefixes = ["cat", "catch", "yell"]
        sentence = "The cats were catching yarn"

        // Sort words in root array by length?
        // Take one root at a time -> See its length.
        // Go through ever word in sentence.
        // Trim every word down to the length.
        // If root==trimmed_word -> Put it in the map   
        Output: "The cat were cat yarn"

        // Another approach

        /**
        1. Create a hashSet of all the words in the dictionary for O(1) access.
        2. Pick every word in the sentence (Conver it into an array)
        2.1 For each word start going character by character - doing a O(1) lookup.
        2.2 If the character group is found in set replace it in the array (remember to break the moment a match is found)
        2.3 if not found just ignore it    
         */

        // HashSet insert to get a O(1) lookup
        Set <String> dictWords = new HashSet<>();
        for(String word: dictionary) {
            dictWords.add(word);
        } 

        // Get the list of words in sentence
        String[] sentenceWords = sentence.split(" ");

        for(int sentenceIdx = 0; sentenceIdx<sentenceWords.length; sentenceIdx++) {
            String word = sentenceWords[sentenceIdx];
            int length = word.length();
            for(int i=0; i<length; i++) {
                String partialWord = word.substring(0, i+1);
                if(dictWords.contains(partialWord)) {
                    sentenceWords[sentenceIdx] = partialWord;  
                    break;   
                }
            }
        }

        return String.join(" ", sentenceWords);


    }
}