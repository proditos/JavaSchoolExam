package calculator.impl;

import calculator.api.Parser;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Vladislav Konovalov
 */
public class ParserImpl implements Parser {
    @Override
    public Queue<Object> parse(String expr) {
        return new LinkedList<>();
    }
}
