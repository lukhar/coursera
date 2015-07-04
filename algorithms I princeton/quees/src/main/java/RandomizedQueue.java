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

        int uniform;
        do {
            uniform = StdRandom.uniform(first, last + 1);
        } while (queue[uniform] == null);

        Item item = (Item) queue[uniform];
        queue[uniform] = null;

        if (uniform == last && last > -1) {
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
        Object[] items = new Object[size];
        int j = 0;
        for (int i = first; i <= last; i++) {
            if (queue[i] != null) {
                items[j] = queue[i];
                j++;
            }
        }

        return new RandomIterator<>(items);
    }


    private class RandomIterator<Item> implements Iterator<Item> {

        private final Object[] items;
        private final int first;
        private int last;

        private RandomIterator(Object[] items) {
            this.items = items;
            this.first = 0;
            this.last = items.length - 1;
        }

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int uniform;
            do {
                uniform = StdRandom.uniform(first, last + 1);
            } while (items[uniform] == null);

            Item item = (Item) items[uniform];
            items[uniform] = null;

            if (uniform == last && last > -1) {
                while (last > -1 && items[last] == null) {
                    last--;
                }
            }

            size--;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        int numberOfTrials = 1000;
        RandomizedQueue<Integer> integers = new RandomizedQueue<Integer>();

        // when
        for (int i = 0; i < numberOfTrials; i++) {
            int uniform = StdRandom.uniform(0, 3);

            switch (uniform) {
                case 0:
                    integers.enqueue(StdRandom.uniform(5, 15));
                    StdOut.println("operation: " + uniform + " iteration: " + i);
                    break;
                case 1:
                    if (!integers.isEmpty()) {
                        integers.dequeue();
                        StdOut.println("operation: " + uniform + " iteration: " + i);
                    }
                    break;
                case 2:
                    if (!integers.isEmpty()) {
                        integers.sample();
                        StdOut.println("operation: " + uniform + " iteration: " + i);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

