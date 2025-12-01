import components.stack.Stack;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author P. Bucci
 */
public final class Sequence {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Sequence() {
        // no code needed here
    }

    /**
     * Shifts entries between {@code leftStack} and {@code rightStack}, keeping
     * reverse of the former concatenated with the latter fixed, and resulting
     * in length of the former equal to {@code newLeftLength}.
     *
     * @param <T>
     *            type of {@code Stack} entries
     * @param leftStack
     *            the left {@code Stack}
     * @param rightStack
     *            the right {@code Stack}
     * @param newLeftLength
     *            desired new length of {@code leftStack}
     * @updates leftStack, rightStack
     * @requires <pre>
     * 0 <= newLeftLength  and
     * newLeftLength <= |leftStack| + |rightStack|
     * </pre>
     * @ensures <pre>
     * rev(leftStack) * rightStack = rev(#leftStack) * #rightStack  and
     * |leftStack| = newLeftLength}
     * </pre>
     */
    private static <T> void setLengthOfLeftStack(Stack<T> leftStack, Stack<T> rightStack,
            int newLeftLength) {
        while (leftStack.length() < newLeftLength) {
            T x = rightStack.pop();
            leftStack.push(x);
        }
        while (leftStack.length() > newLeftLength) {
            T x = leftStack.pop();
            rightStack.push(x);
        }
    }
}
