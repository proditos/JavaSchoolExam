package calculator.impl;

import calculator.common.Operation;
import calculator.api.Validator;
import java.util.Iterator;

/**
 * @author Vladislav Konovalov
 */
public class ValidatorImpl implements Validator {
    @Override
    public boolean isValid(String expr) {
        if (expr == null || expr.isEmpty()) {
            return false;
        }
        if (isSymbolsInvalid(expr)) {
            return false;
        }
        if (isNumbersInvalid(expr)) {
            return false;
        }
        if (isParenthesesInvalid(expr)) {
            return false;
        }
        return !isOperationsInvalid(expr);
    }

    private boolean isSymbolsInvalid(String expr) {
        Iterator<String> signIterator = Operation.getAllSigns().iterator();
        StringBuilder symbolRegex = new StringBuilder();
        symbolRegex.append("[");
        while (signIterator.hasNext()) {
            symbolRegex.append("\\").append(signIterator.next());
        }
        symbolRegex.append("().\\d]+");
        return !expr.matches(symbolRegex.toString());
    }

    private boolean isNumbersInvalid(String expr) {
        String[] withoutNumbers = expr.split("\\d+(\\.\\d+)?");
        for (int i = 0; i < withoutNumbers.length; i++) {
            String element = withoutNumbers[i];
            if (element.isEmpty() || element.contains(".")) {
                if (i == 0 && Character.isDigit(expr.charAt(0))) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    private boolean isParenthesesInvalid(String expr) {
        if (expr.contains("()") || expr.contains(")(")) {
            return true;
        }

        char[] chars = expr.toCharArray();
        int counter = 0;
        for (char c : chars) {
            if (c == '(') {
                counter++;
            }
            if (c == ')') {
                counter--;
            }
            if (counter < 0) {
                return true;
            }
        }
        return counter != 0;
    }

    private boolean isOperationsInvalid(String expr) {
        char[] chars = expr.toCharArray();
        int length = chars.length;

        boolean isLastOperation = Operation.isOperation("" + chars[length - 1]);
        if (isLastOperation) {
            return true;
        }

        boolean isFirstOperation = Operation.isOperation("" + chars[0]);
        if (isFirstOperation && Operation.getBySign("" + chars[0]).getPriority() > 1) {
            return true;
        }

        for (int i = 1; i < length - 1; i++) {
            boolean isCurrentOperation = Operation.isOperation("" + chars[i]);
            boolean isLeftOperation = Operation.isOperation("" + chars[i - 1]);
            boolean isRightOperation = Operation.isOperation("" + chars[i + 1]);
            if (isCurrentOperation && (isLeftOperation || isRightOperation)) {
                return true;
            }
        }
        return false;
    }
}
