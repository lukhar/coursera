import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;

public class PointTest {

    @Test
    public void shouldBeLessWhenYCoordinateIsGreater() {
        Point biggerY = new Point(3, 4);
        assertThat(new Point(2, 3).compareTo(biggerY)).isLessThan(0);
    }

    @Test
    public void shouldBeEqualWhenCoordinatesAreSame() {
        assertThat(new Point(2, 4).compareTo(new Point(2, 4))).isEqualTo(0);
    }

    @Test
    public void shouldBeLessWhenYCoordinateIsSameAndXIsGreater() {
        Point greaterXSameY = new Point(3, 4);
        assertThat(new Point(2, 4).compareTo(greaterXSameY)).isLessThan(0);
    }

    @Test
    public void slopeOrderShouldBeLessWhenLineIsLessSteep() {
        Point base = new Point(2, 2);
        Point steeper = new Point(3, 4);
        Point smoother = new Point(4, 3);
        assertThat(base.slopeOrder().compare(smoother, steeper)).isLessThan(0);
    }

    @Test
    public void shouldComputeSlope() {
        Point a = new Point(12, 10);
        Point b = new Point(3, 6);

        assertThat(a.slopeTo(b)).isEqualTo((6.0 - 10) / (3.0 - 12.0));
    }

    @Test
    public void slopeShouldBePositiveInfinityWhenLineIsVertical() {
        Point a = new Point(0, 10);
        Point b = new Point(0, 6);

        assertThat(a.slopeTo(b)).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    public void slopeShouldBePositiveZeroWhenLineIsHorizontal() {
        Point a = new Point(12, 0);
        Point b = new Point(3, 0);

        assertThat(a.slopeTo(b)).isEqualTo(0);
    }

    @Test
    public void slopeShouldBeNegativeInfinityWhenLineIsDegenerate() {
        Point a = new Point(12, 12);
        Point b = new Point(12, 12);

        assertThat(a.slopeTo(b)).isEqualTo(Double.NEGATIVE_INFINITY);
    }

}