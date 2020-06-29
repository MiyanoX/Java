import java.util.Stack;

class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l1.val == 0) return l2;
        if (l2 == null || l2.val == 0) return l1;

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int prev = 0;
        ListNode head = null;
        while (!(s1.empty() && s2.empty()) || prev != 0) {
            int a, b;
            if (s1.empty()) {
                a = 0;
            } else {
                a = s1.pop();
            }
            if (s2.empty()) {
                b = 0;
            } else {
                b = s2.pop();
            }
            int res = a + b + prev;
            prev = res / 10;
            res = res % 10;
            ListNode cur = new ListNode(res);
            cur.next = head;
            head = cur;
        }

        return head;
    }
}
