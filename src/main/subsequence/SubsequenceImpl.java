package subsequence;

import java.util.List;

/**
 * @author Vladislav Konovalov
 */
public class SubsequenceImpl implements Subsequence {
    @Override
    public boolean find(List<Object> x, List<Object> y) {
        if (x == null || y == null) throw new IllegalArgumentException();
        if (x.isEmpty()) return true;

        int count = 0;
        for (Object oy : y) {
            if (oy.equals(x.get(count))) count++;
            if (count == x.size()) return true;
        }
        return false;
    }
}
