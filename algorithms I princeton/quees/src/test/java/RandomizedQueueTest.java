import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RandomizedQueueTest {
    @Test
    public void shouldHaveZeroSizeAfterCreation() {
        assertThat(new RandomizedQueue<>().size()).isEqualTo(0);
    }

    @Test
    public void shouldBeEmptyAfterCreation() {
        assertThat(new RandomizedQueue<>().isEmpty()).isTrue();
    }

    @Test
    public void shouldIncreaseSizeAfterAddingNewElement() {
        // given
        int anyItem = 0;
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        // when
        randomizedQueue.enqueue(anyItem);

        // then
        assertThat(randomizedQueue.isEmpty()).isFalse();
        assertThat(randomizedQueue.size()).isEqualTo(1);
    }

    @Test
    public void shouldThrowNullPointerWhenNullElementIsAddedToFront() {
        // given
        RandomizedQueue<Object> anyQueue = new RandomizedQueue<>();

        // when
        when(anyQueue).enqueue(null);

        // then
        assertThat(caughtException())
                .isInstanceOf(NullPointerException.class)
                .hasNoCause();
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenRandomizedDequeueEmptyQueue() {
        // given
        RandomizedQueue<Object> emptyQueue = new RandomizedQueue<>();

        // when
        when(emptyQueue).dequeue();

        // then
        assertThat(caughtException())
                .isInstanceOf(NoSuchElementException.class)
                .hasNoCause();
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenSamplingEmptyQueue() {
        // given
        RandomizedQueue<Object> emptyQueue = new RandomizedQueue<>();

        // when
        when(emptyQueue).sample();

        // then
        assertThat(caughtException())
                .isInstanceOf(NoSuchElementException.class)
                .hasNoCause();
    }

    @Test
    public void shouldPerformSimpleAddRemoveOperations() {
        // given
        RandomizedQueue<String> strings = new RandomizedQueue<>();
        strings.enqueue("one");

        // when
        String elem = strings.dequeue();

        // then
        assertThat(strings.isEmpty()).isTrue();
        assertThat(strings.size()).isEqualTo(0);
        assertThat(elem).isEqualTo("one");
    }

    @Test
    public void shouldProperlyRemoveElements() {
        // given
        RandomizedQueue<String> strings = new RandomizedQueue<>();
        strings.enqueue("one");
        strings.enqueue("two");
        strings.enqueue("four");
        strings.enqueue("five");
        strings.enqueue("six");

        // when
        for (int i = 0; i < 5; i++) {
            strings.dequeue();
        }

        // then
        assertThat(strings.isEmpty()).isTrue();
    }

    @Test
    public void shouldConstructValidIterator() {
        // given
        List<String> output = new ArrayList<>();
        RandomizedQueue<String> strings = new RandomizedQueue<>();
        strings.enqueue("one");
        strings.enqueue("two");
        strings.enqueue("three");

        // when
        for (String string : strings) {
            output.add(string);
        }

        // then
        assertThat(output).contains("one", "two", "three");
    }

    @Test
    public void shouldThrowExceptionWhenInvokingRemoveOnIterator() {
        // given
        RandomizedQueue<Object> objects = new RandomizedQueue<>();
        Iterator<Object> iterator = objects.iterator();

        // when
        when(iterator).remove();

        // then
        assertThat(caughtException())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasNoCause();
    }

    @Test
    public void stressTest() {
        // given
        RandomizedQueue<Integer> integers = new RandomizedQueue<>();

        // when
        integers.enqueue(1);
        integers.dequeue();
        integers.enqueue(1);
        integers.enqueue(2);
        integers.dequeue();
        integers.enqueue(2);

        // then
    }
}
