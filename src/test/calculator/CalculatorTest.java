package calculator;

import calculator.api.Calculator;
import calculator.impl.CalculatorImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Vladislav Konovalov
 */
class CalculatorTest {
    private final Calculator calculator = new CalculatorImpl();

    @ParameterizedTest
    @CsvSource(value = {"2+3;5", "+2*3;6", "-5+3;-2", "4-(6);-2", "12/(+3);4", "10/2-7+3*4;10",
            "10/(2-7+3)*4;-20", "22/3*3.0480;22.352", "22/4*2.113573;11.6247",
            "-22/4*2.113573;-11.6247", "(2+3)*(4+5);45", "2*(-3+15/3);4", "-(2+3);-5", "1;1",
            "-1;-1", "+1;1", "1.0;1"}, delimiter = ';')
    void itShouldReturnCorrectResultOfExpression(String expr, String expected) {
        String result = calculator.evaluate(expr);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1/0", "-1/0", "0/(-0)"})
    void divideByZeroShouldReturnNull(String expr) {
        String result = calculator.evaluate(expr);
        assertNull(result);
    }
}
