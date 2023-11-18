package Leetcode2023.Trees.Traversals.DFS.Iterative;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Leetcode2023.Trees.TreeNode;

/**
 * LRN. I have to first explore the left sub-tree
 * then explore the right sub-tree
 * once i am sure that this has been done only then i move to the node itself
 */
public class Postorder {
    public List<Integer> postOrderTraversal(TreeNode root) {

        List<Integer> traversalResult = new ArrayList<>();
        Stack<TreeNode> processLaterStack = new Stack<>();

        while(!processLaterStack.isEmpty() ||root==null) {
            // I keep going left and keep putting into stack
            if(root!=null) {
                processLaterStack.add(root);
                root = root.left;
            }
            // Once I see that i have left a node that has no left child
            else {
                // I can now explore the right of the top of stack but note that,
                // we need to look at it later again to explore the node
                TreeNode nodeToProcess = processLaterStack.peek().right;

                // Right child present? Explore it.
                if(nodeToProcess!=null) {
                    root = nodeToProcess;
                }
                // No right child present? (also we now know there is no left child)
                else {
                    // then get the next node from the stack
                    // pop and print
                    nodeToProcess = processLaterStack.pop();
                    traversalResult.add(nodeToProcess.val);
                    // Is this node the right node of the current node on top of stack?
                    // That simply means the top of the stack should be explored first.
                    while(!processLaterStack.isEmpty() ||
                        nodeToProcess == processLaterStack.peek().right) {
                            // then pop and print
                            nodeToProcess = processLaterStack.pop();
                            traversalResult.add(nodeToProcess.val);
                        }
                }
            }
        }

        return traversalResult;
    }
}