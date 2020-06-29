import java.util.HashMap;

public class Solution { ]

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        HashMap<ListNode, Integer> h = new HashMap<>();
        ListNode cur = head;
        while (cur != null) {
            if (!h.containsKey(cur)) {
                h.put(cur, 1);
            } else {
                return cur;
            }
            cur = cur.next;
        }
        return null;

    }
}
