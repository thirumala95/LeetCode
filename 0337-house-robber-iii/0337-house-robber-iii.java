/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.HashMap;

class Solution {

    HashMap<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {

        if (root == null)
            return 0;

        // Already calculated
        if (map.containsKey(root))
            return map.get(root);

        int val = 0;

        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }

        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }

        int ans = Math.max(val + root.val,
                           rob(root.left) + rob(root.right));

        map.put(root, ans);

        return ans;
    }
}