package proj;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int n; // number of element
    private Node<Item> head; // sentinel node in the beginning
    private Node<Item> tail; // sentinel node in the end

    //linked list class
    private static class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;
    }

    // construct an empty deque
    public Deque() {
        n = 0;
        head = new Node<Item>();
        tail = new Node<Item>();

        head.item = null;
        head.prev = null;
        head.next = tail;

        tail.item = null;
        tail.prev = head;
        tail.next = null;
    }

    // is the deque empty?
    public boolean isEmpty() {

    }

    // return the number of items on the deque
    public int size() {

    }

    // add the item to the front
    public void addFirst(Item item) {

    }

    // add the item to the back
    public void addLast(Item item) {
    }


    // remove and return the item from the front
    public Item removeFirst() {

    }

    // remove and return the item from the back
    public Item removeLast() {

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
    }


    // unit testing (required)
    public static void main(String[] args) {

    }

}
