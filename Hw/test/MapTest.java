import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Baowen Liu
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i])
                    : "" + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i])
                    : "" + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size

    /**
     * test constructor.
     */
    @Test
    public final void testConstructor() {
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();
        assertEquals(s, sExpected);

    }

    /**
     * Test for add non-Empty.
     */
    @Test
    public final void testAddNonEmpty() {
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> sExpected = this.createFromArgsRef("a", "b", "c", "d", "e",
                "f");
        s.add("e", "f");
        assertEquals(s, sExpected);
    }

    /**
     * Test for Remove.
     */
    @Test
    public final void testRemove() {
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> sExpected = this.createFromArgsRef("c", "d");

        s.remove("a");
        assertEquals(s, sExpected);
        assertTrue(!s.hasKey("a"));

    }

    /**
     * Test for Remove-any.
     */
    @Test
    public final void testRemoveAny() {
        Map<String, String> actual = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3");
        Map.Pair<String, String> removed = actual.removeAny();
        String rk = removed.key();
        String rv = removed.value();
        assertTrue(!actual.hasKey(rk));

        Map<String, String> ref = this.createFromArgsRef("a", "1", "b", "2", "c", "3");
        ref.remove(rk);
        assertEquals(ref.size(), actual.size());
        assertEquals(ref, actual);
    }

    /**
     * Test for Value.
     */
    @Test
    public final void testValue() {
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d");
        String test = s.value("a");
        String test2 = s.value("c");
        assertTrue(test.equals("b") && test2.equals("d"));
    }

    /**
     * Test for Has-key.
     */
    @Test
    public final void testHasKey() {
        Map<String, String> s = this.createFromArgsTest("a", "1", "b", "2", "c", "3");
        assertTrue(s.hasKey("a") && s.hasKey("b") && s.hasKey("c"));
    }

    /**
     * Test for Size.
     */
    @Test
    public final void testSize() {
        Map<String, String> s = this.createFromArgsTest("a", "1", "b", "2", "c", "3");
        assertEquals(3, s.size());
    }

}
