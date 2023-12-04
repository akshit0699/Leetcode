package Leetcode2023.DynamicProgramming;

/**
 * 
 * 2214504
 * 
 * 221
 * 2,2,1
 * 22,1
 * 1,21
 * 
 * 
 * 2214
 * I know the string before me had total ways: 3
 * I can contribute to that: in 1 way or in 0 ways
 * 
 * I am a non-zero [Leads to +2]
 * 2,2,1,4
 * 22,1,4
 * 2,21,4
 * 22,14
 * 2,2,14
 * 
 * 2,2,1,4,5,0,4
 * 22,1,4,5,0,4
 * 2,21,4,5,0,4
 * 2,2,14,5,0,4
 * 2,2,1,4,50,4,
 * 
 * 
 * 
 * 
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2
 * 2 6).
 * 
 * The encoding options go from [1, 26] -> 1,2,3......25,26
 * 
 * 
 * 
 * 0 is a special case and is to be always considerd a suffix.
 * If my number statrs with a 0 then well no ways to decode it
 * 
 * // What are my base cases?
 * // 1. I know if the code is 0 -> there are 0 ways to decode it.
 * // 2. I know if the code is a single digit -> there is just 1 way to decode
 * it.
 * // 3. I know if start code with 0 -> there are 0 ways to decode it.
 * 
 * // What is my recursive case?
 * // What are my sub-problems. --> I want to know the number of ways to decode
 * every possible sub-array
 * // How are these sub-probs related --> If I know
 * 
 */
public class DecodeWays {
    public int numDecodings(String s) {
        Integer[] possibleDecodings = new Integer[s.length() + 1];
        return numDecodings(s, 0, possibleDecodings);
    }

    private int numDecodings(String s, int index, Integer[] possibleDecodings) {
        // If it is a single character, there are only 1 possibleDecodings.
        if (index == s.length()) {
            return 1;
        }

        // If it is a 0, then there is no decoding possible actually, no value addition
        // from this guy atleast as a prefix
        if (s.charAt(index) == '0') {
            return 0;
        }

        // If I have something stored in the already computed decodings, return that
        if (possibleDecodings[index] != null) {
            return possibleDecodings[index];
        }
        
        int firsWayOfDecoding = numDecodings(s, index + 1, possibleDecodings); // pick single character

        int secondWayOfDecoding = 0;
        // Check if we can still expand
        if (index < s.length() - 1) {
            String expandedString = s.substring(index, index + 2);
            int integerRepOfExpandedString = Integer.parseInt(expandedString);
            if (integerRepOfExpandedString <= 26) {
                secondWayOfDecoding = numDecodings(s, index + 2, possibleDecodings); // pick two characters
            }
        }

        possibleDecodings[index] = firsWayOfDecoding + secondWayOfDecoding;

        return possibleDecodings[index];
    }
}
