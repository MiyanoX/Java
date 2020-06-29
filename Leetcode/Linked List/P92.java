class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m - n == 0) return head;
        int i = 0;
        ListNode cur = new ListNode(0);
        cur.next = head;
        while (i < m - 1) {
            cur = cur.next;
            i++;
        }
        ListNode prev = null;
        ListNode prevNode = cur;
        ListNode last = cur.next;
        cur = cur.next;
        i++;
        while (i <= n) {
            // System.out.println(cur.val);
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
            i++;
        }
        prevNode.next = prev;
        last.next = cur;
        if (m == 1) return prev;  // if prevNode is not node in linked list , we start from the head of reverse list
        return head;
    }
}
