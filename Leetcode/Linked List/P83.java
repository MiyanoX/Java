class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        // if(head != null) return null;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                ListNode temp = cur.next;
                while (temp.next != null && temp.next.val == cur.val) {
                    temp = temp.next;
                }
                cur.next = temp.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
