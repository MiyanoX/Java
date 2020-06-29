class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHead = slow.next;
        slow.next = null;
        ListNode prev = null;
        ListNode next;
        while (secondHead.next != null) {
            next = secondHead.next;
            secondHead.next = prev;
            prev = secondHead;
            secondHead = next;
        }
        secondHead.next = prev;
        // System.out.println(secondHead.val);
        ListNode res = new ListNode();
        while (secondHead != null) {
            res.next = head;
            head = head.next;
            res = res.next;

            res.next = secondHead;
            secondHead = secondHead.next;
            res = res.next;


            System.out.println(res.val);
            System.out.println(" ");
        }
        if (head != null) res.next = head;
        return;
    }
}


    // using recursion to reverse the linked list
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

]
