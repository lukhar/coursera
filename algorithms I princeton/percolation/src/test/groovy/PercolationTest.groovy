import spock.lang.Specification

class PercolationTest extends Specification {

    @SuppressWarnings("GroovyResultOfObjectAllocationIgnored")
    def 'raise illegal argument exception if board side is not positive'() {
        when: 'invalid size passed to constructor'
        new Percolation(invalidSide)

        then: 'IllegalArgumentException is thrown'
        thrown IllegalArgumentException

        where: 'size is not valid'
        invalidSide << [-4, -1, 0]
    }
    def 'open raises exception when position out of range'() {
        given: 'percolation board of size two'
        def percolation = new Percolation(2)

        when: 'out of bound coordinates #x, #y are passed'
        percolation.open(x, y)

        then: 'throw index out of bound exception'
        thrown IndexOutOfBoundsException

        where: 'coordinates are outside the bounds'
        [x, y] << [[-1, -2], [1, 3], [10, 1], [0, 0]]
    }

    def 'open position is open'() {
        given: 'percolation of size five'
        def percolation = new Percolation(5)

        when: 'cell at given position is open'
        percolation.open(x, y)

        then: 'cell indicator states that cell is open'
        percolation.isOpen(x, y)

        where:
        [x, y] << [[4, 3], [1, 1], [1, 5]]
    }

    def 'single cell board percolates'() {
        given: 'new percolation board of size one'
        def percolation = new Percolation(1)

        when: 'cell in position 1, 1 is open'
        percolation.open(1, 1)

        then: 'board percolates'
        percolation.percolates()
    }

    def 'new board does not percolate'() {
        expect:
        assert !new Percolation(size).percolates()

        where:
        size << [1, 4, 5, 6, 8]
    }

    def 'opened top edge cells are full by default'() {
        given: 'new percolation board of size five'
        def percolation = new Percolation(5)

        when: 'top edge cell #x, #y is open'
        percolation.open(x, y)

        then: 'top edge cell #x, y# is full'
        percolation.isFull(x, y)

        where: 'top edge cells given'
        [x, y] << [[1, 1], [1, 2], [1, 3], [1, 4], [1, 5]]
    }

    def 'opened neighbour of percolated cell is full'() {
        given: 'new board with percolated #x, #y cell'
        def percolation = new Percolation(5)
        percolation.open(x, y)

        when: 'neighbour of #nx, #ny is open'
        percolation.open(nx, ny)

        then: 'neighbour of #nx, #ny is full'
        percolation.isFull(nx, ny)

        where:
        x | y | nx | ny
        1 | 1 | 2  | 1
        1 | 4 | 2  | 4
    }

    def 'opened neighbour of not percolated cell is empty'() {
        given: 'new board with percolated'
        def percolation = new Percolation(5)

        when: 'neighbour of #x, #y is open'
        percolation.open(nx, ny)

        then: 'neighbour of #x, #y is not full'
        !percolation.isFull(nx, ny)

        where:
        [nx, ny] << [[2, 4], [3, 5] ,[5, 1]]
    }

    def 'should not backwash'() {
        given: 'new board of size 3'
        def percolation = new Percolation(3)

        when: 'board is percolated on right side'
        percolation.open(1, 3)
        percolation.open(2, 3)
        percolation.open(3, 3)

        and: 'bottom left cell is open'
        percolation.open(3, 1)

        then: 'bottom left cell is not full'
        !percolation.isFull(3, 1)
        percolation.percolates()
    }
}
