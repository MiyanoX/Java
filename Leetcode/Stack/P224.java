import java.util.Stack;

public class P224 {
}

class Solution {
    private int res;
    private int sign;
    private int cur;

    public int calculate(String s) {
        res = 0;
        sign = 1;
        cur = 0;
        Stack<Integer> stack = new Stack<>();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c >= '0' && c <= '9') {
                int temp = c - '0';
                cur = cur * 10 + temp;
            } else if (c == '+' || c == '-') {
                res += sign * cur;
                sign = c == '+' ? 1 : -1;
                cur = 0;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res += sign * cur;
                int prevSign = stack.pop();
                res *= prevSign;
                res += stack.pop();
                cur = 0;
            }
        }
        return res + sign * cur;
    }
}
