import spock.lang.Specification

class BoardTest extends Specification {

    def 'board dimension'() {
        expect:
        assert new Board(blocks).dimension() == dimmension

        where:
        blocks                                       | dimmension
        [[1, 2], [3, 4]] as int[][]                  | 2
        [[1, 2, 3], [4, 5, 6], [7, 8, 9]] as int[][] | 3
    }

    def 'hamming distance'() {
        expect:
        assert new Board(blocks).hamming() == hammingDistance

        where:
        blocks                 | hammingDistance
        [[8, 1, 3],
         [4, 0, 2],
         [7, 6, 5]] as int[][] | 5
    }

    def 'manhattan distance'() {
        expect:
        assert new Board(blocks).manhattan() == manhattanDistance

        where:
        blocks                 | manhattanDistance
        [[8, 1, 3],
         [4, 0, 2],
         [7, 6, 5]] as int[][] | 10
    }

    def 'board equality'() {
        expect:
        assert new Board(first).equals(new Board(second)) == equal

        where:
        first                       | second                      | equal
        [[1, 2], [3, 0]] as int[][] | [[1, 2], [3, 0]] as int[][] | true
        [[1, 2], [3, 0]] as int[][] | [[1, 2], [0, 3]] as int[][] | false
    }

    def 'null equality'() {
        expect:
        assert !new Board([[1, 2], [3, 0]] as int[][]).equals(null)
    }

    def 'is board solved (final goal)'() {
        expect:
        assert new Board(blocks).isGoal() == isGoal

        where:
        blocks                                       | isGoal
        [[1, 2, 3], [4, 5, 6], [7, 8, 0]] as int[][] | true
        [[1, 0], [2, 3]] as int[][]                  | false
    }

    def 'compute neighbouring boards'() {
        expect:
        assert new Board(initial).neighbors().toSet() == [new Board(first), new Board(second)].toSet()

        where:
        initial                     | first                       | second
        [[0, 1], [3, 2]] as int[][] | [[1, 0], [3, 2]] as int[][] | [[3, 1], [0, 2]] as int[][]
    }

    def 'create twin board'() {
        expect:
        assert new Board(initial).twin() == new Board(twin)

        where:
        initial                     | twin
        [[1, 2], [3, 0]] as int[][] | [[2, 1], [3, 0]] as int[][]

    }
}
