import java.util.Stack;

public class test {
}

class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null) throw new IllegalArgumentException();
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            switch (s) {
                case "+": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a + b);
                    break;
                }
                case "-": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);

                    break;
                }
                case "*": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a * b);

                    break;
                }
                case "/": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);
                    break;
                }
                default:
                    stack.push(Integer.parseInt(s));
                    break;
            }
        }
        return stack.pop();
    }
}
