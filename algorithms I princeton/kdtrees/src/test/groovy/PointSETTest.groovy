import spock.lang.Specification

class PointSETTest extends Specification {
    def 'insertion of null argument raises null pointer exception'() {
        given:
        def points = new PointSET();

        when:
        points.insert(null);

        then:
        thrown(NullPointerException)
    }

    def 'checking for null argument raises null pointer exception'() {
        given:
        def points = new PointSET();

        when:
        points.contains(null);

        then:
        thrown(NullPointerException)
    }

    def 'checking range of null argument raises null pointer exception'() {
        given:
        def points = new PointSET();

        when:
        points.range(null);

        then:
        thrown(NullPointerException)
    }

    def 'checking nearest point of null argument raises null pointer exception'() {
        given:
        def points = new PointSET();

        when:
        points.nearest(null);

        then:
        thrown(NullPointerException)
    }

    def 'new PointSET is empty'() {
        expect:
        assert pointSet.isEmpty()
        assert pointSet.size() == 0

        where:
        pointSet = new PointSET();
    }

    def 'given points inside verify contains method'() {
        given:
        def points = new PointSET()

        expect:
        points.insert(initial)
        points.contains(point) == isContaining

        where:
        initial                | point                  | isContaining
        new Point2D(0.5, 0.16) | new Point2D(0.5, 0.16) | true
        new Point2D(0.5, 0.16) | new Point2D(0.1, 0.3)  | false
    }


}
