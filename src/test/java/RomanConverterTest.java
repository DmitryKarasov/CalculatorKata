import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RomanConverterTest {

    @ParameterizedTest
    @CsvSource({
            "I, 1",
            "v, 5",
            "XI, -1"
    })
    void romanToArabicTest(String romanNum, int expected) {
        int actual = RomanConverter.romanToArabic(romanNum);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "1, I",
            "100, C",
            "99, XCIX",
            "51, LI",
    })
    void arabicToRomanTest(int arabicNum, String expected) {
        String actual = RomanConverter.arabicToRoman(arabicNum);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void arabicToRomanExceptionTest1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanConverter.arabicToRoman(0);
        });

        String expectedMessage = "Римские цифры не могу быть меньше единицы.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void arabicToRomanExceptionTest2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanConverter.arabicToRoman(101);
        });

        String expectedMessage = "Калькулятор не поддерживает операции с числами больше 100.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}