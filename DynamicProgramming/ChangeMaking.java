package Leetcode2023.DynamicProgramming;

import java.util.Arrays;

/**
 * 
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 * 
 * Return the fewest number of coins that you need to make up that amount. If
 * that amount of money cannot be made up by any combination of the coins,
 * return -1.
 * 
 * You may assume that you have an infinite number of each kind of coin.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * 
 * Brute-force TopDown recursion tree
 * 
 * Branching factor: COINS
 * Worst case depth: AMOUNT
 ***** O(branchingFactor^depth) ****
 * 
 * Calls/Level: Coins^level
 * Work/Level: Coins
 * For every level we will be doing work: (Calls/level) * (Work/level) =
 * Coins^(level+1) =~ Coins^Amount.
 * 
 * 
 * Exponential Solution!! How can I improve?
 * From what I can see... we are surely repeating computations for some
 * probelms!
 * Let's cache these subproblems, so that we can eliminate subtrees
 * all-togehter!
 * 
 * I will only be solving once along the depth. Solving O(amount sub-problems *
 * num of coins) =~ Coins*Amount.
 * Here also I can cut down on decision making at different points by saying
 * that hey I have this answer calculated already.
 * 
 * 
 * 
 */
public class ChangeMaking {
    // Bottom-Up DP: Know your Base cases to form--> Global subproblem [normally
    // iterative] -- "Rides up the cache"
    // Force otptimality from the bottom and reach global otpimality

    /**
     * Bottom-Up DP: Know your Base cases to form--> Global subproblem [normally
     * iterative] -- "Rides up the cache"
     * Force otptimality from the bottom and reach global otpimality
     * 
     * @param coins
     * @param amount
     * @return minimum number of coins needed to make given amound
     */
    public int coinChange_bottomUp(int[] coins, int amount) {

        // amounts array where the idex represents the face value of amount and value
        // represent minimum coins needed to make it
        int amountsList[] = new int[amount + 1];
        // Initialize the dp table, keeping at some max value which we surely will
        // reduce to the optimal minimum
        // for every sub-problem
        Arrays.fill(amountsList, amount + 1);
        // I know one base case. If I have to make AMT:0 i need COINS:0
        amountsList[0] = 0;

        // I will try to solve the smaller sub-problems
        // Asking at every point, that to make AMT:X how many coins will be needed? Up
        // untill i know for AMT:amount
        for (int amountToMake = 1; amountToMake <= amount; amountToMake++) {
            // Let's try all the coins, what is the number i can hit if I use 1st coin? 2nd
            // coin? 3rd coin?
            for (int currentCoinValue : coins) {
                // Current coin can contribute only so much, there will be some leftover amount
                // for which
                // we need to know how many coins will be needed. I will let my old
                // pre-compuations return me that.
                int lefOverAmount = amountToMake - currentCoinValue;
                // If the leftOver number is a non-negative guy only then I can consider further
                // computation.
                // If it is a 0 then obviously the result is going be to 1 + 0 = 1
                // If it is not a 0 then figure out what's the minimum effort to build that
                // amound

                // If say it is a 2, then I need to check in my amountsList array that hey for
                // making 2 how much did you
                // say is the least number of coins that we need. lets call it "C"
                // Now we either take that C, add 1 to it and that's by answer. +1 because this
                // currentCoinValue is to be included.
                // or we do not take C, maybe there was some other coin in the row before that
                // was able to bring
                // lefOverAmount to a 0, and hence we needed only 1 i.e. currentCoinValue
                if (lefOverAmount >= 0) {
                    amountsList[amountToMake] = Math.min(amountsList[amountToMake], 1 + amountsList[lefOverAmount]);
                }
            }
        }

        /*
         * dp[amount] has our answer. If we do not have an answer then dp[amount]
         * will be amount + 1 and hence dp[amount] > amount will be true. We then
         * return -1.
         * 
         * Otherwise, dp[amount] holds the answer
         */
        return amountsList[amount] > amount ? -1 : amountsList[amount];
    }

}
