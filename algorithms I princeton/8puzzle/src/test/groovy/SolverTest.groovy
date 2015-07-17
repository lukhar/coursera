import spock.lang.Specification

class SolverTest extends Specification {

    def 'given solved board return empty solution list'() {
        given:
        def solvedBoard = new Board([[1, 2], [3, 0]] as int[][])

        when:
        def solution = new Solver(solvedBoard).solution()

        then:
        assert solution == [solvedBoard]
    }

    def 'given solvable board compute solution'() {
        given:
        def initialBoard = new Board([[0, 1, 3], [4, 2, 5], [7, 8, 6]] as int[][])

        when:
        def solution = new Solver(initialBoard).solution()

        then:
        assert solution == [new Board([[0, 1, 3], [4, 2, 5], [7, 8, 6]] as int[][]),
                            new Board([[1, 0, 3], [4, 2, 5], [7, 8, 6]] as int[][]),
                            new Board([[1, 2, 3], [4, 0, 5], [7, 8, 6]] as int[][]),
                            new Board([[1, 2, 3], [4, 5, 0], [7, 8, 6]] as int[][]),
                            new Board([[1, 2, 3], [4, 5, 6], [7, 8, 0]] as int[][])]
    }
}
