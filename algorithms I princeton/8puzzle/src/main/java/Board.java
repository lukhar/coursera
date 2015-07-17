import java.util.ArrayList;
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
        for (int i = 0;  i < blocks.length; i++) {
            for (int j = 0; j < blocks.length - 1; j++) {
                if (blocks[i][j] != 0 && blocks[i][j+1] != 0) {
                    int[][] copy = copy(blocks);
                    swap(copy, i, j, i, j+1);
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

        if (emptyRow - 1 >= 0) {
            int[][] neighbourBlocks = copy(blocks);
            swap(neighbourBlocks, emptyRow, emptyCol, emptyRow - 1, emptyCol);
            neighbours.add(new Board(neighbourBlocks));
        }
        if (emptyRow + 1 < blocks.length) {
            int[][] neighbourBlocks = copy(blocks);
            swap(neighbourBlocks, emptyRow, emptyCol, emptyRow + 1, emptyCol);
            neighbours.add(new Board(neighbourBlocks));
        }
        if (emptyCol - 1 >= 0) {
            int[][] neighbourBlocks = copy(blocks);
            swap(neighbourBlocks, emptyRow, emptyCol, emptyRow, emptyCol - 1);
            neighbours.add(new Board(neighbourBlocks));
        }
        if (emptyCol + 1 < blocks.length) {
            int[][] neighbourBlocks = copy(blocks);
            swap(neighbourBlocks, emptyRow, emptyCol, emptyRow, emptyCol + 1);
            neighbours.add(new Board(neighbourBlocks));
        }

        return neighbours;
    }

    private void swap(int[][] neighbourBlocks, int fromRow,
                      int fromCol, int toRow, int toCol) {
        int temp = neighbourBlocks[fromRow][fromCol];
        neighbourBlocks[fromRow][fromCol] = neighbourBlocks[toRow][toCol];
        neighbourBlocks[toRow][toCol] = temp;
    }

    private int[][] copy(int[][] blocks) {
        int[][] copy = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            System.arraycopy(blocks[i], 0, copy[i], 0, blocks.length);
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] block : blocks) {
            for (int j = 0; j < blocks.length; j++) {
                stringBuilder.append(block[j]).append("\t");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
    }
}
