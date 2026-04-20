import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testAddition() {
        assertEquals(8.0, Calculator.Calculate(5, 3, "+"), 0.001);
    }


    @Test
    void testDivision() {
        assertEquals(2.5, Calculator.Calculate(5, 2, "/"), 0.001);
    }

    @Test
    void testDivisionByZero() {
        assertNull(Calculator.Calculate(5, 0, "/"), "Деление на ноль должно возвращать null");
    }

    @Test
    void testHistoryStoresLastTen() {
        ArrayList<String> history = new ArrayList<>();
        int maxSize = 10;
        for (int i = 0; i < 11; i++) {
            String[] parts = {String.valueOf(i), "+", "1"};
            Double result = (double)(i + 1);
            InputParser.addResultToHistory(history, parts, result, "+", maxSize);
        }

        assertEquals(10, history.size());

        assertTrue(history.get(0).contains("1 + 1"));

        assertTrue(history.get(history.size() - 1).contains("10 + 1"));

    }

    @Test
    void testClearHistory() {
        ArrayList<String> history = new ArrayList<>();
        history.add("1 + 1 = 2.0");
        history.add("5 * 2 = 10.0");

        String[] parts = {"clear"};

        InputParser.clearHistory(parts, history);

        assertTrue(history.isEmpty());
        assertEquals(0, history.size());
    }
}



