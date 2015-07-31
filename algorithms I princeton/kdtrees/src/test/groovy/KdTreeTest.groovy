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
}
