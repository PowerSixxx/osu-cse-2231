import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

public abstract class Hw18Test {

    protected abstract Stack<String> constructorTest();

    protected abstract Stack<String> constructorRef();

    /*
     * Constructor Test --------------------------------------------------
     */

    /**
     * Tests Default Constructor.
     */
    @Test
    public final void testDefaultConstructor() {
        Stack<String> s = this.constructorTest();
        Stack<String> sExpected = this.constructorRef();

        assertEquals(sExpected, s);
    }

    /*
     * Push Tests --------------------------------------------------
     */

    /**
     * Tests Push.
     */
    @Test
    public final void testPushFromEmpty() {
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("Apple");

        s.push("Apple");

        assertEquals(sExpected, s);
    }

    /**
     * Tests Push-non empty.
     */
    @Test
    public final void testPushFromNonEmpty() {
        Stack<String> s = this.createFromArgsTest("Banana");
        Stack<String> sExpected = this.createFromArgsRef("Apple", "Banana");

        s.push("Apple");

        assertEquals(sExpected, s);
    }
    /*
     * Pop Tests --------------------------------------------------
     */

    /**
     * Tests Pop.
     */
    @Test
    public final void testPopToEmpty() {
        Stack<String> s = this.createFromArgsTest("Apple");
        Stack<String> sExpected = this.createFromArgsRef();

        String ans = s.pop();

        assertEquals(sExpected, s);
        assertEquals("Apple", ans);
    }

    /**
     * Tests Pop-non empty.
     */
    @Test
    public final void testPopToNonEmpty() {
        Stack<String> s = this.createFromArgsTest("Apple", "Banana");
        Stack<String> sExpected = this.createFromArgsRef("Banana");

        String ans = s.pop();

        assertEquals(sExpected, s);
        assertEquals("Apple", ans);
    }
    /*
     * Length Tests --------------------------------------------------
     */

    /**
     * Tests Length.
     */
    @Test
    public final void testLength() {
        Stack<String> s = this.createFromArgsTest("Apple");

        int length = s.length();

        assertEquals(1, length);
    }
}
