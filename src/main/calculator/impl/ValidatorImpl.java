package calculator.impl;

import calculator.Operation;
import calculator.api.Validator;
import java.util.Iterator;

/**
 * @author Vladislav Konovalov
 */
public class ValidatorImpl implements Validator {
    @Override
    public boolean isValid(String expr) {
        if (expr == null || expr.isEmpty()) return false;
        if (isSymbolsInvalid(expr)) return false;
        if (isParenthesesInvalid(expr)) return false;
        return true;
    }

    private boolean isSymbolsInvalid(String expr) {
        Iterator<String> signIterator = Operation.getAllSigns().iterator();
        StringBuilder symbolRegex = new StringBuilder();
        symbolRegex.append("[");
        while (signIterator.hasNext())
            symbolRegex.append("\\").append(signIterator.next());
        symbolRegex.append("().\\d]+");
        return !expr.matches(symbolRegex.toString());
    }

    private boolean isParenthesesInvalid(String expr) {
        if (expr.contains("()") || expr.contains(")("))
            return true;

        char[] chars = expr.toCharArray();
        int counter = 0;
        for (char c : chars) {
            if (c == '(')
                counter++;
            else if (c == ')')
                counter--;
            if (counter < 0)
                return true;
        }
        return counter != 0;
    }
}
