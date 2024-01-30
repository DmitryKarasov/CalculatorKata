import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Calculator {

    private Calculator() {
    }

    public static void run() {

        String regexArabic = "(?:[1-9]|10)[-+*/](?:[1-9]|10)";
        String regexRoman = "(?:I{1,3}[VX]?|[VX]|VI{1,3})[-+*/](?:I{1,3}[VX]?|[VX]|VI{1,3})";

        Scanner sc = new Scanner(System.in);
        System.out.println("Калькулятор запущен.");

        while (true) {

            System.out.print("Введите арифметическое выражение между двумя числами ли введите 'end' для завершения работы: ");
            String userInput = sc.nextLine().replaceAll(" ", "");

            if (userInput.equalsIgnoreCase("end")) break;

            if (Pattern.matches(regexArabic, userInput) || Pattern.matches(regexRoman, userInput)) {
                String operator = findOperation(userInput);

                String[] nums;

                if (operator != null) {
                    nums = userInput.split(operator);
                } else {
                    throw new IllegalArgumentException("Выражение не соответствует формату.");
                }

                if (Pattern.matches(regexRoman, userInput)) {
                    int res = doExpression(
                            RomanConverter.romanToArabic(nums[0]),
                            RomanConverter.romanToArabic(nums[1]),
                            operator
                    );
                    System.out.println(RomanConverter.arabicToRoman(res));
                } else {
                    System.out.println(
                            doExpression(
                                    Integer.parseInt(nums[0]),
                                    Integer.parseInt(nums[1]),
                                    operator
                            )
                    );
                }
            } else {
                throw new IllegalArgumentException("Выражение не соответствует формату.");
            }
        }
        System.out.println("Работа калькулятора завершена.");
    }

    protected static String findOperation(String expression) {
        List<String> operations = List.of("+", "-", "*", "/");
        String operation = "0";

        for (String sym : operations) {
            if (expression.contains(sym)) {
                operation = sym;
                break;
            }
        }
        if (operation.equals("0")) return null;

        return operation.equals("-") || operation.equals("/") ? operation : "\\" + operation;
    }

    protected static int doExpression(int num1, int num2, String operation) {
        return switch (operation) {
            case "-" -> num1 - num2;
            case "\\+" -> num1 + num2;
            case "\\*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> -1;
        };
    }
}
