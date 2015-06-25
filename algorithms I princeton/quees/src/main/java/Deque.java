import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first = null;
    private Node last = first;

    public Deque() {
        this.size = 0;
    }

    @Override
    public java.util.Iterator<Item> iterator() {
        return new InternalIterator<Item>(first);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    private class InternalIterator<Item> implements java.util.Iterator<Item> {

        private Node current;

        private InternalIterator(Node current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item value = (Item) current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public void addFirst(Item item) {
        validate(item);

        Node temp = first;
        first = new Node(item);

        if (last == null) {
            last = first;
        }

        if (temp != null) {
            first.next = temp;
            temp.prev = first;
        }

        size++;
    }

    public void addLast(Item item) {
        validate(item);

        Node temp = last;
        last = new Node(item);

        if (first == null) {
            first = last;
        }

        if (temp != null) {
            temp.next = last;
            last.prev = temp;
        }

        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.value;
        if (first.next != null) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
        }
        size--;

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = last.value;
        if (last.prev != null) {
            last = last.prev;
            last.next = null;
        } else {
            last = null;
        }
        size--;

        return item;
    }


    public static void main(String[] args) {
    }

    private class Node {
        Node next;
        Node prev;
        Item value;

        public Node(Item item) {
            this.value = item;
        }
    }

    private void validate(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }
}
