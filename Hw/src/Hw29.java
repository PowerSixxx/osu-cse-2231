import components.queue.Queue;

/**
 * Evaluates a Boolean expression and returns its value.
 *
 * @param tokens
 *            the {@code Queue<String>} that starts with a bool-expr string
 * @return value of the expression
 * @updates tokens
 * @requires [a bool-expr string is a prefix of tokens]
 * @ensures <pre>
 * valueOfBoolExpr =
 *   [value of longest bool-expr string at start of #tokens]  and
 * #tokens = [longest bool-expr string at start of #tokens] * tokens
 * </pre>
 */

// bool-expr   → T |
// → F |
// → NOT ( bool-expr ) |
// → ( bool-expr binary-op bool-expr )
// binary-op   → AND | OR
public static boolean valueOfBoolExpr(Queue<String> tokens) {
    boolean result;
    while (tokens.length() != 0) {
        String token = tokens.dequeue();
        if (token.equals("T")) {
            result = true;
        } else if (token.equals("F")) {
            result = false;
        } else if (token.equals("NOT")) {
            result = !valueOfBoolExpr(tokens);
        } else if (token.equals("(")) {
            result = valueOfBoolExpr(tokens);
        } else if (token.equals(")")) {

        } else if (token.equals("AND")) {
            result &= valueOfBoolExpr(tokens);
        } else if (token.equals("OR")) {
            result |= valueOfBoolExpr(tokens);
        } else {

        }
    }
    return result;
}
