import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RecursionPracticeTest {

    /**
     * Test factorial 0.
     */
    @Test
    public void testFactorialZero() {
        int result = RecursionPractice.factorial(0);
        assertEquals(1, result);
    }

    /**
     * Test factorial 1.
     */
    @Test
    public void testFactorialOne() {
        int result = RecursionPractice.factorial(1);
        assertEquals(1, result);
    }

    /**
     * Test factorial 5.
     */
    @Test
    public void testFactorialFive() {
        int result = RecursionPractice.factorial(5);
        assertEquals(120, result);
    }

    /**
     * Test factorial 7.
     */
    @Test
    public void testFactorialSeven() {
        int result = RecursionPractice.factorial(7);
        assertEquals(5040, result);
    }

    /**
     * Test reverse with empty.
     */
    @Test
    public void testReverseEmpty() {
        String s = RecursionPractice.reverse("");
        assertEquals("", s);
    }

    /**
     * Test reverse with single char.
     */
    @Test
    public void testReverseOne() {
        String s = RecursionPractice.reverse("a");
        assertEquals("a", s);
    }

    /**
     * Test reverse with multiple.
     */
    @Test
    public void testReverseMultiple() {
        String s = RecursionPractice.reverse("hello");
        assertEquals("olleh", s);
    }

    /**
     * Test fib(0).
     */
    @Test
    public void testFibZero() {
        int result = RecursionPractice.fib(0);
        assertEquals(0, result);
    }

    /**
     * Test fib(1).
     */
    @Test
    public void testFibOne() {
        int result = RecursionPractice.fib(1);
        assertEquals(1, result);
    }

    /**
     * Test fib(2).
     */
    @Test
    public void testFibTwo() {
        int result = RecursionPractice.fib(2);
        assertEquals(1, result); // 0,1,1
    }

    /**
     * Test fib(5).
     */
    @Test
    public void testFibFive() {
        int result = RecursionPractice.fib(5);
        assertEquals(5, result); // 0,1,1,2,3,5
    }

    /**
     * Test fib(15).
     */
    @Test
    public void testFibFifteen() {
        int result = RecursionPractice.fib(15);
        assertEquals(610, result);
    }
}
