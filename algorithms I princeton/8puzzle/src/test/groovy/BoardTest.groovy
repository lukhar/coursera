import spock.lang.Specification

class BoardTest extends Specification {

    def 'given board object get dimension'() {
        given: 'symmetric 2d array'
        def blocks = new int[size][size]

        when:
        def board = new Board(blocks)

        then:
        assert board.dimension() == dimmension

        where:
        size | dimmension
        4    | 4
        5    | 5
        2    | 2
    }
}
