import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class Board {
    private final int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = copy(blocks);
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
        int expectedBlock = 1;

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (i == blocks.length - 1 && j == blocks.length - 1) {
                    return blocks[i][j] == 0;
                }
                if (blocks[i][j] != expectedBlock) {
                    return false;
                }
                expectedBlock++;
            }
        }

        return true;
    }

    public Board twin() {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length - 1; j++) {
                if (blocks[i][j] != 0 && blocks[i][j + 1] != 0) {
                    int[][] copy = copy(blocks);
                    swap(copy, i, j, i, j + 1);
                    return new Board(copy);
                }
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }

        if (this == y) {
            return true;
        }

        if (!y.getClass().equals(this.getClass())) {
            return false;
        }

        Board that = (Board) y;

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        int emptyRow = 0;
        int emptyCol = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    break;
                }
            }
        }

        List<Board> neighbours = new ArrayList<>();

        if (isInRange(emptyRow - 1)) {
            int[][] neighbourBlocks = copy(blocks);
            swap(neighbourBlocks, emptyRow, emptyCol, emptyRow - 1, emptyCol);
            neighbours.add(new Board(neighbourBlocks));
        }
        if (isInRange(emptyRow + 1)) {
            int[][] neighbourBlocks = copy(blocks);
            swap(neighbourBlocks, emptyRow, emptyCol, emptyRow + 1, emptyCol);
            neighbours.add(new Board(neighbourBlocks));
        }
        if (isInRange(emptyCol - 1)) {
            int[][] neighbourBlocks = copy(blocks);
            swap(neighbourBlocks, emptyRow, emptyCol, emptyRow, emptyCol - 1);
            neighbours.add(new Board(neighbourBlocks));
        }
        if (isInRange(emptyCol + 1)) {
            int[][] neighbourBlocks = copy(blocks);
            swap(neighbourBlocks, emptyRow, emptyCol, emptyRow, emptyCol + 1);
            neighbours.add(new Board(neighbourBlocks));
        }

        return neighbours;
    }

    private boolean isInRange(int coordinate) {
        return coordinate >= 0 && coordinate < blocks.length;
    }

    private void swap(int[][] neighbourBlocks, int fromRow,
                      int fromCol, int toRow, int toCol) {
        int temp = neighbourBlocks[fromRow][fromCol];
        neighbourBlocks[fromRow][fromCol] = neighbourBlocks[toRow][toCol];
        neighbourBlocks[toRow][toCol] = temp;
    }

    private int[][] copy(int[][] original) {
        int[][] copy = new int[original.length][original.length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original.length);
        }

        return copy;
    }

    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format("%d\n", blocks.length);
        for (int[] block : blocks) {
            for (int innerBlock : block) {
                formatter.format("%4d", innerBlock);
            }
            formatter.format("\n");
        }

        return formatter.toString();
    }

    public static void main(String[] args) {
    }
}
