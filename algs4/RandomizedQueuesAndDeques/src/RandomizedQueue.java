import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by bbian-chrome on 9/23/15.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] a;

    private class RandomizedQueueIterator implements Iterator <Item> {

        private int randIndex[];
        private int current;
        public RandomizedQueueIterator() {
            current = 0;
            randIndex = new int[size];
            for (int i = 0; i < size; i++) {
                randIndex[i] = i;
            }

            for (int i = 0; i < size; i++) {
                int r = StdRandom.uniform(i + 1);
                int tmp = randIndex[i];
                randIndex[i] = randIndex[r];
                randIndex[r] = tmp;
            }
        }

        public boolean hasNext() {
            return (current != size);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            Item foo = a[randIndex[current]];
            current++;
            return foo;
        }

    }

    private void resize(int capacity) {
        assert capacity >= size;

        Item[] newQ = (Item[]) new Object[capacity];
        for (int i = 0; i < a.length; i++) {
            newQ[i] = a[i];
        }
        a = newQ;
    }
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();

        if (size == a.length) resize(2 * a.length);
        a[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int index = StdRandom.uniform(size);
        Item foo = a[index];

        for (int i = index; i < size - 1; i++) {
            a[i] = a[i+1];
        }
        a[size-1] = null;
        size--;
        return foo;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(size);

        return a[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++)
            rq.enqueue(i);

        System.out.println("Size of queue: " + rq.size());
        Iterator<Integer> it = rq.iterator();
        for (Integer i: rq) {
            System.out.println(i);
        }
    }
}
