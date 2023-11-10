package Leetcode2023.Trees;

/**
 * Lets talk brute force!
 * How can we gurantee that we visit all the paths? We need to be exhaustive here.
 * 
 * By visiting all the paths - This can be done by rooting ourselves at all the sub-trees.
 * We will essentially  visit all the nodes and consider each of them as the root of their sub-tree.
 * 
 * A path must start at a node and end at a node. If we root at all nodes -> This directly means
 * that we have tried all the STARTS.
 * Now for each of these nodes being considered at START, we will try out all the nodes under it
 * considering each of them to be an END. 
 * 
 * Linear amount of STARTS will have a linear amount of ENDS (Hinting towards O(n^2) clearely)
 * 
 * [1] Visit each node O(n)
 *      [1.1] Initialize a path sum search through-out the sub-tree rooted at this node. O(n)
 * [2] Bubble up the data recursively
 */
public class PathsWithSumK {
    
    /**
     * O(N^2 solution)
     * Roots a pathSum search at all possible starts and aggregated all of the results
     * to return the count of all path sums in the tree
     * 
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if(root==null) return 0;
        // Get me all the paths starting at "root" and ending at any of the nodes below it.
        // We will start a search for targetSum in the tree that is rooted at "root" and go down the tree considering
        // every node as root one by one and seeing the running sum for each of the formed subtrees.
        int rootedPathSum = subTreePathsSum(root, targetSum, 0);

        return  rootedPathSum + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }
    
    /**
     * Given a root node and a target - get the count of all the paths [not only root-leaf paths] that can make the targetSum.
     * We will see when does the running sum in a sub-tree equak the targetSum.
     * Note that the sub-tree here can be a  2 node sub-tree, 5 node sub-tree or even the full root to leaf path.
     */
    private int subTreePathsSum(TreeNode node, int targetSum, int runningSum) {
        int count = 0;
        if(node==null) return count; // I cant contribute, no path comes to be.
        
        runningSum += node.val; // Since I can contribute add me

        // If adding me lead to target -> increment count
        if(targetSum==runningSum) count++;
        
        // See what can the left and right subtrees do.
        count += subTreePathsSum(node.left, targetSum, runningSum);
        count += subTreePathsSum(node.right, targetSum, runningSum);

        return count;
    }

}
