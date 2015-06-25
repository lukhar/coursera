public class PercolationStats {

    private final double[] stats;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.stats = new double[T];
        calculate(N, T);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(N, T);

        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = "
            + percolationStats.confidenceLo() + ", "
            + percolationStats.confidenceHi());
    }

    private void calculate(int N, int T) {
        double totalSites = N * N;
        for (int i = 0; i < T; i++) {
            ClosedSitesIndex closedSitesIndex = new ClosedSitesIndex(N);
            Percolation percolation = new Percolation(N);

            int openedSites = 0;
            while (!percolation.percolates()) {
                int index = StdRandom.uniform(0, closedSitesIndex.getSize());
                int position = closedSitesIndex.get(index);
                int row = closedSitesIndex.fetchRow(position);
                int col = closedSitesIndex.fetchCol(position);

                percolation.open(row, col);
                openedSites++;
            }
            stats[i] = openedSites / totalSites;
        }
    }

    public double mean() {
        return StdStats.mean(stats);
    }

    public double stddev() {
        return StdStats.stddev(stats);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(stats.length);
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(stats.length);
    }

    private class ClosedSitesIndex {
        private final int[] index;
        private final int side;
        private int last;
        private int size;

        ClosedSitesIndex(int N) {
            this.size = N * N;
            this.side = N;
            this.index = new int[size];
            this.last = size - 1;

            for (int i = 0; i < size; i++) {
                index[i] = i;
            }
        }

        public int get(int p) {
            if (p < 0 || p > last) {
                throw new IndexOutOfBoundsException();
            }
            int val = index[p];
            index[p] = index[last];
            index[last] = -1;
            last--;
            size--;

            return val;
        }

        public int getSize() {
            return size;
        }

        public int fetchCol(int position) {
            return position % side + 1;
        }

        public int fetchRow(int position) {
            return position / side + 1;
        }
    }
}

