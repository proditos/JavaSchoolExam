package calculator.api;

import java.util.Queue;

/**
 * @author Vladislav Konovalov
 */
public interface Parser {
    Queue<Object> parse(String expr);
}
