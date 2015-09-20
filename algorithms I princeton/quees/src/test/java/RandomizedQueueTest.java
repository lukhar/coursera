import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomizedQueueTest {

    @Test
    public void newQueueShouldBeEmptyAndHaveZeroSize() {
        assertThat(new RandomizedQueue<>()).hasSize(0).isEmpty();
    }

    @Test
    public void shouldIncreaseSizeAfterAddingNewElement() {
        // given
        int anyItem = 0;
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        // when
        randomizedQueue.enqueue(anyItem);

        // then
        assertThat(randomizedQueue).hasSize(1).isNotEmpty();
    }

    @Test
    public void shouldThrowNullPointerWhenNullElementIsAddedToFront() {
        // given
        RandomizedQueue<Object> anyQueue = new RandomizedQueue<>();

        // when
        when(anyQueue).enqueue(null);

        // then
        assertThat((Throwable) (caughtException()))
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
        assertThat((Throwable) (caughtException()))
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
        assertThat((Throwable) (caughtException()))
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
        assertThat(strings).hasSize(0).isEmpty();
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
        assertThat(strings).isEmpty();
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
        assertThat((Throwable) (caughtException()))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasNoCause();
    }

    @Test
    public void givenEmptyQueueIteratorRaiseExceptionOnIteration() {
        // given
        Iterator<Object> iterator = new RandomizedQueue<>().iterator();

        // when
        when(iterator).next();

        // then
        assertThat((Throwable) (caughtException()))
            .isInstanceOf(NoSuchElementException.class)
            .hasNoCause();
    }

    @Test
    public void givenQueueIteratorRaiseExceptionOnIteration() {
        // given
        RandomizedQueue<Integer> objects = new RandomizedQueue<>();
        objects.enqueue(1);
        objects.enqueue(2);
        objects.dequeue();
        objects.enqueue(3);
        objects.dequeue();
        objects.dequeue();
        objects.enqueue(5);
        objects.enqueue(6);
        objects.dequeue();
        objects.enqueue(7);
        objects.dequeue();


        // when
        Iterator<Integer> iterator = objects.iterator();
        iterator.next();
        when(iterator).next();

        // then
        assertThat((Throwable) (caughtException()))
            .isInstanceOf(NoSuchElementException.class)
            .hasNoCause();
    }
}
