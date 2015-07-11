import spock.lang.Specification

class FastTest extends Specification {

    @SuppressWarnings("GroovyAccessibility")
    def 'given points find collinear ones'() {
        given:
        def fast = new Fast()

        when:
        def collinear = fast.collinear(points)

        then:
        collinear == expected

        where:
        points                               | expected
        [new Point(10000, 0),
         new Point(0, 10000),
         new Point(3000, 7000),
         new Point(7000, 3000),
         new Point(20000, 21000),
         new Point(3000, 4000),
         new Point(14000, 15000),
         new Point(6000, 7000)] as Point[]   | [[new Point(10000, 0), new Point(7000, 3000), new Point(3000, 7000), new Point(0, 10000)],
                                                [new Point(3000, 4000), new Point(6000, 7000), new Point(14000, 15000), new Point(20000, 21000)]]
        [new Point(1, 2),
         new Point(3, 4),
         new Point(5, 6)] as Point[]         | []

        [new Point(19000, 10000),
         new Point(18000, 10000),
         new Point(32000, 10000),
         new Point(21000, 10000),
         new Point(1234, 5678),
         new Point(14000, 10000)] as Point[] | [[new Point(14000, 10000), new Point(18000, 10000),
                                                 new Point(19000, 10000), new Point(21000, 10000), new Point(32000, 10000)]]

    }
}

