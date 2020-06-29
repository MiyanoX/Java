import java.util.Stack;

public class P232 {
}

class MyQueue {
    private Stack<Integer> stack;
    private Stack<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stack = new Stack<>();
        queue = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        while (!queue.empty()) {
            stack.push(queue.pop());
        }
        stack.push(x);
        while (!stack.empty()) {
            queue.push(stack.pop());
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        return queue.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return queue.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return queue.empty();
    }
}
