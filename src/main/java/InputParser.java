import java.util.Objects;
import java.util.ArrayList;

public class InputParser {
    public static boolean showHelp(String[] parts) {
        if (Objects.equals(parts[0], "help")) {
            System.out.println("Доступные команды:");
            System.out.println();
            System.out.println("<число> <оператор> <число>");
            System.out.println();
            System.out.println("Операторы:\n+ сложение\n- вычитание\n* умножение\n/ деление");
            System.out.println();
            System.out.println("Дополнительные команды:\nhistory\nexit\nhelp");
            return true;
        } return false;
    }

    public static boolean exitProgram(String[] parts) {
        if (Objects.equals(parts[0], "exit")) {
            System.out.println("Выход из программы");
            return true;
        }
        return false;
    }

    public static boolean showHistory(String[] parts, ArrayList<String> history) {
        if (Objects.equals(parts[0], "history")) {
            if (history.isEmpty()) {
                System.out.println("История пуста");
            } else {
                for (int i = 0; i < history.size(); i++) {
                    System.out.println((i + 1) + ") " + history.get(i));
                }
            } return true;
        } return false;
    }

    public static boolean showLast(String[] parts, ArrayList<String> history) {
        if (Objects.equals(parts[0], "last")) {
            if (history.isEmpty()) {
                System.out.println("История пуста");
            } else {
                String lastRecord = history.get(history.size() - 1);;
                String[] partsLast = lastRecord.split(" = ");
                System.out.println("Результат: " + partsLast[1]);
            } return true;
        } return false;
    }

    public static boolean clearHistory(String[] parts, ArrayList<String> history) {
        if (Objects.equals(parts[0], "clear")) {
            if (history.isEmpty()) {
                System.out.println("История пуста");
            } else {
                history.clear();
                System.out.println("История очищена");
            } return true;
        } return false;
    }

    public static double[] parseNumbers(String[] parts) {
        if (parts.length != 3) return null;

        try {
            double num1 = Double.parseDouble(parts[0]);
            double num2 = Double.parseDouble(parts[2]);
            return new double[]{num1, num2};
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: нужно вводить числа!");
            return null;
        }
    }

    public static void addResultToHistory(ArrayList<String> history,String[] parts, Double result, String operator, int maxSize) {
        String record = parts[0] + " " + operator + " " + parts[2] + " = " + result;

        if (history.size() >= maxSize) {
            history.remove(0); // удаляем самый старый элемент
        }
        history.add(record);
    }
}