package Leetcode2023.StringOperations;

import java.util.HashMap;
import java.util.Map;

class LongestPalindrome_2 {
    public int longestPalindrome(String s) {
        // It boils down to the count of the characters.
        // If there are 5 character occurence. Use 4 out of that.
        // If there are 4 occurences. Use all i.e. 4 of those.
        // If there is just 1 occurence - set a flag to true and use it for a +1.

        // A flag to denote if we do a +1 or not.
        boolean hasOddGuy = false;
        Integer longestPalindromeLength = 0;

        // Get a mapping from character to its count
        Map<Character, Integer> characterCountMap = buildCharacterCountMap(s);

        // Iterate over this map
        for (Map.Entry<Character, Integer> entry : characterCountMap.entrySet()) {
            Integer count = entry.getValue();
            if (count % 2 != 0) {
                hasOddGuy = true;
                if (count != 1) {
                    longestPalindromeLength += (count - 1);
                }
            } else {
                longestPalindromeLength += count;
            }
        }

        if (hasOddGuy) {
            return longestPalindromeLength + 1;
        }
        return longestPalindromeLength;

    }

    public Map<Character, Integer> buildCharacterCountMap(String s) {
        Map<Character, Integer> countMap = new HashMap<>();

        for (char c : s.toCharArray()) {
            if (countMap.containsKey(c)) {
                countMap.put(c, countMap.get(c) + 1);
            } else {
                countMap.put(c, 1);
            }
        }

        return countMap;
    }
}
