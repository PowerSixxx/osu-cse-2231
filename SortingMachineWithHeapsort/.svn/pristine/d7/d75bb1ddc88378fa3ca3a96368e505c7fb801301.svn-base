import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Baowen Liu, Chen Lou
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    /**
     * Testing add non empty.
     */
    @Test
    public final void testAddNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a", "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "a", "b",
                "c", "d");
        m.add("d");
        assertEquals(mExpected, m);
    }

    /**
     * Testing add multiple times with non empty elements.
     */
    @Test
    public final void testAddMultiple() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "a", "b",
                "c", "d");
        m.add("b");
        m.add("c");
        m.add("d");
        assertEquals(mExpected, m);
    }

    /**
     * Testing changeToExtractionMode on empty sorting machine and it is in
     * insertion mode.
     */
    @Test
    public final void testChangeToExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Testing changeToExtractionMode on non empty sorting machine and it is in
     * insertion mode.
     */
    @Test
    public final void testChangeToExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a", "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "a", "b",
                "c");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Testing removeFirst() with a one-element.
     */
    @Test
    public final void testRemoveFirstOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "a");
        assertEquals(mExpected.removeFirst(), m.removeFirst());
        assertEquals(mExpected, m);
    }

    /**
     * Testing removeFirst() with a multiple-element.
     */
    @Test
    public final void testRemoveFirstMultipleToZero() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a", "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "a", "b",
                "c");
        assertEquals(mExpected.removeFirst(), m.removeFirst());
        assertEquals(mExpected.removeFirst(), m.removeFirst());
        assertEquals(mExpected.removeFirst(), m.removeFirst());
        assertEquals(mExpected, m);
    }

    /**
     * Testing removeFirst() with a multiple-element.
     */
    @Test
    public final void testRemoveFirstMultiple() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a", "b", "c",
                "d");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "a", "b",
                "c", "d");
        assertEquals(mExpected.removeFirst(), m.removeFirst());
        assertEquals(mExpected, m);
    }

    /**
     * Testing isInInsertionMode() in extractionMode with empty element.
     */
    @Test
    public final void testIsInsertionModeEmptyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        assertEquals(mExpected.isInInsertionMode(), m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Testing isInInsertionMode() in extractionMode and non-empty.
     */
    @Test
    public final void testIsInsertionModeNonEmptyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a", "b", "c",
                "d");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "a", "b",
                "c", "d");
        assertEquals(mExpected.isInInsertionMode(), m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Testing isInInsertionMode() in insertionMode and empty.
     */
    @Test
    public final void testIsInsertionModeEmptyTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        assertEquals(mExpected.isInInsertionMode(), m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Testing isInInsertionMode() in insertionMode and non-empty.
     */
    @Test
    public final void testIsInsertionModeNonEmptyTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a", "b", "c",
                "d");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "a", "b",
                "c", "d");
        assertEquals(mExpected.isInInsertionMode(), m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Testing comparator in insertionMode and empty.
     */
    @Test
    public final void testOrderEmptyTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Testing comparator in insertionMode and non empty.
     */
    @Test
    public final void testOrderNonEmptyTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a", "b", "c",
                "d");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "a", "b",
                "c", "d");
        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Testing Testing comparator in extraction mode and empty.
     */
    @Test
    public final void testOrderEmptyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Testing Testing comparator in extraction mode and non empty.
     */
    @Test
    public final void testOrderNonEmptyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a", "b", "c",
                "d");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "a", "b",
                "c", "d");
        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Testing Size() with zero size and in insertionMode.
     */
    @Test
    public final void testSizeEmptyTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);
    }

    /**
     * Testing Size() with zero size and in extractionMode.
     */
    @Test
    public final void testSizeEmptyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);
    }

    /**
     * Testing Size() with non-empty size and in insertionMode.
     */
    @Test
    public final void testSizeNonEmptyTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a", "b", "c",
                "d");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true, "a", "b",
                "c", "d");
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);
    }

    /**
     * Testing Size() with non-empty size and in extractionMode.
     */
    @Test
    public final void testSizeNonEmptyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a", "b", "c",
                "d");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "a", "b",
                "c", "d");
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);
    }
}
