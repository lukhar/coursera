import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INITIAL_SIZE = 2;
    private Object[] queue;
    private int first;
    private int last;
    private int size;

    public RandomizedQueue() {
        this.queue = new Object[INITIAL_SIZE];
        this.first = 0;
        this.last = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        enlarge();

        queue[++last] = item;
        size++;
    }

    private void enlarge() {
        if (size < queue.length && last < queue.length - 1) {
            return;
        }

        Object[] newQueue = new Object[queue.length * 2];

        int j = 0;
        for (int i = first; i <= last; i++) {
            if (queue[i] != null) {
                newQueue[j] = queue[i];
                j++;
            }
        }
        first = 0;
        last = j - 1;
        queue = newQueue;
    }

    private void shrink() {
        float ratio = (float) size / queue.length;
        if (ratio < 0.25 && ratio > 0) {
            Object[] newQueue = new Object[queue.length / 2];

            int j = 0;
            for (int i = first; i <= last; i++) {
                if (queue[i] != null) {
                    newQueue[j] = queue[i];
                    j++;
                }
            }
            first = 0;
            last = j - 1;
            queue = newQueue;
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        shrink();

        int randomIndex;
        do {
            randomIndex = StdRandom.uniform(first, last + 1);
        } while (queue[randomIndex] == null);

        Item item = (Item) queue[randomIndex];
        queue[randomIndex] = null;

        if (randomIndex == last && last > -1) {
            while (last > -1 && queue[last] == null) {
                last--;
            }
        }
        size--;

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int uniform;
        do {
            uniform = StdRandom.uniform(first, last + 1);
        } while (queue[uniform] == null);

        return (Item) queue[uniform];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator<>(first, last, size);
    }


    private class RandomIterator<Item> implements Iterator<Item> {

        private final int[] shuffled;
        private int current;

        public RandomIterator(int first, int last, int size) {
            int[] indexes = new int[size];
            int i = 0;
            for (int index = first; index <= last; index++) {
                if (queue[index] != null) {
                    indexes[i] = index;
                    i++;
                }
            }
            StdRandom.shuffle(indexes);
            this.shuffled = indexes;
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < shuffled.length;
        }

        @Override
        public Item next() {
            if (current >= shuffled.length) {
                throw new NoSuchElementException();
            }

            if (shuffled[current] >= queue.length || shuffled[current] < 0) {
                System.out.println("buya!");
            }

            while (queue[shuffled[current]] == null) {
                current += 1;
            }

            Item item = (Item) queue[shuffled[current]];
            current += 1;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

    }
}

