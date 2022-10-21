package subsequence;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vladislav Konovalov
 */
class SubsequenceTest {
    private final Subsequence subsequence = new SubsequenceImpl();

    @Test
    void find1() {
        List<Object> x = Stream.of(1, 2, 3, 4, 5, 7, 9, 20).collect(toList());
        List<Object> y = Stream.of(1, 3, 5, 7, 9).collect(toList());

        boolean result = subsequence.find(x, y);

        assertFalse(result);
    }

    @Test
    void find2() {
        List<Object> x = Stream.of(3, 9, 1, 5, 7).collect(toList());
        List<Object> y = Stream.of(1, 2, 3, 4, 5, 7, 9, 20).collect(toList());

        boolean result = subsequence.find(x, y);

        assertFalse(result);
    }

    @Test
    void find3() {
        List<Object> x = Stream.of("B", "A", "D", "C").collect(toList());
        List<Object> y = Stream.of("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "D").collect(toList());

        boolean result = subsequence.find(x, y);

        assertFalse(result);
    }

    @Test
    void find4() {
        List<Object> x = Stream.of("B", "A", "D", "C").collect(toList());
        List<Object> y = Stream.of("BD", "ABC", "B", "M", "D", "M", "C", "DC", "D").collect(toList());

        boolean result = subsequence.find(x, y);

        assertFalse(result);
    }

    @Test
    void find5() {
        List<Object> x = new ArrayList<>();
        List<Object> y = Stream.of("BD", "ABC", "B", "M", "D", "M", "C", "DC", "D").collect(toList());

        boolean result = subsequence.find(x, y);

        assertTrue(result);
    }

    @Test
    void find6() {
        List<Object> x = new ArrayList<>();
        List<Object> y = new ArrayList<>();

        boolean result = subsequence.find(x, y);

        assertTrue(result);
    }

    @Test
    void find7() {
        List<Object> x = Stream.of("B", "A", "D", "C").collect(toList());
        List<Object> y = new ArrayList<>();

        boolean result = subsequence.find(x, y);

        assertFalse(result);
    }

    @Test
    void find8() {
        List<Object> x = null;
        List<Object> y = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> subsequence.find(x, y));
    }

    @Test
    void find9() {
        List<Object> x = new ArrayList<>();
        List<Object> y = null;

        assertThrows(IllegalArgumentException.class, () -> subsequence.find(x, y));
    }

    @Test
    void find10() {
        List<Object> x = Stream.of(1, 3, 5, 7, 9).collect(toList());
        List<Object> y = Stream.of(10, 1, 2, 3, 4, 5, 7, 9, 20).collect(toList());

        boolean result = subsequence.find(x, y);

        assertTrue(result);
    }

    @Test
    void find11() {
        List<Object> x = Stream.of(1, 3, 5, 7, 9).collect(toList());
        List<Object> y = Stream.of(10, 1, 2, 3, 4, 3, 5, 7, 9, 20).collect(toList());

        boolean result = subsequence.find(x, y);

        assertTrue(result);
    }

    @Test
    void find12() {
        List<Object> x = Stream.of(1, 3, 5, 7, 9).collect(toList());
        List<Object> y = Stream.of(1, 2, 3, 4, 5, 7, 9, 20).collect(toList());

        boolean result = subsequence.find(x, y);

        assertTrue(result);
    }
}