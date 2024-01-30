import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "1 + 1, \\+",
            "1 - 1, -",
            "1 * 1, \\*",
            "1 / 1, /"
    })
    void findOperationTest(String expression, String expected) {
        String actual = Calculator.findOperation(expression);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findOperationTestNull() {
        String actual = Calculator.findOperation("1 1");
        Assertions.assertNull(actual);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, \\+, 2",
            "1, 1, -, 0",
            "5, 2, \\*, 10",
            "5, 2, /, 2",
            "5, 2, 0, -1",
    })
    void doExpressionTest(int num1, int num2, String operation, int expected) {
        int actual = Calculator.doExpression(num1, num2, operation);
        Assertions.assertEquals(expected, actual);
    }
}