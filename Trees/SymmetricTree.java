package Leetcode2023.Trees;

/**
 * SYMMETRIC AROUND CENTER
 * MIRROR OF ITSELF
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;

        // Is the right sub-tree same as the left sub-tree?
        return isSameTree(root.left, root.right);

    }
    private boolean isSameTree(TreeNode left, TreeNode right) {
            // if any of the sub-trees is null both of the sub-trees should be null, else return false
            if(left==null || right==null) return left==right;

            // they both should have the same values at root.
            if(left.val != right.val) return false;

            // There children should match outwards and inwards
            return isSameTree(left.left, right.right) && isSameTree(left.right, right.left);
    }


    public boolean isSymmetricIterativeWay(TreeNode root) {
        return true;
    }
}
