public class Board {
    private final int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = blocks;
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int expectedBlock = 1;
        int distance = 0;

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (i == blocks.length - 1 && j == blocks.length - 1) {
                    continue;
                }
                if (blocks[i][j] != expectedBlock) {
                    distance++;
                }
                expectedBlock++;
            }
        }

        return distance;
    }

    public int manhattan() {
        int distance = 0;

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] == 0) {
                    continue;
                }
                distance += euclideanDistance(blocks[i][j], i, j);
            }
        }

        return distance;
    }

    private int euclideanDistance(int block, int row, int col) {
        int expectedRow = (block - 1) / blocks.length;
        int expectedCol = (block - 1) % blocks.length;

        return Math.abs(row - expectedRow) + Math.abs(col - expectedCol);
    }

    public boolean isGoal() {
        return false;
    }

    public Board twin() {
        return null;
    }

    @Override
    public boolean equals(Object y) {
        return false;
    }

    public Iterable<Board> neighbors() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    public static void main(String[] args) {
    }
}
