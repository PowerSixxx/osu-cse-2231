import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Baowen Liu
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    /**
     * Test for constructor
     */
    @Test
    public final void testConstructorEmpty() {
        Set<String> actual = this.constructorTest();
        Set<String> expected = this.constructorRef();
        assertEquals(expected, actual);
    }

    /**
     * Test add.
     */
    @Test
    public final void testAddIntoEmpty() {
        Set<String> actual = this.constructorTest();
        Set<String> expected = this.createFromArgsRef("a");
        actual.add("a");
        assertEquals(expected, actual);
    }

    @Test
    public final void testAddNonEmpty() {
        Set<String> s = this.createFromArgsTest("a", "b");
        Set<String> sExpected = this.createFromArgsRef("a", "b", "c");
        s.add("c");
        assertEquals(s, sExpected);
    }

    /**
     * Test remove.
     */
    @Test
    public final void testRemoveNonEmpty() {
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        Set<String> sExpected = this.createFromArgsRef("a", "b");
        s.remove("c");
        assertEquals(s, sExpected);
    }

    /**
     * Test removeAny.
     */
    @Test
    public final void testRemoveAnyFromMulti() {
        Set<String> actual = this.createFromArgsTest("a", "b", "c");
        Set<String> expected = this.createFromArgsRef("a", "b", "c");

        String x = actual.removeAny();
        assertEquals(true, expected.contains(x));
        expected.remove(x);

        assertEquals(expected, actual);
    }

    /**
     * Test Contains.
     */
    @Test
    public final void testContainsTrue() {
        Set<String> s = this.createFromArgsTest("alpha", "beta");
        assertEquals(true, s.contains("alpha"));
    }

    /**
     * Test Size.
     */
    @Test
    public final void testSize() {
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        Set<String> sExpected = this.createFromArgsRef("a", "b", "c");
        assertEquals(3, s.size());
        assertEquals(s, sExpected);
    }
}
