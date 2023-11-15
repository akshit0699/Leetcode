package Leetcode2023.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathSum  {
    public int pathSumCalc(TreeNode root, int targetSum) {
        int pathsCount = 0;
        if(root==null) return pathsCount;

        List<Integer> path = new ArrayList<>(Arrays.asList(root.val));

        // Base-case can be ended here.
        if(isLeafNode(root) && root.val==targetSum) {
            return ++pathsCount;
        }

        // Get the possible paths from left and right sub-trees
        if(root.left!=null) pathSumHelper(root.left, targetSum-root.val, pathsCount);
        if(root.right!=null) pathSumHelper(root.right, targetSum-root.val, pathsCount);

        return pathsCount;

    }

    private void pathSumHelper(TreeNode node, int sum,
        int pathsCount) {
            if(node==null) return;

            // Base case  
            if(isLeafNode(node) && node.val==sum) {
                ++pathsCount;
            } 

            // Recursive call
            pathSumHelper(node.left, sum-node.val, pathsCount);
            pathSumHelper(node.right, sum-node.val, pathsCount);
            
            // Backtrack the path at all times [Happy/Unhappy cases.]
            // NEVER FORGET TO BACKTRACK
  
            
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right==null;
    }
}
