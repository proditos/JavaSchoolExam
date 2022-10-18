package pyramid;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vladislav Konovalov
 */
public class PyramidBuilder {
    public int[][] buildPyramid(List<Integer> input) {
        if (input == null) throw new CannotBuildPyramidException();
        for (Integer integer : input)
            if (integer == null) throw new CannotBuildPyramidException();
        int inputSize = input.size();
        if (!isTriangular(inputSize)) throw new CannotBuildPyramidException();

        Collections.sort(input);
        Iterator<Integer> inputIterator = input.listIterator();
        int rows = getTriangularOrderNumber(inputSize);
        int cols = 2 * rows - 1;
        int[][] pyramid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < i + 1; j++) {
                int colIndex = (rows - 1 - i) + 2 * j;
                pyramid[i][colIndex] = inputIterator.next();
            }
        }

        return pyramid;
    }

    private boolean isTriangular(int number) {
        number = 8 * number + 1;
        int tmp = (int) Math.sqrt(number);
        return Math.pow(tmp, 2) == number;
    }

    private int getTriangularOrderNumber(int number) {
        double sqrtValue = Math.sqrt(8.0 * number + 1.0);
        int sqrtRoundedValue = (int) Math.round(sqrtValue);
        return (sqrtRoundedValue - 1) / 2;
    }
}
