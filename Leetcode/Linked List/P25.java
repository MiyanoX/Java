/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        Boolean flag = true;
        ListNode cur = head, prev = null;
        ListNode kPrev = prev;
        while (true) {
            int n = k;
            ListNode kHead = cur;
            if (!flag) break;
            while (n > 0) {
                if (cur == null) {
                    while (n != k) {
                        ListNode temp = prev.next;
                        prev.next = cur;
                        cur = prev;
                        prev = temp;
                        n++;
                    }
                    flag = false;
                    break;
                }
                ListNode temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
                n--;
            }
            if (!flag) break;
            kHead.next = cur;
            if (kPrev == null) {
                head = prev;
            } else {
                kPrev.next = prev;
            }
            kPrev = kHead;

        }
        return head;
    }
}


    // recursion
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            //剩余数量小于k的话，则不需要反转。
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(head, tail);
        //下一轮的开始的地方就是tail
        head.next = reverseKGroup(tail, k);

        return newHead;
    }

    /*
    左闭又开区间
     */
    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }
]
