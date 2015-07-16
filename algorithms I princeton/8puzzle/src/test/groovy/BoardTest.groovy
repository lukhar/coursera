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

}
