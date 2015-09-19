import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final byte CLOSED = 0;
    private static final byte OPEN = 1;
    private static final byte OUT_OF_BOUND = -1;

    private final WeightedQuickUnionUF weightedQuickUnion;
    private final int side;
    private final int topCell;
    private final int bottomCell;
    private final byte[] vacancy;
    private final WeightedQuickUnionUF connectedToTop;

    public Percolation(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException();
        }
        this.side = side;
        this.weightedQuickUnion = new WeightedQuickUnionUF(side * side + 2);
        this.connectedToTop = new WeightedQuickUnionUF(side * side + 1);
        this.topCell = side * side;
        this.bottomCell = side * side + 1;
        this.vacancy = new byte[side * side];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < side; i++) {
            weightedQuickUnion.union(topCell, i);
            connectedToTop.union(topCell, i);
            weightedQuickUnion.union(bottomCell, side * (side - 1) + i);
        }
    }

    public void open(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        int middleCell = linearPosition(row, col);
        if (vacancy[middleCell] != CLOSED) {
            return;
        }

        vacancy[middleCell] = OPEN;

        connectNeighbour(middleCell, linearPosition(row - 1, col));
        connectNeighbour(middleCell, linearPosition(row + 1, col));
        connectNeighbour(middleCell, linearPosition(row, col - 1));
        connectNeighbour(middleCell, linearPosition(row, col + 1));
    }

    private void connectNeighbour(int central, int neighbour) {
        if (neighbour == OUT_OF_BOUND) {
            return;
        }

        if (vacancy[neighbour] != CLOSED) {
            weightedQuickUnion.union(central, neighbour);
            connectedToTop.union(central, neighbour);
        }
    }

    private boolean isValid(int row, int col) {
        return row > 0 && row <= side && col > 0 && col <= side;
    }

    public boolean percolates() {
        boolean connected = weightedQuickUnion.connected(topCell, bottomCell);

        if (side != 1) {
            return connected;
        } else {
            return connected && isFull(1, 1);
        }
    }

    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return vacancy[linearPosition(row, col)] == OPEN;
    }

    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return weightedQuickUnion.connected(topCell, linearPosition(row, col))
            && vacancy[linearPosition(row, col)] != CLOSED
            && connectedToTop.connected(topCell, linearPosition(row, col));
    }

    private int linearPosition(int row, int col) {
        if (!isValid(row, col)) {
            return OUT_OF_BOUND;
        }

        return (row - 1) * side + (col - 1);
    }
}
