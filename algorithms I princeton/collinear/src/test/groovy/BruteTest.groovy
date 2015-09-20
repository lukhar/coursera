import spock.lang.Specification

class BruteTest extends Specification implements PointMixin {

    @SuppressWarnings("GroovyAccessibility")
    def 'given points check if they have same slope'(Point ref, Point[] points, sameSlope) {
        expect:
        Brute.sameSlope(ref, points) == sameSlope

        where:
        ref          | points                       | sameSlope
        point(0, 0)  | [point(1, 1), point(3, 3)]   | true
        point(0, 0)  | [point(-1, 2), point(-4, 8)] | true
        point(0, 0)  | [point(1, 2), point(-4, 8)]  | false
        point(1, 10) | []                           | true
        point(1, 10) | null                         | true
    }

    @SuppressWarnings("GroovyAccessibility")
    def 'given points find collinear ones'(Point[] points, collinear) {
        given:
        def brute = new Brute()

        when:
        def computed = brute.collinear(points)

        then:
        computed == collinear

        where:
        points              | collinear
        [point(10000, 0),
         point(0, 10000),
         point(3000, 7000),
         point(7000, 3000),
         point(20000, 21000),
         point(3000, 4000),
         point(14000, 15000),
         point(6000, 7000)] | [[point(10000, 0), point(7000, 3000), point(3000, 7000), point(0, 10000)],
                               [point(3000, 4000), point(6000, 7000), point(14000, 15000), point(20000, 21000)]]
        [point(1, 2),
         point(3, 4),
         point(5, 6)]       | []
    }
}
