import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanConverter {

    static int romanToArabic(String romanNum) {
        Map<String, Integer> numsMapper = new HashMap<>() {{
            put("I", 1);
            put("II", 2);
            put("III", 3);
            put("IV", 4);
            put("V", 5);
            put("VI", 6);
            put("VII", 7);
            put("VIII", 8);
            put("IX", 9);
            put("X", 10);
        }};

        return numsMapper.getOrDefault(romanNum.toUpperCase(), -1);
    }

    static String arabicToRoman(int arabicNum) {

        if (arabicNum <= 0) throw new IllegalArgumentException("Римские цифры не могу быть меньше единицы.");
        if (arabicNum > 100)
            throw new IllegalArgumentException("Калькулятор не поддерживает операции с числами больше 100.");

        StringBuilder romanNum = new StringBuilder();
        Map<Integer, String> numsMapper = new HashMap<>() {{
            put(100, "C");
            put(90, "XC");
            put(50, "L");
            put(40, "XL");
            put(10, "X");
            put(9, "IX");
            put(5, "V");
            put(4, "IV");
            put(1, "I");
        }};

        List<Integer> keyList = numsMapper
                .keySet()
                .stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        while (arabicNum > 0) {
            for (int num : keyList) {
                while (arabicNum >= num) {
                    romanNum.append(numsMapper.get(num));
                    arabicNum -= num;
                }
            }
        }
        return romanNum.toString();
    }
}
