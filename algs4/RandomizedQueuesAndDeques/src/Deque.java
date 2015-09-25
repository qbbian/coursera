import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by bbian-chrome on 9/22/15.
 */
public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    private Node head, tail;
    private int size;

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Deque() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();

        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;
        head.prev = null;

        if (oldHead == null) {
            tail = head;
        } else {
            oldHead.prev = head;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();

        Node oldTail = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        tail.prev = oldTail;
        if (oldTail == null) {
            head = tail;
        } else {
            oldTail.next = tail;
        }
        size++;
    }

    public Item removeFirst() {
        if (head == null)
            throw new NoSuchElementException();

        Item item = head.item;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (tail == null)
            throw new NoSuchElementException();

        Item item = tail.item;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<String> dqString = new Deque<String>();
        System.out.println("Is empty? " + dqString.isEmpty());
        dqString.addFirst("Foo");
        System.out.println("Current size: " + dqString.size());
        dqString.addLast("Bar");
        System.out.println("Current size: " + dqString.size());
        Iterator<String> se = dqString.iterator();
        for (String s: dqString) {
            System.out.println("Is empty? " + dqString.isEmpty());
            System.out.println("Current size: " + dqString.size());
            System.out.println(s);
        }

        dqString.removeLast();
        System.out.println("Current size: " + dqString.size());
        dqString.removeFirst();
        System.out.println("Current size: " + dqString.size());
        System.out.println("Is empty? " + dqString.isEmpty());    }
}
