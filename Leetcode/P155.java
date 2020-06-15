import java.util.Stack;

class MinStack {
    private Stack<Integer> stack;
    private int minOfStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minOfStack = Integer.MAX_VALUE;
    }

    public void push(int x) {
        stack.push(x);
        if (x < minOfStack) minOfStack = x;
    }

    public void pop() {
        if (stack.pop() == minOfStack) reCalMin();
    }

    public int top() {
        return stack.peek();
    }

    private void reCalMin() {
        minOfStack = Integer.MAX_VALUE;
        for (int a : stack) {
            if (a < minOfStack) {
                minOfStack = a;
            }
        }
    }

    public int getMin() {
        return minOfStack;
    }
}
