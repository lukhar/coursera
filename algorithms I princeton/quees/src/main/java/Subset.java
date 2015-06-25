
public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> strings = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            strings.enqueue(StdIn.readString());
        }

        int k = Integer.parseInt(args[0]);

        for (int i = 0; i < k; i++) {
            StdOut.println(strings.dequeue());
        }
    }
}
