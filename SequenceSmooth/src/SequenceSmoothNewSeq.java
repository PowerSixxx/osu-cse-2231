import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Baowen Liu
 *
 */
public final class SequenceSmoothNewSeq {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmoothNewSeq() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>} and return the result.
     *
     * @param s1
     *            the sequence to smooth
     * @return the smoothed sequence
     * @requires |s1| >= 1
     * @ensures <pre>
     * |result| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        result = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        // Other Method
        Sequence<Integer> result = new Sequence1L<>();

        int count = 0, var1 = 0, var2 = 0, avg = 0;
        while (count < s1.length() - 1) {
            var1 = s1.entry(count);
            var2 = s1.entry(count + 1);
            avg = (var1 + var2) / 2;
            result.add(count, avg);
            count++;
        }
        return result;
    }

    // Recursive Method
    /**
     * Return a new {@code Sequence<Integer>} that is the smoothed version of
     * the given sequence {@code s1}.
     *
     * @param s1
     *            the sequence to smooth
     * @return a new sequence containing the smoothed values
     * @requires |s1| >= 1
     */
    public static Sequence<Integer> smooth2(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        Sequence<Integer> result = new Sequence1L<>();
        smoothMain(s1, result);
        return result;
    }

    /**
     * Builds the smoothed sequence by removing the first element of {@code s1},
     * recursing on the remainder, and then inserting the average of the first
     * two elements at the appropriate position in {@code result}.
     *
     * @param s1
     *            the sequence to smooth
     * @param result
     * @requires |s1| >= 1
     * @ensures result contains the averages of all adjacent pairs of elements
     *          from the original {@code s1}, in order
     */
    public static void smoothMain(Sequence<Integer> s1, Sequence<Integer> result) {
        if (s1.length() > 1) {
            int var1 = s1.remove(0);
            int var2 = s1.entry(0);
            smoothMain(s1, result);
            result.add(0, (var1 + var2) / 2);
            s1.add(0, var1);
        }
    }

}
