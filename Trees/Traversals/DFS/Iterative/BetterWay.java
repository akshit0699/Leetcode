package Leetcode2023.Trees.Traversals.DFS.Iterative;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Leetcode2023.Trees.TreeNode;

public class BetterWay {
    private List<Integer> preorderTraversal(TreeNode root) {
        // List to return - simply ArrayList.
        ArrayList<Integer> traversalResult = new ArrayList<>();
        // base case
        if (root == null)
            return traversalResult;
        // Stack to stage result
        Stack<TreeNode> processLaterStack = new Stack<>();
        processLaterStack.add(root);
        while (!processLaterStack.isEmpty()) {
            root = processLaterStack.pop();
            traversalResult.add(root.val);
            if (root.right != null)
                processLaterStack.add(root.right);
            // This should be on top in stack because L comes before R in NLR    
            if (root.left != null)
                processLaterStack.add(root.left);
        }
        return traversalResult;
    }

    private List<Integer> postorderTraversal(TreeNode root) {
        // List to return [Using a linked list coz we want to O(1) addition to front of list]
        LinkedList<Integer> traversalResult = new LinkedList<>();
        // base case
        if (root == null)
            return traversalResult;
        // Stack to explore the rest part of node later
        Stack<TreeNode> processLaterStack = new Stack<>();
        processLaterStack.add(root);
        // NLR???
        while (!processLaterStack.isEmpty()) {
            root = processLaterStack.pop();
            traversalResult.addFirst(root.val);
            // Visiting left first, because we are using a linked list insert
            if (root.left != null)
                processLaterStack.add(root.left);
            if (root.right != null)
                processLaterStack.add(root.right);
        }
        return traversalResult;
    }
}
