import calculator.Calculator;
import org.junit.jupiter.api.Test;
import utils.InputParser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testAddition() {
        assertEquals(8.0, Calculator.calculate(5, 3, "+"), 0.001);
    }


    @Test
    void testDivision() {
        assertEquals(2.5, Calculator.calculate(5, 2, "/"), 0.001);
    }

    @Test
    void testDivisionByZero() {
        assertNull(Calculator.calculate(5, 0, "/"), "Деление на ноль должно возвращать null");
    }

    @Test
    void testHistoryStoresLastTen() {
        int maxSize = 10;
        for (int i = 0; i < 11; i++) {
            String[] parts = {String.valueOf(i), "+", "1"};
            Double result = (double)(i + 1);
            InputParser.addResultToHistory(parts, result, "+", maxSize);
        }

        ArrayList<String> actualHistory = InputParser.getHistory();

        assertEquals(10, actualHistory.size());

        assertTrue(actualHistory.get(0).contains("1 + 1"));

        assertTrue(actualHistory.get(actualHistory.size() - 1).contains("10 + 1"));

    }

    @Test
    void testClearHistory() {
        ArrayList<String> actualHistory = InputParser.getHistory();
        actualHistory.add("1 + 1 = 2.0");
        actualHistory.add("5 * 2 = 10.0");

        String[] parts = {"clear"};

        InputParser.clearHistory(parts);


        assertTrue(actualHistory.isEmpty());
        assertEquals(0, actualHistory.size());
    }
}



