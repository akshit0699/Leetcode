package Leetcode2023.StringOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Input: ["eat", "bat", "ate", "tab", "tea", "eat"]
 * Output:
 * [
 * ["eat", "ate", "tea", "eat"],
 * ["bat", "tab"]
 * ]
 * Explanation:
 * 1.) "eat", "ate", "tea", "eat" are all anagrams w/ respect to each other
 * 2.) "bat", "tab" are all anagrams as well
 * 
 * 
 * Represent every word by a number.
 * Add all the numbers for a word.
 * we will have an integer array at hand.
 * group together the same numbers. (maybe sort and break or just start
 * braeaking up to us.)
 */
class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> anamgramGroups = new ArrayList<>(new ArrayList<>());
        Map<String, List<String>> wordsGropuedByIntegerRepresentation = new HashMap<>();

        for (String word : strs) {
            String integerRepresentation = getIntegerRepresentation(word);
            List<String> existingWordsInIntegerGroup = wordsGropuedByIntegerRepresentation.getOrDefault(
                integerRepresentation, new ArrayList<>());
            existingWordsInIntegerGroup.add(word);
            wordsGropuedByIntegerRepresentation.put(integerRepresentation, existingWordsInIntegerGroup);
        }

        // Go through the map create a seperate list for every integerVersion and append
        // to a parent list and return that.
        for (Map.Entry<String, List<String>> entry : wordsGropuedByIntegerRepresentation.entrySet()) {
            anamgramGroups.add(entry.getValue());
        }

        return anamgramGroups;
    }

    // SUM CAN BE SIMILAR BECAUSE OF DIFFERENT REASONS.
    // THE PATTERN DOES NOT NEED TO BE THE EXACT SAME cat and tca will have seperate patterns but might not match up.
    // What if I sort the string/array after this?
    // Even better -> MAKE USE OF THE INDEXES OF AN ARRAY!
    private String getIntegerRepresentation(String word) {
        char[] integerRep = new char[26];
        for (Character c : word.toCharArray()) {
            // Need to map 'a' to integer 1, 'd' to integer value 4.... 'z' to 26
            int numericPlace = c - 'a';
            // We will keep a count of the occurences because we want to match that
            integerRep[numericPlace]++;
        }
        // Converting character array to string
        return new String(integerRep);
    }
}