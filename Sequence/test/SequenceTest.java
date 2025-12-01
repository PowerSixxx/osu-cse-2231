import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Baowen Liu
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length

    /**
     * Test for constructor
     */
    @Test
    public void testConstructor() {
        Sequence<String> sequence = this.constructorTest();
        Sequence<String> expected = this.constructorRef();
        assertEquals(sequence, expected);
    }

    /**
     * Test for add
     */
    @Test
    public void testAddRegular() {
        Sequence<String> s = this.createFromArgsTest("b", "c");
        Sequence<String> sExpected = this.createFromArgsRef("a", "b", "c");
        s.add(0, "a");
        assertEquals(s, sExpected);
    }

    @Test
    public void testAddForEmpty() {
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef("a");
        s.add(0, "a");
        assertEquals(s, sExpected);
    }

    @Test
    public void testAddForMultipleTime() {
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef("a", "b", "c");
        s.add(0, "a");
        s.add(1, "b");
        s.add(2, "c");
        assertEquals(s, sExpected);
    }

    /**
     * Test for remove
     */
    @Test
    public void testRemove() {
        Sequence<String> s = this.createFromArgsTest("a", "b", "c");
        Sequence<String> sExpected = this.createFromArgsRef("b", "c");
        String x = s.remove(0);
        assertEquals(s, sExpected);
        assertEquals(x, "a");
    }

    /**
     * Test for length
     */
    @Test
    public final void testLengthEmpty() {
        Sequence<String> q = this.createFromArgsTest();
        Sequence<String> qExpected = this.createFromArgsRef();
        int i = q.length();
        assertEquals(qExpected, q);
        assertEquals(0, i);
    }

    /**
     * test for length not empty.
     */
    @Test
    public final void testLengthOne() {
        Sequence<String> q = this.createFromArgsTest("hello");
        Sequence<String> qExpected = this.createFromArgsRef("hello");
        int i = q.length();
        assertEquals(qExpected, q);
        assertEquals(1, i);
    }

    /**
     * test for length more than one value.
     */
    @Test
    public final void testLengthMoreThanOne() {
        Sequence<String> q = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> qExpected = this.createFromArgsRef("red", "green", "blue");
        int i = q.length();
        assertEquals(qExpected, q);
        assertEquals(3, i);
    }
}
