package subsequence;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

/**
 * @author Vladislav Konovalov
 */
public class SubsequenceTest {
    private final Subsequence subsequence = new SubsequenceImpl();

    @Test
    public void find0() {
        List<Object> x = Stream.of(1, 3, 5, 7, 9).collect(toList());
        List<Object> y = Stream.of(10, 1, 2, 3, 4, 5, 7, 9, 20).collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertTrue(result);
    }

    @Test
    public void find00() {
        List<Object> x = Stream.of(1, 3, 5, 7, 9).collect(toList());
        List<Object> y = Stream.of(10, 1, 2, 3, 4, 3, 5, 7, 9, 20).collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertTrue(result);
    }

    @Test
    public void find() {
        List<Object> x = Stream.of(1, 3, 5, 7, 9).collect(toList());
        List<Object> y = Stream.of(1, 2, 3, 4, 5, 7, 9, 20).collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertTrue(result);
    }

    @Test
    public void find1() {
        List<Object> x = Stream.of(1, 2, 3, 4, 5, 7, 9, 20).collect(toList());
        List<Object> y = Stream.of(1, 3, 5, 7, 9).collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertFalse(result);
    }

    @Test
    public void find2() {
        List<Object> x = Stream.of(3, 9, 1, 5, 7).collect(toList());
        List<Object> y = Stream.of(1, 2, 3, 4, 5, 7, 9, 20).collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertFalse(result);
    }

    @Test
    public void find3() {
        List<Object> x = Stream.of("B", "A", "D", "C").collect(toList());
        List<Object> y = Stream.of("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "D").collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertFalse(result);
    }

    @Test
    public void find4() {
        List<Object> x = Stream.of("B", "A", "D", "C").collect(toList());
        List<Object> y = Stream.of("BD", "ABC", "B", "M", "D", "M", "C", "DC", "D").collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertFalse(result);
    }

    @Test
    public void find5() {
        List<Object> x = new ArrayList<>();
        List<Object> y = Stream.of("BD", "ABC", "B", "M", "D", "M", "C", "DC", "D").collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertTrue(result);
    }

    @Test
    public void find6() {
        List<Object> x = new ArrayList<>();
        List<Object> y = new ArrayList<>();

        boolean result = subsequence.find(x, y);

        Assert.assertTrue(result);
    }

    @Test
    public void find7() {
        List<Object> x = Stream.of("B", "A", "D", "C").collect(toList());
        List<Object> y = new ArrayList<>();

        boolean result = subsequence.find(x, y);

        Assert.assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void find8() {
        List<Object> x = null;
        List<Object> y = new ArrayList<>();

        subsequence.find(x, y);
    }

    @Test(expected = IllegalArgumentException.class)
    public void find9() {
        List<Object> x = new ArrayList<>();
        List<Object> y = null;

        subsequence.find(x, y);
    }
}