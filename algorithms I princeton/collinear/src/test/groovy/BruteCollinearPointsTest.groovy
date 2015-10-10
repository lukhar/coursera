import spock.lang.Specification

class BruteCollinearPointsTest extends Specification implements PointMixin, ComparableMixin {

    @SuppressWarnings("GroovyAccessibility")
    def 'given points check if they have same slope'(Point ref, Point[] points, sameSlope) {
        expect:
        BruteCollinearPoints.sameSlope(ref, points) == sameSlope

        where:
        ref          | points                       | sameSlope
        point(0, 0)  | [point(1, 1), point(3, 3)]   | true
        point(0, 0)  | [point(-1, 2), point(-4, 8)] | true
        point(0, 0)  | [point(1, 2), point(-4, 8)]  | false
        point(1, 10) | []                           | true
        point(1, 10) | null                         | true
    }

    def 'given no points throw NullPointerException'() {
        when:
        new BruteCollinearPoints(null)

        then:
        thrown(NullPointerException)
    }

    def 'given duplicate points throw IllegalArgumentException'(Point[] duplicates) {
        when:
        new BruteCollinearPoints(duplicates)

        then:
        thrown(IllegalArgumentException)

        where:
        duplicates << [[point(1, 1), point(1, 2), point(1, 2), point(1, 3)]]
    }

    def 'given points find collinear segments'(Point[] points, LineSegment[] segments, numberOfSegments) {
        given:
        def collinear = new BruteCollinearPoints(points)

        when:
        def computed = collinear.segments()

        then:
        toComparable(computed) == toComparable(segments)
        collinear.numberOfSegments() == numberOfSegments

        where:
        points              | segments                                                  | numberOfSegments
        [point(10000, 0),
         point(0, 10000),
         point(3000, 7000),
         point(7000, 3000),
         point(20000, 21000),
         point(3000, 4000),
         point(14000, 15000),
         point(6000, 7000)] | [new LineSegment(point(10000, 0), point(0, 10000)),
                               new LineSegment(point(3000, 4000), point(20000, 21000))] | 2
        [point(1, 2),
         point(3, 4),
         point(5, 6)]       | []                                                        | 0
    }
}
