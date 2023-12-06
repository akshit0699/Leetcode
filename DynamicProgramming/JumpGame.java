package Leetcode2023.DynamicProgramming;

/**
 * You are given an integer array nums. You are initially positioned at the
 * array's first index, and each element in the array represents your maximum
 * jump length at that position.
 * 
 * Return true if you can reach the last index, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * [0,1,2,3,4]
 * 
 * [Y,Y,Y,Y,Y]
 * [0,1,2,3,4]
 * Greedy would be to take as many jumps as possible wherever you are. Which
 * obviosuly will not always work out for us.
 * 
 * We need to know at every posititon which jump can
 * We need to find the optimal solution.
 * 
 * Variant: Figure out the minimum number of jumps needed to reach
 * 
 * What is the obvious maximum work that I need to do?
 * Universal question: What is the minimum jumps needed to reach string index starting from index = 0?
 * 
 * Let's sub-problem it down and try to fomulate base cases:
 * --> Min jumps needed to reach end starting from idx= len-1 (last)
 * = 0 THIS IS KNOWLEDGE! Relate the sub-problems to kind of bubble this knowledge up the ladder.
 * 
 * Hence my recurence relation becomes:
 * --> Min jumps needed to reach end starting from
 * =1 + minminimOf(minJumps(i+s)) [Where s belongs to number of jumps i can take starting from 9 upto jumps[i]]
 * This is minViaIndex
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] canBeReached = new boolean[n];
        canBeReached[0] = true;
        int leftPost = 0;

        for (int rightPos = 0; rightPos < n; rightPos++) {
        }

        return false;
    }
}
