/**
 * Computes {@code a} mod {@code b} as % should have been defined to work.
 *
 * @param a
 *            the number being reduced
 * @param b
 *            the modulus
 * @return the result of a mod b, which satisfies 0 <= {@code mod} < b
 * @requires b > 0
 * @ensures <pre>
 * 0 <= mod  and  mod < b  and
 * there exists k: integer (a = k * b + mod)
 * </pre>
 */
public static int mod(int a, int b) {
    int result = a % b;
    if (result < 0) {
        result += b;
    }
    return result;
}
