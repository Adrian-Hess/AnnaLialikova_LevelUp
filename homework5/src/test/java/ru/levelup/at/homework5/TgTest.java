package ru.levelup.at.homework5;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

import java.util.stream.IntStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TgTest extends BaseTest {

    @DataProvider
    private Object[][] getDoubleArgs() {
        return new Object[][]{
            {0.0, 0.0}, {Math.PI, 0.0}, {Math.PI / 4, 1.0}, {-Math.PI / 4, -1.0}, {1.2, 2.572151622}, {-4.5, -4.637332}
        };
    }

    @Test(dataProvider = "getDoubleArgs")
    public void testTgPositiveCases(double a, double expectedResult) {
        assertEquals(calculator.tg(a), expectedResult, doubleComparisonDelta);
    }
}
