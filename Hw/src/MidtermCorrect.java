import components.naturalnumber.NaturalNumberSecondary;

/**
 * NaturalNumber represented as a Sequence<Integer>, with implementations of
 * primary methods.
 *
 * @convention |this.digits| > 0 and (this.digits = <0> or (<0> is not postfix
 *             of $this.digits)) and entries($this.digits) is subset of
 *             {0,1,2,3,4,5,6,7,8,9}
 *
 * @correspondence this = [the natural number obtained by "concatenating" the
 *                 values in $this.digits, from left to right, then reversing
 *                 the result.]
 */
public class NaturalNumber21 extends NaturalNumberSecondary {

    /**
     * Representation of {@code this}.
     */
    private Sequence<Integer> digits;

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";

        if (this.digits.length() == 1 && this.digits.entry(0) == 0) {
            this.digits.clear();
        }
        this.digits.add(0, k);
    }

    @Override
    public final int divideBy10() {
        int ans;
        if (this.digits.length() == 1) {
            ans = this.digits.remove(0);
            this.digits.add(0, 0);
        } else {
            ans = this.digits.remove(0);
        }
        return ans;
    }

    @Override
    public final boolean isZero() {
        return this.digits.length() == 1 && this.digits.entry(0) == 0;
    }

    // Rest of class left out for brevity
}
