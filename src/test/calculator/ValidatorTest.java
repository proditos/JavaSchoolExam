package calculator;

import calculator.api.Validator;
import calculator.impl.ValidatorImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Vladislav Konovalov
 */
class ValidatorTest {
    private final Validator validator = new ValidatorImpl();

    @ParameterizedTest
    @ValueSource(strings = {"1", "-1", "1.2", "+1.0", "-10/(+2-7+3)*4", "+10/(((-2-7)+3)*4)", "1+1"})
    void itShouldReturnTrueForCorrectExpressions(String expr) {
        assertTrue(validator.isValid(expr));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void itShouldReturnFalseForNullAndEmptyExpressions(String expr) {
        assertFalse(validator.isValid(expr));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+", "*", ".", "(", ")", "()", "*1+2", "1+.2", ".1", "1+2.", "1..2+3",
            "1.23.4+5", "1++2", "(1+2)(3+4)", "1 + 2", "1+2,3", "(1*(2+3)", "1+2+", "1*(2+3))"})
    void isShouldReturnFalseForIncorrectExpressions(String expr) {
        assertFalse(validator.isValid(expr));
    }
}
