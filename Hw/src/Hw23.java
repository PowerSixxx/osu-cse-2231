import components.statement.Statement;

/**
 * Refactors the given {@code Statement} so that every IF_ELSE statement with a
 * negated condition (NEXT_IS_NOT_EMPTY, NEXT_IS_NOT_ENEMY, NEXT_IS_NOT_FRIEND,
 * NEXT_IS_NOT_WALL) is replaced by an equivalent IF_ELSE with the opposite
 * condition and the "then" and "else" BLOCKs switched. Every other statement is
 * left unmodified.
 *
 * @param s
 *            the {@code Statement}
 * @updates s
 * @ensures <pre>
 * s = [#s refactored so that IF_ELSE statements with "not"
 *   conditions are simplified so the "not" is removed]
 * </pre>
 */
// IF_ELSE (NEXT_IS_NOT_EMPTY) THEN A ELSE B
// TO
// IF_ELSE (NEXT_IS_EMPTY) THEN B ELSE A
public static void simplifyIfElse(Statement s) {
    switch (s.kind()) {
        case BLOCK: {
            for (int i = 0; i < s.lengthOfBlock(); i++) {
                Statement subLabel = s.removeFromBlock(i);
                simplifyIfElse(subLabel);
                s.addToBlock(i, subLabel);
            }

            break;
        }
        case IF: {
            Statement body = s.newInstance();
            Statement.Condition c = s.disassembleIf(body);
            simplifyIfElse(body);
            s.assembleIf(c, body);

            break;
        }
        case IF_ELSE: {

            Statement thenPart = s.newInstance();
            Statement elsePart = s.newInstance();
            Statement.Condition c = s.disassembleIfElse(thenPart, elsePart);

            simplifyIfElse(thenPart);
            simplifyIfElse(elsePart);
            switch (c.name()) {
                case "NEXT_IS_NOT_EMPTY": {
                    c = Statement.Condition.NEXT_IS_EMPTY;
                    s.assembleIfElse(c, elsePart, thenPart);
                    break;
                }
                case "NEXT_IS_NOT_ENEMY": {
                    c = Statement.Condition.NEXT_IS_ENEMY;
                    s.assembleIfElse(c, elsePart, thenPart);
                    break;
                }
                case "NEXT_IS_NOT_FRIEND": {
                    c = Statement.Condition.NEXT_IS_FRIEND;
                    s.assembleIfElse(c, elsePart, thenPart);
                    break;
                }
                case "NEXT_IS_NOT_WALL": {
                    c = Statement.Condition.NEXT_IS_WALL;
                    s.assembleIfElse(c, elsePart, thenPart);
                    break;
                }
                default: {
                    s.assembleIfElse(c, thenPart, elsePart);
                    break;
                }
            }
            break;
        }
        case WHILE: {

            Statement body = s.newInstance();
            Statement.Condition c = s.disassembleWhile(body);
            simplifyIfElse(body);
            s.assembleWhile(c, body);
            break;
        }
        case CALL: {
            // nothing to do here...can you explain why?
            // Because call is the leaf node, it will never contain if_else statement
            break;
        }
        default: {
            // this will never happen...can you explain why?
            break;
        }
    }
}
