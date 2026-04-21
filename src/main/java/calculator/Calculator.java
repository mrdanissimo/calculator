package calculator;

public class Calculator {
    public static Double calculate (double num1, double num2, String operator) {
        double result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2; return result;
            case "-":
                result = num1 - num2; return result;
            case "*":
                result = num1 * num2; return result;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                    return result;
                } else {
                    System.out.println("Ошибка: деление на ноль");
                    return null;
                }
            default:
                System.out.println("Ошибка: неизвестный оператор");
                return null;
        }
    }
}
