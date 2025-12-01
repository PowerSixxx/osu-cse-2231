package components.waitingline;

/**
 * @author Zhenxiang Ning
 * @author Yuli Peng
 * @author Chen Lou
 * @author Baowen Liu
 */
public interface WaitingLine<T> extends WaitingLineKernel<T> {

    /**
     * Reports the front of {@code this}.
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    T front();

    /**
     * Concatenates ("appends") {@code q} to the end of {@code this}.
     *
     * @param q
     *            the {@code Queue} to be appended to the end of {@code this}
     * @updates this
     * @clears q
     * @ensures this = #this * #q
     */
    void append(WaitingLine<T> q);

    /**
     * Report the position of x
     *
     * @param x
     *            The element to find
     * @return The position of the element
     * @requires x is in this
     * @ensures this[pos] = x
     */
    int pos(T x);

    /**
     * Insert an element at the front of this
     *
     * @param x
     *            the entry to be added
     * @requires x is NOT in this
     * @ensures this = <x> * #this
     */
    void insertAtFront(T x);

    /**
     * Move an element to the front of the line
     *
     * @param x
     *            the element to move
     * @requires x is in this
     * @ensures this = <x> * a * b where a * <x> * b = #this
     */
    void moveToFront(T x);
}
