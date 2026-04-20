import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        try(Scanner input = new Scanner(System.in)) {
            ArrayList<String> history = new ArrayList<>();
            while (true) {
//              Ввод данных пользователем
                System.out.print("Введите выражение (число - оператор - число): ");
                String expression = input.nextLine();

//              Разбиение строки на части
                String[] parts = expression.trim().toLowerCase().split("\\s+");

//              Команды
                if (InputParser.exitProgram(parts)) break;
                if (InputParser.showHelp(parts)) continue;
                if (InputParser.showHistory(parts, history)) continue;
                if (InputParser.showLast(parts, history)) continue;
                if (InputParser.clearHistory(parts, history)) continue;

                String[] convertedParts = CalculateWord.convertToSymbols(parts);
                boolean isWordMode = false;
                if (convertedParts != null) {
                    parts = convertedParts;
                    isWordMode = true;
                }

//              Проверка длины
                if (parts.length != 3){
                    System.out.println("Oшибка!");
                    continue;
                }

//              Преобразование 1 и 3 элемента в double и проверка на правильность ввода
                double[] numbers = InputParser.parseNumbers(parts);
                if (numbers == null) continue; // неверный ввод, продолжаем цикл
                double num1 = numbers[0];
                double num2 = numbers[1];
                String operator = parts[1];

//              Проеврка на ноль, правильность ввода оператора и выполнение мат действий
                Double result = Calculator.Calculate(num1, num2, operator);
                if (result != null) {
                    if (isWordMode) {
                        // Если вводили словами — выводим словами
                        String textResult = CalculateWord.convertResultToWords(result);
                        System.out.println("Результат: " + textResult);
                    } else {
                        // Если вводили цифрами — выводим цифры
                        System.out.println("Результат: " + result);
                    }

                    // Сохранение в историю
                    InputParser.addResultToHistory(history, parts, result, operator, 10);
                }
            }
        }
    }
}