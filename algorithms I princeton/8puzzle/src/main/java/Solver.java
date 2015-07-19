import java.util.ArrayList;
import java.util.List;

public class Solver {

    private static class SearchNode implements Comparable<SearchNode> {
        private final int moves;
        private final Board board;
        private final SearchNode previous;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }

        public int priority() {
            return moves + board.manhattan();
        }

        @Override
        public int compareTo(SearchNode that) {
            return this.priority() - that.priority();
        }
    }

    private final List<Board> solution;

    public Solver(Board initial) {
        this.solution = solve(initial);
    }

    private List<Board> solve(Board initial) {
        Board twin = initial.twin();
        MinPQ<SearchNode> mainQueue = new MinPQ<>();
        MinPQ<SearchNode> twinQueue = new MinPQ<>();
        int moves = 0;
        mainQueue.insert(new SearchNode(initial, moves, null));
        twinQueue.insert(new SearchNode(twin, moves, null));


        while (true) {
            moves++;
            SearchNode mainSearch = mainQueue.delMin();
            SearchNode twinSearch = twinQueue.delMin();

            if (mainSearch.board.isGoal()) {
                return retraceSteps(mainSearch);
            }

            if (twinSearch.board.isGoal()) {
                return null;
            }

            for (Board board : mainSearch.board.neighbors()) {
                SearchNode mainPrevious = mainSearch.previous;
                if (mainPrevious == null || !board.equals(mainPrevious.board)) {
                    mainQueue.insert(new SearchNode(board, moves, mainSearch));
                }
            }

            for (Board board : twinSearch.board.neighbors()) {
                SearchNode twinPrevious = twinSearch.previous;
                if (twinPrevious == null || !board.equals(twinPrevious.board)) {
                    twinQueue.insert(new SearchNode(board, moves, twinSearch));
                }
            }
        }
    }

    private List<Board> retraceSteps(SearchNode searchNode) {
        List<Board> result = new ArrayList<>();
        SearchNode current = searchNode;
        while (current != null) {
            result.add(0, current.board);
            current = current.previous;
        }
        return result;
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
