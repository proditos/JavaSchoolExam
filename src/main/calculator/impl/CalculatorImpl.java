package calculator.impl;

import calculator.Operation;
import calculator.api.Calculator;
import calculator.api.Parser;
import calculator.api.Validator;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

/**
 * @author Vladislav Konovalov
 */
public class CalculatorImpl implements Calculator {
    @Override
    public String evaluate(String expr) {
        Validator validator = new ValidatorImpl();
        if (!validator.isValid(expr))
            return null;

        Parser parser = new ParserImpl();
        Iterator<Object> elementsIterator = parser.parse(expr).iterator();
        Deque<Double> numbers = new LinkedList<>();
        Deque<Object> operations = new LinkedList<>();

        while (elementsIterator.hasNext()) {
            Object element = elementsIterator.next();
            if (element instanceof Double) {
                numbers.push((Double) element);
            } else if ("(".equals(element)) {
                operations.push(element);
            } else if (")".equals(element)) {
                while (!"(".equals(operations.peek())) {
                    double number2 = numbers.pop();
                    double number1 = numbers.pop();
                    Operation operation = (Operation) operations.pop();
                    double result = operation.eval(number1, number2);
                    numbers.push(result);
                }
                operations.pop();
            } else {
                if (operations.isEmpty()) {
                    operations.push(element);
                } else if ("(".equals(operations.peek())) {
                    operations.push(element);
                } else {
                    Operation operation = (Operation) element;
                    Operation previousOperation = (Operation) operations.peek();
                    if (operation.getPriority() <= previousOperation.getPriority()) {
                        double number2 = numbers.pop();
                        double number1 = numbers.pop();
                        operations.pop();
                        double result = previousOperation.eval(number1, number2);
                        numbers.push(result);
                    }
                    operations.push(element);
                }
            }
        }

        while (numbers.size() > 1) {
            double number2 = numbers.pop();
            double number1 = numbers.pop();
            Operation operation = (Operation) operations.pop();
            double result = operation.eval(number1, number2);
            numbers.push(result);
        }

        double result = numbers.pop();

        if (Double.isNaN(result) || Double.isInfinite(result))
            return null;

        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));

        return df.format(result);
    }
}
