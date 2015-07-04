import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DequeTest {

    @Test
    public void shouldHaveZeroSizeAfterCreation() {
        assertThat(new Deque<>().size()).isEqualTo(0);
    }

    @Test
    public void shouldBeEmptyAfterCreation() {
        assertThat(new Deque<>().isEmpty()).isTrue();
    }

    @Test
    public void shouldIncreaseSizeAfterAddingNewElement() {
        // given
        int anyItem = 0;
        Deque<Integer> Deque = new Deque<>();

        // when
        Deque.addLast(anyItem);

        // then
        assertThat(Deque.isEmpty()).isFalse();
        assertThat(Deque.size()).isEqualTo(1);
    }

    @Test
    public void shouldThrowNullPointerWhenNullElementIsAddedToFront() {
        // given
        Deque<Object> anyDeck = new Deque<>();

        // when
        when(anyDeck).addFirst(null);

        // then
        assertThat((Throwable) (caughtException()))
            .isInstanceOf(NullPointerException.class)
            .hasNoCause();
    }

    @Test
    public void shouldThrowNullPointerWhenNullElementAddedToBack() {
        // given
        Deque<Object> anyDeque = new Deque<>();

        // when
        when(anyDeque).addLast(null);

        // then
        assertThat((Throwable) (caughtException()))
            .isInstanceOf(NullPointerException.class)
            .hasNoCause();
    }

    @Test
    public void shouldPerformSimpleAddRemoveOperations() {
        // given
        Deque<String> strings = new Deque<>();
        strings.addFirst("one");

        // when
        String elem = strings.removeFirst();

        // then
        assertThat(strings.isEmpty()).isTrue();
        assertThat(strings.size()).isEqualTo(0);
        assertThat(elem).isEqualTo("one");
    }

    @Test
    public void shouldShouldEnsureSizeWhileAddingNewElementsInFront() {
        // given
        Deque<String> fullDeque = new Deque<>();
        fullDeque.addFirst("A");
        fullDeque.addFirst("B");

        // when
        fullDeque.addFirst("C");

        // then
        assertThat(fullDeque.size()).isEqualTo(3);
    }

    @Test
    public void shouldThrowExceptionWhenRemovingFrontElementFromEmptyDeque() {
        // given
        Deque<Object> emptyDeque = new Deque<>();

        // when
        when(emptyDeque).removeFirst();

        // then
        assertThat((Throwable) (caughtException()))
            .isInstanceOf(NoSuchElementException.class)
            .hasNoCause();
    }

    @Test
    public void shouldThrowExceptionWhenRemovingLastElementFromEmptyDeque() {
        // given
        Deque<Object> emptyDeque = new Deque<>();

        // when
        when(emptyDeque).removeLast();

        // then
        assertThat((Throwable) (caughtException()))
            .isInstanceOf(NoSuchElementException.class)
            .hasNoCause();
    }

    @Test
    public void shouldHandleConsecutiveAddingAndRemovingElements() {
        // given
        Deque<String> strings = new Deque<>();
        strings.addFirst("one");
        strings.addLast("two");

        // when
        List<String> output = Arrays.asList(strings.removeLast(), strings.removeLast());
        strings.addFirst("zero");

        // then
        assertThat(output).containsExactly("two", "one");
        assertThat(strings.size()).isEqualTo(1);
    }

    @Test
    public void shouldLastElementShouldBeSameAsFirstWhenDequeHasOnlyOneItem() {
        // given
        Deque<String> strings = new Deque<>();
        strings.addFirst("one");
        Deque<Object> objects = new Deque<>();
        objects.addLast("two");

        // when
        String last = strings.removeLast();
        Object first = objects.removeFirst();

        // then
        assertThat(last).isEqualTo("one");
        assertThat(first).isEqualTo("two");
    }

    @Test
    public void shouldThrowExceptionWhenInvokingRemoveOnIterator() {
        // given
        Deque<Object> objects = new Deque<>();
        Iterator<Object> iterator = objects.iterator();

        // when
        when(iterator).remove();

        // then
        assertThat((Throwable) (caughtException()))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasNoCause();
    }

    @Test
    public void shouldConstructValidIterator() {
        // given
        List<String> output = new ArrayList<>();
        Deque<String> strings = new Deque<>();
        strings.addLast("one");
        strings.addLast("two");
        strings.addLast("three");

        // when
        for (String string : strings) {
            output.add(string);
        }

        // then
        assertThat(output).containsExactly("one", "two", "three");
    }

    @Test
    public void shouldThrowNoSuchElementException() {
        // given
        Deque emptyDeque = new Deque();
        Iterator iterator = emptyDeque.iterator();

        // when
        when(iterator).next();

        // then
        assertThat((Throwable) (caughtException()))
            .isInstanceOf(NoSuchElementException.class)
            .hasNoCause();
    }

    @Test
    public void shouldBeEmptyAfterInterMixedOperations() {
        // given
        Deque<String> strings = new Deque<>();

        // when
        strings.addFirst("one");
        strings.addLast("two");
        strings.removeFirst();
        strings.removeLast();

        // then
        assertThat(strings.size()).isEqualTo(0);
        assertThat(strings.isEmpty()).isTrue();
    }

    @Test
    public void shouldProperlyRemoveElements() {
        // given
        Deque<String> strings = new Deque<>();
        strings.addLast("one");
        strings.addLast("two");
        strings.addLast("four");
        strings.addLast("five");
        strings.addLast("six");

        // when
        for (int i = 0; i < 5; i++) {
            strings.removeFirst();
        }

        // then
        assertThat(strings.isEmpty()).isTrue();
    }
}
