package calculator.impl;

import calculator.Operation;
import calculator.api.Parser;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Vladislav Konovalov
 */
public class ParserImpl implements Parser {
    @Override
    public Queue<Object> parse(String expr) {
        Queue<Object> result = new LinkedList<>();
        expr = addZeros(expr);
        StringBuilder editableExpr = new StringBuilder(expr);
        while (editableExpr.length() > 0) {
            char element = editableExpr.charAt(0);
            if (Operation.isOperation("" + element)) {
                result.add(Operation.getBySign("" + element));
                editableExpr.deleteCharAt(0);
            } else if (Character.isDigit(element)) {
                int numberLength = getNumberLength(editableExpr);
                String number = editableExpr.substring(0, numberLength);
                result.add(Double.parseDouble(number));
                editableExpr.delete(0, numberLength);
            } else {
                result.add("" + element);
                editableExpr.deleteCharAt(0);
            }
        }
        return result;
    }

    private int getNumberLength(StringBuilder expr) {
        for (int i = 1; i < expr.length(); i++) {
            char nextChar = expr.charAt(i);
            if (!(Character.isDigit(nextChar) || nextChar == '.')) {
                return i;
            }
        }
        return expr.length();
    }

    private String addZeros(String expr) {
        expr = expr.replace("(-", "(0-");
        expr = expr.replace("(+", "(0+");
        StringBuilder editableExpr = new StringBuilder(expr);
        if (editableExpr.charAt(0) == '-')
            editableExpr.replace(0, 1, "0-");
        else if (editableExpr.charAt(0) == '+')
            editableExpr.replace(0, 1, "0+");
        return editableExpr.toString();
    }
}
