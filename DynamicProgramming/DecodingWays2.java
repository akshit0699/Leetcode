package Leetcode2023.DynamicProgramming;

public class DecodingWays2 {
    public int numDecodings(String s) {
        Integer[] possibleDecodings = new Integer[s.length() + 1];
        return numDecodings(s, 0, possibleDecodings);
    }

    // We will think of the recursive tree at all points.
    // The decode pointer will point to the new plce from where we can possibly try
    // a tree split
    private int numDecodings(String s, int index, Integer[] possibleDecodings) {

        // If we have reached the end of the string
        // or we are checking for an empty string, that is a possibleDecding surely
        if (index >= s.length())
            return 1;

        // If we have the result cached already simply return that.
        if (possibleDecodings[index] != null)
            return possibleDecodings[index];

        int totalPossibleDecodings = 0;

        // For every run through the string considering from different points of split
        // (index)
        // We have at max option to see ourselves and the number just to the right of
        // us.
        for (int suffix = 1; suffix <= 2; suffix++) {
            // Basically saying just the lone guy at [index] -- when suffix=1
            // Basically saying just the lone guy at [index] + 1 to the right to it -- when suffix=2
            if (index + suffix < s.length()) {
                String snippedString = s.substring(index, index + suffix);
                if (isValid(snippedString)) {
                    // Cool the snippedString is valid, now find me how many decoding are possible
                    // for
                    // the reaming string
                    totalPossibleDecodings += numDecodings(s, index + suffix, possibleDecodings);
                }
            }
        }

        // Record subproblem answer to decompositions from (decodePointer)...(s.length - 1)
        possibleDecodings[index] = totalPossibleDecodings;

        return possibleDecodings[index];
    }

    private boolean isValid(String snippedString) {
        // Should not start be a 0, should not be a zero
        // Should be between 0 and 26 in integer representation
        if (snippedString.length() == 0 || snippedString.charAt(0) == '0') {
            return false;
          }
      
          int value = Integer.parseInt(snippedString);
      
          return value >= 1 && value <= 26;
    }
}
