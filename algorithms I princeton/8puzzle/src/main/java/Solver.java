import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {

    private final static Comparator<Board> HAMMING = new Comparator<Board>() {
        @Override
        public int compare(Board o1, Board o2) {
            return o1.hamming() - o2.hamming();
        }
    };

    private final static Comparator<Board> MANHATTAN = new Comparator<Board>() {
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
        List<Board> result = new ArrayList<>();
        Board previous = null;
        MinPQ<Board> queue = new MinPQ<>(MANHATTAN);
        queue.insert(initial);

        while (!queue.isEmpty()) {
            Board search = queue.delMin();
            result.add(search);

            if (search.isGoal()) {
                return result;
            }

            for (Board board : search.neighbors()) {
                if (!board.equals(previous)) {
                    queue.insert(board);
                }
            }
            previous = search;
        }

        return result;
    }

    public boolean isSolvable() {
        return false;
    }

    public int moves() {
        return solution.size();
    }

    public Iterable<Board> solution() {
        return solution;
    }

    public static void main(String[] args) {
    }
}
