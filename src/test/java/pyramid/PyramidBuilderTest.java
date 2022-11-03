package pyramid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PyramidBuilderTest {
    private final PyramidBuilder pyramidBuilder = new PyramidBuilder();

    @Test
    void buildPyramid1() {
        List<Integer> input = Arrays.asList(1, 15, 2);
        int[][] expected = new int[][] {
                {0, 1, 0},
                {2, 0, 15}
        };

        int[][] pyramid = pyramidBuilder.buildPyramid(input);

        comparePyramids(expected, pyramid);
    }

    @Test
    void buildPyramid2() {
        List<Integer> input = Arrays.asList(1, 3, 2, 9, 4, 5);
        int[][] expected = new int[][] {
                {0, 0, 1, 0, 0},
                {0, 2, 0, 3, 0},
                {4, 0, 5, 0, 9}
        };

        int[][] pyramid = pyramidBuilder.buildPyramid(input);

        comparePyramids(expected, pyramid);
    }

    @Test
    void buildPyramid3() {
        List<Integer> input = Arrays.asList(1, 3, 2, 9, 4, 5, 10, 8, 7, 6);
        int[][] expected = new int[][] {
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 2, 0, 3, 0, 0},
                {0, 4, 0, 5, 0, 6, 0},
                {7, 0, 8, 0, 9, 0, 10}
        };

        int[][] pyramid = pyramidBuilder.buildPyramid(input);

        comparePyramids(expected, pyramid);
    }

    @Test
    void buildPyramid4() {
        List<Integer> input = Arrays.asList(11, 1, 12, 3, 2, 13, 9, 4, 5, 14, 10, 8, 7, 15, 6);
        int[][] expected = new int[][] {
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 3, 0, 0, 0},
                {0, 0, 4, 0, 5, 0, 6, 0, 0},
                {0, 7, 0, 8, 0, 9, 0, 10, 0},
                {11, 0, 12, 0, 13, 0, 14, 0, 15}
        };

        int[][] pyramid = pyramidBuilder.buildPyramid(input);

        comparePyramids(expected, pyramid);
    }

    @Test
    void buildPyramid5() {
        List<Integer> input = Arrays.asList(11, 1, 21, 12, 3, 16, 2, 13, 9, 4, 17, 5, 14, 10, 18, 8, 7, 19, 15, 6, 20);
        int[][] expected = new int[][] {
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 2, 0, 3, 0, 0, 0, 0},
                {0, 0, 0, 4, 0, 5, 0, 6, 0, 0, 0},
                {0, 0, 7, 0, 8, 0, 9, 0, 10, 0, 0},
                {0, 11, 0, 12, 0, 13, 0, 14, 0, 15, 0},
                {16, 0, 17, 0, 18, 0, 19, 0, 20, 0, 21}
        };

        int[][] pyramid = pyramidBuilder.buildPyramid(input);

        comparePyramids(expected, pyramid);
    }


    @Test
    void buildPyramid6() {
        List<Integer> input = Arrays.asList(1, 3, 2, 9, 4, null);

        assertThrows(CannotBuildPyramidException.class, () -> pyramidBuilder.buildPyramid(input));
    }

    @Test
    void buildPyramid7() {
        List<Integer> input = Arrays.asList(1, 3, 2, 9, 4, 5, null);

        assertThrows(CannotBuildPyramidException.class, () -> pyramidBuilder.buildPyramid(input));
    }

    @Test
    void buildPyramid8() {
        List<Integer> input = new ArrayList<>();
        for (int i = 1; i < 256; i++)
            input.add(i);

        assertThrows(CannotBuildPyramidException.class, () -> pyramidBuilder.buildPyramid(input));
    }

    @Test
    void buildPyramid9() {
        List<Integer> input = new ArrayList<>();
        for (int i = 1; i < 10000; i++)
            input.add(i);

        assertThrows(CannotBuildPyramidException.class, () -> pyramidBuilder.buildPyramid(input));
    }

    @Test
    void buildPyramid10() {
        List<Integer> input = Collections.nCopies(Integer.MAX_VALUE - 1, 0);

        assertThrows(CannotBuildPyramidException.class, () -> pyramidBuilder.buildPyramid(input));
    }

    @Test
    void buildPyramid11() {
        List<Integer> input = Arrays.asList(1, 3, 2, 0, 4, 5);
        int[][] expected = new int[][] {
                {0, 0, 0, 0, 0},
                {0, 1, 0, 2, 0},
                {3, 0, 4, 0, 5}
        };

        int[][] pyramid = pyramidBuilder.buildPyramid(input);

        comparePyramids(expected, pyramid);
    }

    private void comparePyramids(int[][] expected, int[][] pyramid) {
        assertEquals(expected.length, pyramid.length);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], pyramid[i]);
        }
    }
}