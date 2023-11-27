package Leetcode2023.StringOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given 2 lists of strings A and B, return all strings in A that are a superset
 * of the characters of every individual string in B.
 * 
 * Input-Output
 * 
 * Example 1
 * 
 * Input:
 * A = ["orange", "room", "more"]
 * B = ["rm", "oo"]
 * 
 * Output: ["room"]
 * Explanation:
 * "orange" vs. " rm" (x - has an 'r' but is missing an 'm')
 * "orange" vs. "oo" (x - has an 'o' but only 1, not 2)
 * 
 * "room" vs. "rm" (✓ - has an 'r' and 'm')
 * "room" vs. "oo" (✓ - has 2 'o's)
 * 
 * "more" vs. "rm" (✓ - has an 'r' and an 'm')
 * "more" vs. "oo" (x - only has 1 'o' and not 2)
 * 
 * "room" is the only word in A that is a superset of the characters of every
 * individual word in B.
 * 
 * 
 * Room contains every character that is mentioned as part of the words in the
 * array B (o,r,m)
 * 
 */
public class WordSubsets {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        // 1. Create a mapping of character -> frequency
        // Need to build a Universal level map only containing the highest possible
        // count across all words_
        // 2. Go through word by word.
        // 3. Check if that word satisifes all contrints of the map. if so all good.
        // 3. If not, leave out that word and move on.

        Map<Character, Integer> charToFrequencyMap = buildCharacterCountMap(words2);
        List<String> resultantArray = new ArrayList<>();
        for (String word : words1) {
            if (isSubset(word, charToFrequencyMap)) {
                resultantArray.add(word);
            }
        }
        return resultantArray;
    }

    private Map<Character, Integer> buildCharacterCountMap(String[] wordsArray) {
        Map<Character, Integer> charToFrequencyMap = new HashMap<>();
        for (String word : wordsArray) {
            Map<Character, Integer> currentWordMap = new HashMap<>();
            for (char c : word.toCharArray()) {
                currentWordMap.put(c, currentWordMap.getOrDefault(c, 0) + 1);
                int newWordFrequency = currentWordMap.get(c);
                int existingFrequency = charToFrequencyMap.getOrDefault(c, 0);
                if (newWordFrequency > existingFrequency) {
                    charToFrequencyMap.put(c, newWordFrequency);
                }
            }
        }
        return charToFrequencyMap;
    }

    private boolean isSubset(String word, Map<Character, Integer> targetCharToFrequencyMap) {
        Map<Character, Integer> wordCharToFreqMap = new HashMap<>();

        for (Character c : word.toCharArray()) {
            wordCharToFreqMap.put(c, wordCharToFreqMap.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : targetCharToFrequencyMap.entrySet()) {
            Character charToCheck = entry.getKey();
            if (!wordCharToFreqMap.containsKey(charToCheck))
                return false;

            int targettedFrequency = entry.getValue();
            int wordFrequency = wordCharToFreqMap.get(charToCheck);

            if (wordFrequency < targettedFrequency)
                return false;
        }
        return true;
    }
}
