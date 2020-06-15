import java.util.Stack;

public class P402 {
}

class Solution {
    public String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }
        Stack<Integer> stack = new Stack<>();
        String res = "";
        for (char c : num.toCharArray()) {
            int i = c - '0';
            while (k > 0 && !stack.empty() && i < stack.peek()) {
                stack.pop();
                k--;
            }
            if (!stack.empty() || i != 0) {
                stack.push(i);
            }
        }

        while (!stack.empty()) {
            res = stack.pop() + res;
        }
        if (res.length() == 0) return "0";
        return res.substring(0, res.length() - k);
    }
}
