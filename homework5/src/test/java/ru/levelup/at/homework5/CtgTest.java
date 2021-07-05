package ru.levelup.at.homework5;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CtgTest extends BaseTest {
    @DataProvider
    private Object[][] getDoubleArgs() {
        return new Object[][]{
            {Math.PI / 2, 0.0}, {-Math.PI / 2, 0.0}, {Math.PI / 4, 1.0}, {-Math.PI / 4, -1.0},
            {0.3, 3.232728}, {-0.1, -9.9666444}
        };
    }

    @Test(dataProvider = "getDoubleArgs")
    public void testCtgPositiveCases(double a, double expectedResult) {
        assertEquals(calculator.ctg(a), expectedResult, doubleComparisonDelta);
    }
}
