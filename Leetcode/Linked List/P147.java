class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = new ListNode(Integer.MIN_VALUE);
        first.next = head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val > cur.next.val) {
                ListNode insert = first;
                while (insert != cur) {
                    if (cur.next.val >= insert.val && cur.next.val < insert.next.val) {
                        break;
                    }
                    insert = insert.next;
                }
                ListNode temp = cur.next.next;
                cur.next.next = insert.next;
                insert.next = cur.next;
                cur.next = temp;
            } else {
                cur = cur.next;
            }
        }
        return first.next;
    }
}
