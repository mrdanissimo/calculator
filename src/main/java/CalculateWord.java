import java.util.HashMap;

public class CalculateWord {
    public static HashMap<String, Integer> wordToNumber = new HashMap<>();
    public static HashMap<String, String> wordToOperator = new HashMap<>();
    public static HashMap<Integer, String> numberToWord = new HashMap<>();

    static {
        addNumber("ноль", 0);
        addNumber("один", 1);
        addNumber("два", 2);
        addNumber("три", 3);
        addNumber("четыре", 4);
        addNumber("пять", 5);
        addNumber("шесть", 6);
        addNumber("семь", 7);
        addNumber("восемь", 8);
        addNumber("девять", 9);

        addNumber("одиннадцать", 11);
        addNumber("двенадцать", 12);
        addNumber("тринадцать", 13);
        addNumber("четырнадцать", 14);
        addNumber("пятнадцать", 15);
        addNumber("шестнадцать", 16);
        addNumber("семнадцать", 17);
        addNumber("восемнадцать", 18);
        addNumber("девятнадцать", 19);

        addNumber("десять", 10);
        addNumber("двадцать", 20);
        addNumber("тридцать", 30);
        addNumber("сорок", 40);
        addNumber("пятьдесят", 50);
        addNumber("шестьдесят", 60);
        addNumber("семьдесят", 70);
        addNumber("восемьдесят", 80);
        addNumber("девяносто", 90);

        addNumber("сто", 100);
        addNumber("двести", 200);
        addNumber("триста", 300);
        addNumber("четыреста", 400);
        addNumber("пятьсот", 500);
        addNumber("шестьсот", 600);
        addNumber("семьсот", 700);
        addNumber("восемьсот", 800);
        addNumber("девятьсот", 900);

        addNumber("тысяча", 1000);

        wordToOperator.put("плюс", "+");
        wordToOperator.put("минус", "-");
        wordToOperator.put("умножить", "*");
        wordToOperator.put("на", "*");
        wordToOperator.put("делить", "/");
        wordToOperator.put("поделить", "/");
    }

    private static void addNumber(String word, int number) {
        wordToNumber.put(word,number);
        numberToWord.put(number, word);
    }

    public static String convertResultToWords(double result) {
        int n = (int) result;
        String res = "";
        if (n == 0) return "ноль";

        if (n < 0){
            res += "минус ";
            n = Math.abs(n);
        }

        if (n > 0) {
            if (numberToWord.containsKey(n)) {
                res += numberToWord.get(n);
            } else {
                int t = (n / 10) * 10;
                int o = n % 10;
                res += numberToWord.get(t) + " " + numberToWord.get(o);
            }
        }

        if (n >= 100) {
            int h = (n / 100) * 100;
            res += numberToWord.get(h) + " ";
            n %= 10;
        }
        return res.trim();
    }

    private static int parseWordNumber(String[] parts, int start, int end) {
        int total = 0;
        for (int i = start; i < end; i++) {
            total += wordToNumber.getOrDefault(parts[i], 0);
        }
        return total;
    }

    public static String[] convertToSymbols(String[] parts) {
        int operatorIndex = -1;
        String symbolOperator = "";

        for (int i = 0; i < parts.length; i++) {
            if (wordToOperator.containsKey(parts[i])) {
                operatorIndex = i;
                symbolOperator = wordToOperator.get(parts[i]);
                break;
            }
        }

        if (operatorIndex == -1) return null; // Оператор не найден

        int num1 = parseWordNumber(parts, 0, operatorIndex);
        int num2 = parseWordNumber(parts, operatorIndex + 1, parts.length);

        return new String[]{String.valueOf(num1), symbolOperator, String.valueOf(num2)};
    }
}
