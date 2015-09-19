import edu.princeton.cs.algs4.Point2D
import spock.lang.Specification


class KdTreeTest extends Specification {

    def 'insertion of null argument raises null pointer exception'() {
        given:
        def points = new KdTree();

        when:
        points.insert(null);

        then:
        thrown(NullPointerException)
    }

    def 'checking for null argument raises null pointer exception'() {
        given:
        def points = new KdTree();

        when:
        points.contains(null);

        then:
        thrown(NullPointerException)
    }

    def 'checking range of null argument raises null pointer exception'() {
        given:
        def points = new KdTree();

        when:
        points.range(null);

        then:
        thrown(NullPointerException)
    }

    def 'checking nearest point of null argument raises null pointer exception'() {
        given:
        def points = new KdTree();

        when:
        points.nearest(null);

        then:
        thrown(NullPointerException)
    }

    def 'contains inserted point'() {
        given:
        def points = new KdTree();
        def insertedPoint = new Point2D(4.5, 6.7)

        when:
        points.insert(insertedPoint)

        then:
        assert points.contains(insertedPoint)
    }

    def 'given multiple points inserted contains all of them'() {
        given:
        def points = new KdTree()
        def a = new Point2D(-0.5, 12)
        def b = new Point2D(8.5, -4.5)
        def c = new Point2D(3.2, 1.5)

        when:
        points.insert(a)
        points.insert(b)
        points.insert(c)

        then:
        assert points.contains(a)
        assert points.contains(b)
        assert points.contains(c)
    }

    def 'given multiple points inserted compute size'() {
        given:
        def points = new KdTree()

        when:
        points.insert(new Point2D(-0.5, 12))
        points.insert(new Point2D(8.5, -4.5))
        points.insert(new Point2D(3.2, 1.5))

        then:
        assert points.size() == 3
    }

    def 'given same point inserted several time do not increase size'() {
        given:
        def points = new KdTree()
        def samePoint = new Point2D(-0.5, 12)

        when:
        points.insert(samePoint)
        points.insert(samePoint)
        points.insert(samePoint)

        then:
        assert points.size() == 1
    }

    def 'given new KdTree is empty'() {
        expect:
        assert points.isEmpty()
        assert points.size() == 0

        where:
        points = new KdTree()
    }

    def 'given set of points and rectangle return points inside of rectangle'() {
        given:
        def points = new KdTree()
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
        a                     | b                     | c
        new Point2D(3.0, 1.5) | new Point2D(2.5, 2.0) | new Point2D(0.5, 2.0)
        new Point2D(3.0, 1.5) | new Point2D(4.0, 1.5) | new Point2D(1.5, 20.0)
        new Point2D(1.0, 1.0) | new Point2D(4.0, 2.0) | new Point2D(1.5, 20.0)
    }

    def 'given set of points find nearest one'() {
        given:
        def points = new KdTree()

        when:
        points.insert(new Point2D(3.0, 1.5))
        points.insert(new Point2D(19.3, 21.5))
        points.insert(new Point2D(1.4, 1.3))

        and:
        def nearest = points.nearest(point)

        then:
        assert nearest == expected

        where:
        point                    | expected
        new Point2D(4.0, 2.0)    | new Point2D(3.0, 1.5)
        new Point2D(21.0, 112.0) | new Point2D(19.3, 21.5)
        new Point2D(0.3, 0.7)    | new Point2D(1.4, 1.3)
    }

    def 'given empty set return null as nearest point'() {
       expect:
       assert new KdTree().nearest(new Point2D(1.0, 2.0)) == null
    }
}
