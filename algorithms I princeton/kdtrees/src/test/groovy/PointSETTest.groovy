import edu.princeton.cs.algs4.Point2D
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
        points.insert(initial)

        expect:
        points.contains(point) == isContaining

        where:
        initial          | point            | isContaining
        point(0.5, 0.16) | point(0.5, 0.16) | true
        point(0.5, 0.16) | point(0.1, 0.3)  | false
    }

    def 'given set of points and rectangle return points inside of rectangle'() {
        given:
        def points = new PointSET()
        def rectangle = new RectHV(1.0, 1.0, 4.0, 2.0)

        when:
        points.insert(a)
        points.insert(b)
        points.insert(c)

        and:
        def range = points.range(rectangle)

        then:
        range.containsAll(a, b)

        where:
        a               | b               | c
        point(3.0, 1.5) | point(2.5, 2.0) | point(0.5, 2.0)
        point(3.0, 1.5) | point(4.0, 1.5) | point(1.5, 20.0)
        point(1.0, 1.0) | point(4.0, 2.0) | point(1.5, 20.0)
    }

    def 'given set of points find nearest one'() {
        given:
        def points = new PointSET()

        when:
        points.insert(point(3.0, 1.5))
        points.insert(point(19.3, 21.5))
        points.insert(point(1.4, 1.3))

        and:
        def nearest = points.nearest(point)

        then:
        assert nearest == expected

        where:
        point              | expected
        point(4.0, 2.0)    | point(3.0, 1.5)
        point(21.0, 112.0) | point(19.3, 21.5)
        point(0.3, 0.7)    | point(1.4, 1.3)
    }

    def Point2D point(double x, double y) {
        return new Point2D(x, y)
    }
}
