import spock.lang.Specification

class FastTest extends Specification implements PointMixin {

    @SuppressWarnings("GroovyAccessibility")
    def 'given points find collinear ones'(Point[] points, collinear) {
        given:
        def fast = new Fast()

        when:
        def computed = fast.collinear(points)

        then:
        computed == collinear

        where:
        points                | collinear
        [point(10000, 0),
         point(0, 10000),
         point(3000, 7000),
         point(7000, 3000),
         point(20000, 21000),
         point(3000, 4000),
         point(14000, 15000),
         point(6000, 7000)]   | [[point(10000, 0), point(7000, 3000), point(3000, 7000), point(0, 10000)],
                                 [point(3000, 4000), point(6000, 7000), point(14000, 15000), point(20000, 21000)]]
        [point(1, 2),
         point(3, 4),
         point(5, 6)]         | []

        [point(19000, 10000),
         point(18000, 10000),
         point(32000, 10000),
         point(21000, 10000),
         point(1234, 5678),
         point(14000, 10000)] | [[point(14000, 10000), point(18000, 10000),
                                  point(19000, 10000), point(21000, 10000), point(32000, 10000)]]

    }
}

