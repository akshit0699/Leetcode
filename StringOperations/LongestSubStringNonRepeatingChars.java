package Leetcode2023.StringOperations;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, return the length of the longest substring of s without
 * repeating characters.
 * 
 * Input-Output
 * 
 * Example 1
 * 
 * Input: "ABCABADEC"
 * Output: 5
 * Explanation: Though there are substrings such as "AB" and "ABC" that have all
 * unique characters, "BADEC" is the longest substring w/ no character repeats.
 * 012345678
 * ABCABADEC
 * 3
 * 
 * currentMax
 * overallMax
 * 
 * I will put the character as i am looking at them in a set. If set contains it already i break the flow
 * 
 * WHAT'S THE REPEATED WORK -> We are re-looking at the array again and again!
 * 
 * Should have thought of the TWO-POINTER approach by mysefl :') -- Anyways that is the way to go here in-order to reduce the complexity.
 * 
 * We will do a couple of other questions with the 2-p approach
 * 
 */
class LongestSubStringNonRepeatingChars {
    public int lengthOfLongestSubstring(String s) {
        int overAllMax = 0;
        int start = 0;
        int end = 0;
        Set<Character> characterSet = new HashSet<>();

        while(end < s.length()) {
            // First character in the window
            Character startCharacter = s.charAt(start);
            // Last character in the window
            Character endCharacter = s.charAt(end);

            // Whoops, this is a repeating character, you can't expand further
            // You would need to move one step from left extrimity.
            if(characterSet.contains(endCharacter)) {
                characterSet.remove(startCharacter);
                // This basically is the next starting position.
                start++;
            }
            // If this is the first time I am seeing this character.
            // That's good, add it to the set and explore further i.e. end++
            else {
                characterSet.add(endCharacter);
                // This is also a good time to see if our expansion resulted in a bigger set
                overAllMax = Math.max(characterSet.size(), overAllMax);
                end++;
                
            }
        }
        
        return overAllMax;
    }
}