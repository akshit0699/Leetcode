package Leetcode2023.Trees;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(q==null || p==null) return p==q;
        if(p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right,q.right);
    }
}
