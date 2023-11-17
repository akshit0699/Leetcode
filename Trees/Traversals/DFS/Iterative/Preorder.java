package Leetcode2023.Trees.Traversals.DFS.Iterative;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Leetcode2023.Trees.TreeNode;


/**
 * We perform a pre-order traversal using a stack.
 * Essentialy pre-order refers to: NLR - First process the root, then left
 * sub-tree and then finally the right sub-tree.
 * 
 * Algorithm is as follows:
 * 1. Keep going left - because DFS
 * 2. See the node: Print and put in stack to explore later.
 * 3. Once the left sub-tree is exhausted, reach out to top of stack and explore it.
 * 4. Right is the only thing left to be explored.
 * 
 */
public class Preorder {
    public List<Integer> preOrderTraversal(TreeNode root) {

        List<Integer> traversalResult = new ArrayList<>();
        Stack<TreeNode> processLaterStack = new Stack<>();

        // Either something should be to pick from stack
        // or the current node is not-null.
        while(!processLaterStack.empty() || root!=null) {
            // Process my left sub-tree
            while(root!=null) {
                // Process NODE first
                traversalResult.add(root.val);
                // Put NODE in stack to process right later
                processLaterStack.add(root);
                // Explore the left of NODE
                root =  root.left;    
            }
            
            // Get the top from stack and explore it.
            root = processLaterStack.pop();
            root = root.right;

        }

        return traversalResult;
    }
}
