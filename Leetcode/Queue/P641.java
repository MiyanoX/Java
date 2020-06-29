class MyCircularDeque {
    private int[] a;
    private int front, last, size;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        size = k;
        a = new int[k + 1];  // have k +1 space
        front = 0;
        last = size;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        a[front] = value;
        front = plusOne(front);
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        a[last] = value;
        last = minusOne(last);
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        front = minusOne(front);
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        last = plusOne(last);
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) return -1;
        return a[minusOne(front)];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) return -1;
        return a[plusOne(last)];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        if (front - last == 1 || last - front == size) return true;
        return false;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        if (front == last) return true;
        return false;
    }

    public int plusOne(int p) {
        if (p + 1 > size) return 0;
        return p + 1;
    }

    public int minusOne(int p) {
        if (p - 1 < 0) return size;
        return p - 1;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
