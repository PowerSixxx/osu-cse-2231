package components.waitingline;

import components.standard.Standard;

/**
 * @author Zhenxiang Ning
 * @author Yuli Peng
 * @author Chen Lou
 * @author Baowen Liu
 */
public interface WaitingLineKernel<T> extends Standard<WaitingLine<T>>, Iterable<T> {

    /**
     * Adds {@code x} to the end of {@code this}.
     *
     * @param x
     *            the entry to be added
     * @aliases reference {@code x}
     * @requires x is NOT in this
     * @updates this
     * @ensures this = #this * <x>
     */
    void addToLine(T x);

    /**
     * Removes and returns the entry at the front of {@code this}.
     *
     * @return the entry removed
     * @updates this
     * @requires this /= <>
     * @ensures #this = <dequeue> * this
     */
    T removeFromLine();

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length();

    /**
     * Reports whether x is in this.
     *
     * @param x
     *            the element to be checked
     * @return true iff element is in this
     * @ensures contains = (x is in this)
     */
    boolean contains(T x);

    /**
     * Removes x from this, and returns it.
     *
     * @param x
     *            the element to be removed
     * @return the element removed
     * @updates this
     * @requires x is in this
     * @ensures <pre>
     * this = a * b where a * <x> * b = #this and
     * remove = x
     * </pre>
     */
    T remove(T x);
}
