package calculator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Vladislav Konovalov
 */
public class StringIterator implements Iterator<String> {
    private final String expr;
    private int index = 0;

    public StringIterator(String expr) {
        if (!expr.matches("[+\\-*/().\\d\\s]+"))
            this.expr = null;
        else
            this.expr = expr.replaceAll("\\s", "");
    }

    @Override
    public String next() {
        if (expr == null) return null;
        if (index >= expr.length()) throw new NoSuchElementException();

        String element = expr.substring(index, index + 1);
        if (element.matches("[+\\-*/()]")) {
            index++;
            return element;
        } else if (element.matches("\\d")) {
            element = nextNumber(index);
            index += element.length();
            return element;
        } else {
            index++;
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        if (expr == null) return false;
        return index < expr.length();
    }

    private String nextNumber(int from) {
        String number = expr.substring(from);
        int numberLength = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.substring(i, i + 1).matches("[\\d.]"))
                numberLength++;
            else
                break;
        }
        number = number.substring(0, numberLength);
        while (number.endsWith("."))
            number = number.substring(0, number.length() - 1);
        return number;
    }
}
