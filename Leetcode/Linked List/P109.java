/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private List<Integer> l;

    private TreeNode listToBST(int start, int end) {
        if (start == end) return null;
        int mid = (start + end) / 2;
        TreeNode midNode = new TreeNode(this.l.get(mid));
        midNode.left = listToBST(start, mid);
        midNode.right = listToBST(mid + 1, end);
        return midNode;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        l = new ArrayList<>();
        while (head != null) {
            this.l.add(head.val);
            head = head.next;
        }

        return listToBST(0, this.l.size());

    }
}
