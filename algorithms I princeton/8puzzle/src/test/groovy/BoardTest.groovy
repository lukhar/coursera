import spock.lang.Specification

class BoardTest extends Specification {

    def 'board dimension'(int[][] blocks, dimension) {
        expect:
        assert new Board(blocks).dimension() == dimension

        where:
        blocks                            | dimension
        [[1, 2], [3, 4]]                  | 2
        [[1, 2, 3], [4, 5, 6], [7, 8, 9]] | 3
    }

    def 'hamming distance'(int[][] blocks, hammingDistance) {
        expect:
        assert new Board(blocks).hamming() == hammingDistance

        where:
        blocks      | hammingDistance
        [[8, 1, 3],
         [4, 0, 2],
         [7, 6, 5]] | 5
    }

    def 'manhattan distance'(int[][] blocks, manhattanDistance) {
        expect:
        assert new Board(blocks).manhattan() == manhattanDistance

        where:
        blocks      | manhattanDistance
        [[8, 1, 3],
         [4, 0, 2],
         [7, 6, 5]] | 10
    }

    def 'board equality'(int[][] first, int[][] second, equal) {
        expect:
        assert new Board(first).equals(new Board(second)) == equal

        where:
        first                             | second           | equal
        [[1, 2], [3, 0]]                  | [[1, 2], [3, 0]] | true
        [[1, 2], [3, 0]]                  | [[1, 2], [0, 3]] | false
        [[1, 2, 3], [4, 5, 6], [7, 8, 0]] | [[1, 2], [0, 3]] | false
    }

    def 'null equality'() {
        expect:
        assert !new Board([[1, 2], [3, 0]] as int[][]).equals(null)
    }

    def 'is board solved (final goal)'(int[][] blocks, isGoal) {
        expect:
        assert new Board(blocks).isGoal() == isGoal

        where:
        blocks                            | isGoal
        [[1, 2, 3], [4, 5, 6], [7, 8, 0]] | true
        [[1, 0], [2, 3]]                  | false
    }

    def 'compute neighbouring boards'(int[][] initial, int[][] first, int[][] second) {
        expect:
        assert new Board(initial).neighbors().toSet() == [new Board(first), new Board(second)].toSet()

        where:
        initial          | first            | second
        [[0, 1], [3, 2]] | [[1, 0], [3, 2]] | [[3, 1], [0, 2]]
    }

    def 'create twin board'(int[][] initial, int[][] twin) {
        expect:
        assert new Board(initial).twin() == new Board(twin)

        where:
        initial          | twin
        [[1, 2], [3, 0]] | [[2, 1], [3, 0]]

    }
}
