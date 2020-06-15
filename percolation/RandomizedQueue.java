import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;                // number of elements on deque
    private Item[] q;            // array of Randomized Queue

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // resize the array
    private void resize(int size) {
        assert size >= n;
        Item[] temp = (Item[]) new Object[size];
        if (n >= 0) System.arraycopy(q, 0, temp, 0, n);
        q = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("can not add null");
        if (n == q.length) resize(2 * q.length);
        q[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        int a = StdRandom.uniform(n);
        Item item = q[a];
        q[a] = q[n - 1];
        q[n - 1] = null;
        n--;
        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        int a = StdRandom.uniform(n);
        return q[a];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int cur;
        private int[] RandomIndex;

        public RandomIterator() {
            cur = 0;
            RandomIndex = new int[n];
            for (int i = 0; i < n; i++) {
                RandomIndex[i] = i;
            }
            StdRandom.shuffle(RandomIndex);
        }

        public boolean hasNext() {
            return cur != n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return q[RandomIndex[cur++]];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        for (String s : q) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        for (String s : q) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        for (String s : q) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }

}
