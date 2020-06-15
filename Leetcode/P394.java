import java.util.Stack;

public class P394 {
}

class Solution {
    public String decodeString(String s) {
        int multi = 0;
        StringBuilder cur = new StringBuilder();
        Stack<String> stack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                multi = multi * 10 + (c - '0');
            } else if (c == '[') {
                stack.push(cur.toString());
                cur.setLength(0);
                intStack.push(multi);
                multi = 0;
            } else if (c == ']') {
                String temp = cur.toString();
                cur.setLength(0);
                cur.append(stack.pop());
                for (int i = intStack.pop(); i > 0; i--) {
                    cur.append(temp);
                }
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }
}
