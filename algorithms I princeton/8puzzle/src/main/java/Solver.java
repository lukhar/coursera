import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {

    private static final Comparator<Board> HAMMING = new Comparator<Board>() {
        @Override
        public int compare(Board o1, Board o2) {
            return o1.hamming() - o2.hamming();
        }
    };

    private static final Comparator<Board> MANHATTAN = new Comparator<Board>() {
        @Override
        public int compare(Board o1, Board o2) {
            return o1.manhattan() - o2.manhattan();
        }
    };

    private final List<Board> solution;

    public Solver(Board initial) {
        this.solution = solve(initial);
    }

    private List<Board> solve(Board initial) {
        Board twin = initial.twin();
        List<Board> result = new ArrayList<>();
        Board mainPrevious = null;
        Board twinPrevious = null;
        MinPQ<Board> mainQueue = new MinPQ<>(MANHATTAN);
        MinPQ<Board> twinQueue = new MinPQ<>(MANHATTAN);

        mainQueue.insert(initial);
        twinQueue.insert(twin);

        while (true) {
            Board mainSearch = mainQueue.delMin();
            Board twinSearch = twinQueue.delMin();
            result.add(mainSearch);

            if (mainSearch.isGoal()) {
                return result;
            }

            if (twinSearch.isGoal()) {
                return null;
            }

            for (Board board : mainSearch.neighbors()) {
                if (!board.equals(mainPrevious)) {
                    mainQueue.insert(board);
                }
            }

            for (Board board : twinSearch.neighbors()) {
                if (!board.equals(twinPrevious)) {
                    twinQueue.insert(board);
                }
            }

            mainPrevious = mainSearch;
            twinPrevious = twinSearch;
        }
    }

    public boolean isSolvable() {
        return solution != null;
    }

    public int moves() {
        if (solution != null) {
            return solution.size() - 1;
        }
        return -1;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    public static void main(String[] args) {
    }
}
