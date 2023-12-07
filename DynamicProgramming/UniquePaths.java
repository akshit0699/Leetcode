package Leetcode2023.DynamicProgramming;

/**
 * m x n grid.
 * Initially located at the top-left corner (i.e., grid[0][0]).
 * Need to reach bottom-right corner (i.e., grid[m - 1][n - 1]).
 * 
 * Can only move either down or right at any point in time.
 * 
 * Given the two integers m and n, return the number of possible unique paths
 * that we can take to reach the bottom-right corner.
 * 
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach
 * the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 */
public class UniquePaths {

    // DP Solution
    public int uniquePaths(int m, int n) {
        int memoziedAnswer = uniquePathsMemoized(m, n);
        int recursiveAnswer = uniquePathsRecursive(m, n);

        int uniquePathsForCoords[][] = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            uniquePathsForCoords[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            uniquePathsForCoords[0][i] = 1;
        }

        for (int row = 1; row < m; row++) {
            for (int column = 1; column < n; column++) {
                uniquePathsForCoords[row][column] = uniquePathsForCoords[row][column - 1]
                        + uniquePathsForCoords[row - 1][column];
            }
        }
        return uniquePathsForCoords[m - 1][n - 1];
    }

    // This gives TLE but important to understand how to approach the problem
    private int uniquePathsRecursive(int m, int n) {
        return recursvieUniquePathsHelper(m - 1, n - 1);
    }

    // This is to prevent duplicate word by using a memo to store results
    private int uniquePathsMemoized(int m, int n) {
        return memoizedUniquePathsHelper(m - 1, n - 1, new int[n][m]);
    }

    private int recursvieUniquePathsHelper(int m, int n) {
        // Base cases
        if (m < 0 || n < 0) {
            return 0;
        } else if (m == 0 || n == 0) {
            return 1;
        // Just get me the unique paths for the 2 boxes from where you can reach me
        // i.e. just above me and just to my left - there some is my answer.
        } else {
            return recursvieUniquePathsHelper(m - 1, n) + recursvieUniquePathsHelper(m, n - 1);
        }
    }

    private int memoizedUniquePathsHelper(int m, int n, int[][] memo) {
        if (m < 0 || n < 0) {
            return 0;
        } else if (m == 0 || n == 0) {
            return 1;

        // Returning the answer if already computed    
        } else if (memo[n][m] > 0) {
            return memo[n][m];
        } else {
            memo[n][m] = memoizedUniquePathsHelper(m - 1, n, memo) + memoizedUniquePathsHelper(m, n - 1, memo);
            return memo[n][m];
        }
    }

}
