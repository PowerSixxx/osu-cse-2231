import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Baowen Liu, Chen Lou
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /**
     * Test empty constructor.
     */
    @Test
    public void testConstructorEmpty() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        // test Constructor with empty input.
        assertEquals(n, nExpected);
    }

    /**
     * test max value integer.
     */
    @Test
    public void testConstructorMaxNumber() {
        int max = Integer.MAX_VALUE;
        NaturalNumber nTest = this.constructorTest(max);
        NaturalNumber nRef = this.constructorRef(max);
        // test Constructor with Max Value Integer Number.
        assertEquals(nRef, nTest);
    }

    /**
     * Test positive integer constructor.
     */
    @Test
    public void testConstructorPosInt() {
        int i = 3;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        // test Constructor with integer.
        assertEquals(n, nExpected);
    }

    /**
     * Test integer zero constructor.
     */
    @Test
    public void testConstructorZero() {
        int i = 0;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        // test Constructor with Integer zero.
        assertEquals(n, nExpected);
    }

    /**
     * test with string input.
     */
    @Test
    public void testConstructorStringZero() {
        NaturalNumber nTest = this.constructorTest("0");
        NaturalNumber nRef = this.constructorRef("0");
        // test Constructor with String of Zero.
        assertEquals(nRef, nTest);
    }

    /**
     * Test constructor with string.
     */
    @Test
    public void testConstructorString() {
        String s = "123";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        // test Constructor with String input.
        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with NaturalNumber built from string.
     */
    @Test
    public void testConstructornaturalnumber() {
        NaturalNumber s = this.constructorTest("123");
        NaturalNumber n = this.constructorTest(s);
        // test Constructor with NaturalNumber constructed with String.
        NaturalNumber nExpected = this.constructorRef(s);
        assertEquals(n, nExpected);
    }

    /**
     * test with NaturalNumber Zero built from integer.
     */
    @Test
    public void testConstructorNaturalNumberZero() {
        NaturalNumber zero = this.constructorTest(0);
        NaturalNumber nTest = this.constructorTest(zero);
        NaturalNumber nRef = this.constructorRef(zero);
        // test Constructor with NaturalNumber input
        assertEquals(nRef, nTest);
    }

    // kernel testing start from here//
    // kernel testing start from here//
    // kernel testing start from here//
    /**
     * Test multiplyBy10 with empty.
     */
    @Test
    public void testMultiplyBy10Empty() {
        final int i = 3;
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(i);
        // test MultiplyBy10 with Zero and integer input.
        n.multiplyBy10(i);
        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10 with i = 0 and k = 0.
     */
    @Test
    public void testMultiplyBy10EmptyAndEmpty() {
        final int i = 0;
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(i);
        // test MultiplyBy10 with zero and empty input.
        n.multiplyBy10(i);
        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10 routine.
     */
    @Test
    public void testMultiplyBy10Routine() {
        final int i = 5;
        NaturalNumber n = this.constructorTest(9);
        NaturalNumber nExpected = this.constructorRef(95);
        // test MultiplyBy10 with integer input.
        n.multiplyBy10(i);
        assertEquals(n, nExpected);
    }

    /**
     * test DivideBy10 with base zero.
     */
    @Test
    public void testDivideBy10FromZero() {
        NaturalNumber nTest = this.constructorTest(0);
        NaturalNumber nRef = this.constructorRef(0);
        // test DivideBy10 with only zero input.
        int removednTest = nTest.divideBy10();
        int removednRef = 0;
        assertEquals(removednRef, removednTest);
        assertEquals(nRef, nTest);
    }

    /**
     * test DivideBy10 with MultiDigit number.
     */
    @Test
    public void testDivideBy10MultiDigit() {
        NaturalNumber nTest = this.constructorTest(123456789);
        NaturalNumber nRef = this.constructorRef(12345678);
        // test DivideBy10 with more than one digit input.
        int removednTest = nTest.divideBy10();
        int removednRef = 9;
        assertEquals(removednRef, removednTest);
        assertEquals(nRef, nTest);
    }

    /**
     * test DivideBy10 with singleiDigit number.
     */
    @Test
    public void testDivideBy10singleiDigit() {
        NaturalNumber nTest = this.constructorTest(9);
        NaturalNumber nRef = this.constructorRef(0);
        // test DivideBy10 with single digit input.
        int removednTest = nTest.divideBy10();
        int removednRef = 9;
        assertEquals(removednRef, removednTest);
        assertEquals(nRef, nTest);
    }

    /**
     * Test IsZero when false.
     */
    @Test
    public void testIsZeroFalse1() {
        NaturalNumber nTest = this.constructorTest(9);
        NaturalNumber nExpected = this.constructorTest(9);
        // if is not equal to zero return false.
        assertTrue(!nTest.isZero());
        assertEquals(nTest, nExpected);
    }

    @Test
    public void testIsZeroFalse2() {
        NaturalNumber nTest = this.constructorTest(1234);
        NaturalNumber nExpected = this.constructorTest(1234);
        // if is not equal to zero return false.
        assertTrue(!nTest.isZero());
        assertEquals(nTest, nExpected);
    }

    /**
     * Test IsZero when true.
     */
    @Test
    public void testIsZeroTrue() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        // if is equal to zero return true.
        assertEquals(n, nExpected);
        assertTrue(n.isZero());
        assertTrue(nExpected.isZero());
    }
}
