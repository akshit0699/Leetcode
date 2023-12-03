package Leetcode2023.DynamicProgramming;

import java.util.Arrays;

/**
 * 
 * // N steps to reach the top
 * // distinct ways in which I can reach top.
 * // if 1 step -> 1 way
 * // if 0 steps -> 1 way
 * // if 2 steps -> I have two options either I take 1+1 or 2.
 * // For every iteration this is how I can express my deciison space.
 * 
 * // 4
 * // 3 2
 * // 2 1 1 0
 * // 1 0
 * 
 * // [0 1 2 3 4]
 * // []
 * 
 * DP Solution is obvious: ways[i] = ways[i-1] + ways[i-2] - just look at the 2 guys behind me.
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        int[] waysToClimbStairs = new int[n + 1];
        Arrays.fill(waysToClimbStairs, -1);
        return getDistinctWays(n, waysToClimbStairs);

    }

    private int getDistinctWays(int n, int[] dp) {
        // There is just 1 way if there are no steps or 0 steps.
        if (n == 0 || n == 1)
            return 1;

        // Return the result if already cached. 
        if (dp[n] > -1)
            return dp[n];

        // Either take 2 steps
        int stepsNeedIfTwoStepsClimbed = getDistinctWays(n - 2, dp);
        // or take just one step
        int stepsNeedIfOneStepClimbed = getDistinctWays(n - 1, dp);

        // Caching for using later
        dp[n] = stepsNeedIfTwoStepsClimbed + stepsNeedIfOneStepClimbed;

        // At all time returning the answer computed for the current recursion level
        return dp[n];
    }
}
