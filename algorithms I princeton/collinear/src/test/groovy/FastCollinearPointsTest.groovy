import spock.lang.Specification

class FastCollinearPointsTest extends Specification implements PointMixin, ComparableMixin {

    def 'given no points throw NullPointerException'() {
        when:
        new FastCollinearPoints(null)

        then:
        thrown(NullPointerException)
    }

    def 'given duplicate points throw IllegalArgumentException'(Point[] duplicates) {
        when:
        new FastCollinearPoints(duplicates)

        then:
        thrown(IllegalArgumentException)

        where:
        duplicates << [[point(1, 1), point(1, 2), point(1, 2), point(1, 3)]]
    }

    def 'given points find collinear segments'(Point[] points, LineSegment[] segments, numberOfSegments) {
        given:
        def collinear = new FastCollinearPoints(points)

        when:
        def computed = collinear.segments()

        then:
        toComparable(computed) == toComparable(segments)
        collinear.numberOfSegments() == numberOfSegments

        where:
        points                | segments                                                    | numberOfSegments
        [point(10000, 0),
         point(0, 10000),
         point(3000, 7000),
         point(7000, 3000),
         point(20000, 21000),
         point(3000, 4000),
         point(14000, 15000),
         point(6000, 7000)]   | [new LineSegment(point(10000, 0), point(0, 10000)),
                                 new LineSegment(point(3000, 4000), point(20000, 21000))]   | 2
        [point(1, 2),
         point(3, 4),
         point(5, 6)]         | []                                                          | 0

        [point(19000, 10000),
         point(18000, 10000),
         point(32000, 10000),
         point(21000, 10000),
         point(1234, 5678),
         point(14000, 10000)] | [new LineSegment(point(14000, 10000), point(32000, 10000))] | 1

        [point(10000, 0),
         point(8000, 2000),
         point(2000, 8000),
         point(0, 10000),
         point(20000, 0),
         point(18000, 2000),
         point(2000, 18000),
         point(10000, 20000),
         point(30000, 0),
         point(0, 30000),
         point(20000, 10000),
         point(13000, 0),
         point(11000, 3000),
         point(5000, 12000),
         point(9000, 6000)]   | [new LineSegment(point(10000, 0), point(30000, 0)),
                                 new LineSegment(point(10000, 0), point(0, 10000)),
                                 new LineSegment(point(13000, 0), point(5000, 12000)),
                                 new LineSegment(point(30000, 0), point(0, 30000))]         | 4
    }
}

