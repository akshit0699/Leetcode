package Leetcode2023.Trees;

public class InvertBinaryTree {
    public TreeNode invertTreeRecursiveWay(TreeNode root) {
        // if I am null or a left just return me 
        if(root==null || (root.left ==null && root.right==null)) return root;
        // staging the left-subtree to re-attach later
        TreeNode stagedLeft = root.left;
        // invert the right-subtree and make it my left
        root.left = invertTreeRecursiveWay(root.right);
        // invert the left-subtree and make it my right
        root.right = invertTreeRecursiveWay(stagedLeft);
        // return me
        return root;
    }


    public TreeNode invertTreeIterativeWay(TreeNode root) {
        

        return root;
    }
}
