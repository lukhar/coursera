import spock.lang.Specification

class TestBrute extends Specification {

    @SuppressWarnings("GroovyAccessibility")
    def 'given points check if they have same slope'() {
        expect:
        Brute.sameSlope(ref, points) == sameSlope

        where:
        ref              | points                                          | sameSlope
        new Point(0, 0)  | [new Point(1, 1), new Point(3, 3)] as Point[]   | true
        new Point(0, 0)  | [new Point(-1, 2), new Point(-4, 8)] as Point[] | true
        new Point(0, 0)  | [new Point(1, 2), new Point(-4, 8)] as Point[]  | false
        new Point(1, 10) | [] as Point[]                                   | true
        new Point(1, 10) | null                                            | true
    }

    @SuppressWarnings("GroovyAccessibility")
    def 'given points find collinear ones'() {
        given:
        def brute = new Brute()

        when:
        def collinear = brute.collinear(points)

        then:
        collinear == expected

        where:
        points                             | expected
        [new Point(10000, 0),
         new Point(0, 10000),
         new Point(3000, 7000),
         new Point(7000, 3000),
         new Point(20000, 21000),
         new Point(3000, 4000),
         new Point(14000, 15000),
         new Point(6000, 7000)] as Point[] | [[new Point(10000, 0), new Point(7000, 3000), new Point(3000, 7000), new Point(0, 10000)],
                                              [new Point(3000, 4000), new Point(6000, 7000), new Point(14000, 15000), new Point(20000, 21000)]]
        [new Point(1, 2),
         new Point(3, 4),
         new Point(5, 6)] as Point[] | []
    }
}
