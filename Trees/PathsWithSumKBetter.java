package Leetcode2023.Trees;

import java.util.HashMap;
import java.util.Map;

/**
 * What do we know? What is the best we can do?
 * At any point we know what is the runningSum up-untill this point
 * And we also know what sums we were able to build in the paths above us.
 * 
 * So we can probably use this knowledge and cut out some sums from the running sum
 * Example: a->c is 10. a->f is coming out to be 22. My target is 12.
 * Clearly I am 10 extra here so if i can cut off a->c i will be left with d->e->f which will be 12.
 * 
 */
public class PathsWithSumKBetter {
    public int pathSum(TreeNode root, int targetSum) {

        if(root==null) return 0;
        // Map storing the mapping of different sums to their frequencies in the tree
        Map<Long, Integer> subTreeSumFrequencies =  new HashMap<>();

        // Initialize the map saying that a sub-tree that can make a sum of 0 surely exists
        subTreeSumFrequencies.put(0L,1);

        return pathSumHelper(root, targetSum, 0L, subTreeSumFrequencies);

    }

    /**
     * 
     * @param node
     * @param targetSum
     * @param currPathSum
     * @param cache
     * @return
     */
    private int pathSumHelper(TreeNode node, int targetSum,
        long currPathSum, Map<Long, Integer> cache) {

        int count = 0;
        if(node==null) return count;

        // Holds the running sum as we travere through the tree [X]
        currPathSum += node.val;

        // If the currPathSum not hit the target
        // It needs to be modified (added/subtracted) to make it targetSum
        // The delta is distanceFromTarget, we can see if the other paths(nodes)
        // traversed in the tree can be removed such that this delta goes down to 0.
        long distanceFromTarget = currPathSum -  targetSum;


        // How many paths in this path when effectively removed can kill the distance
        // to the target node? Such that X can become equal to target
        count = cache.getOrDefault(distanceFromTarget, 0);

        // Update the cache
        // We have an additional path to make sum = X
        if(cache.containsKey(currPathSum)) {
            cache.put(currPathSum, cache.get(currPathSum) + 1);
        }
        // now we have one new path to make sum = X
        else {
            cache.put(currPathSum, 1);
        }

        // Normal DFS
        count += pathSumHelper(node.left, targetSum, currPathSum, cache);
        count += pathSumHelper(node.right, targetSum, currPathSum, cache);

        // Going back up in recusion, we need to reduce by 1 the visited path sums frequencey
        cache.put(currPathSum, cache.get(currPathSum) - 1);
        return count;
    }
}
