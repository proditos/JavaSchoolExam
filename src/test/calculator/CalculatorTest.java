package calculator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vladislav Konovalov
 */
public class CalculatorTest {
    private final Calculator calculator = new CalculatorImpl();

    @Test
    public void evaluate() {
        String input = "2+3";
        String expectedResult = "5";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate1() {
        String input = "4-6";
        String expectedResult = "-2";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate2() {
        String input = "2*3";
        String expectedResult = "6";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate3() {
        String input = "12/3";
        String expectedResult = "4";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate4() {
        String input = "2+3*4";
        String expectedResult = "14";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate5() {
        String input = "10/2-7+3*4";
        String expectedResult = "10";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate6() {
        String input = "10/(2-7+3)*4";
        String expectedResult = "-20";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate7() {
        String input = "22/3*3.0480";
        String expectedResult = "22.352";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate8() {
        String input = "22/4*2.159";
        String expectedResult = "11.8745";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate9() {
        String input = "22/4*2,159";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate10() {
        String input = "- 12)1//(";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate11() {
        String input = "10/(5-5)";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate12() {
        String input = null;
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate13() {
        String input = "(12*(5-1)";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate14() {
        String input = "";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate15() {
        String input = "5+41..1-6";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate16() {
        String input = "5++41-6";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate17() {
        String input = "5--41-6";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate18() {
        String input = "5**41-6";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate19() {
        String input = "5//41-6";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }
}
