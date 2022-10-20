package calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * @author Vladislav Konovalov
 */
public class CalculatorImpl implements Calculator {
    @Override
    public String evaluate(String expr) {
        if (expr == null) return null;

        expr = expr.replaceAll("^-", "0-");
        expr = expr.replaceAll("^\\+", "0+");
        expr = expr.replace("(-", "(0-");
        expr = expr.replace("(+", "(0+");

        Deque<String> operationStack = new LinkedList<>();
        Deque<String> numberStack = new LinkedList<>();
        Iterator<String> iterator = new StringIterator(expr);

        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element == null) return null;

            if (isNumber(element))
                numberStack.push(element);
            if (isOperation(element)) {
                if (element.equals("(")) {
                    operationStack.push(element);
                } else if (element.equals(")")) {
                    if (!"(".equals(operationStack.peek()))
                        while (!"(".equals(operationStack.peek())) {
                            try {
                                double number2 = Double.parseDouble(numberStack.pop());
                                double number1 = Double.parseDouble(numberStack.pop());
                                double result = processOperation(number1, number2, operationStack.pop());
                                if (Double.isNaN(result)) return null;
                                numberStack.push(result + "");
                            } catch (NoSuchElementException e) {
                                return null;
                            }
                        }
                    operationStack.pop();
                } else if (operationStack.isEmpty() || getOperationPrecedence(operationStack.peek()) < getOperationPrecedence(element)) {
                    operationStack.push(element);
                } else {
                    try {
                        double number2 = Double.parseDouble(numberStack.pop());
                        double number1 = Double.parseDouble(numberStack.pop());
                        double result = processOperation(number1, number2, operationStack.pop());
                        if (Double.isNaN(result)) return null;
                        numberStack.push(result + "");
                    } catch (NoSuchElementException e) {
                        return null;
                    }
                    operationStack.push(element);
                }
            }
        }

        if (numberStack.isEmpty()) return null;

        while (numberStack.size() > 1) {
            try {
                double number2 = Double.parseDouble(numberStack.pop());
                double number1 = Double.parseDouble(numberStack.pop());
                double result = processOperation(number1, number2, operationStack.pop());
                if (Double.isNaN(result)) return null;
                numberStack.push(result + "");
            } catch (NoSuchElementException e) {
                return null;
            }
        }

        if (!operationStack.isEmpty()) return null;

        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));

        return df.format(Double.parseDouble(numberStack.pop()));
    }

    private boolean isNumber(String element) {
        if (element == null) return false;
        return element.substring(0, 1).matches("\\d");
    }

    private boolean isOperation(String element) {
        if (element == null) return false;
        return element.substring(0, 1).matches("[+\\-*/()]");
    }

    private int getOperationPrecedence(String operation) {
        switch (operation) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            default:
                return -1;
        }
    }

    private double processOperation(double number1, double number2, String operation) {
        switch (operation) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                if (number2 == 0.0) return Double.NaN;
                return number1 / number2;
            default:
                return Double.NaN;
        }
    }
}
