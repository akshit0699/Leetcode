package Leetcode2023.Trees.Traversals.DFS.Iterative;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Leetcode2023.Trees.TreeNode;

public class Inorder {
        public List<Integer> inOrderTraversal(TreeNode root) {

        List<Integer> traversalResult = new ArrayList<>();
        Stack<TreeNode> nodesStack = new Stack<>();

        // Either something should be to pick from stack
        // or the current node is not-null.
        while (!nodesStack.empty() || root != null) {
            // Process my left sub-tree first
            while (root != null) {
                // Put NODE in stack to process right later
                nodesStack.add(root);
                // Explore the left of NODE
                root = root.left;
            }

            // Get the top from stack to explore it further
            root = nodesStack.pop();
            // Process NODE before moving to he right
            traversalResult.add(root.val);
            // Now explore the right
            root = root.right;

        }

        return traversalResult;
    }
}
