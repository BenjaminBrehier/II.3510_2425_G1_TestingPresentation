package com.bm.testing

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4.0, evaluateExpression("2 + 2"), 0.0)
        assertEquals(22.0, evaluateExpression("20 + 2"), 0.0)
        assertEquals(100.0, evaluateExpression("1 + 99"), 0.0)
        assertEquals(75.0, evaluateExpression("1 + 72 + 2"), 0.0)
    }

    @Test
    fun subtraction_isCorrect() {
        assertEquals(0.0, evaluateExpression("1 - 1"), 0.0)
        assertEquals(19.0, evaluateExpression("20 - 1"), 0.0)
        assertEquals(99.0, evaluateExpression("100 - 1"), 0.0)
        assertEquals(-10.0, evaluateExpression("10 - 20"), 0.0)
    }

    @Test
    fun multiplication_isCorrect() {
        assertEquals(1.0, evaluateExpression("1 * 1"), 0.0)
        assertEquals(20.0, evaluateExpression("4 * 5"), 0.0)
        assertEquals(40.0, evaluateExpression("4 * 5 * 2"), 0.0)
        assertEquals(-40.0, evaluateExpression("4 * 5 * -2"), 0.0)
    }

    @Test
    fun divide_isCorrect() {
        assertEquals(1.0, evaluateExpression("1 / 1"), 0.0)
        assertEquals(5.0, evaluateExpression("15 / 3"), 0.0)
        assertEquals(100.0, evaluateExpression("200 / 2"), 0.0)
    }
}