import components.statement.Statement;

/**
 * Reports the number of calls to primitive instructions (move, turnleft,
 * turnright, infect, skip) in a given {@code Statement}.
 *
 * @param s
 *            the {@code Statement}
 * @return the number of calls to primitive instructions in {@code s}
 * @ensures <pre>
 * countOfPrimitiveCalls =
 *  [number of calls to primitive instructions in s]
 * </pre>
 */
public static int countOfPrimitiveCalls(Statement s) {
    int count = 0;
    switch (s.kind()) {
        case BLOCK: {
            /*
             * Add up the number of calls to primitive instructions in each
             * nested statement in the BLOCK.
             */

            // TODO - fill in case
            for (int i = 0; i < s.lengthOfBlock(); i++) {
                Statement subLabel = s.removeFromBlock(i);
                count += countOfPrimitiveCalls(subLabel);
                s.addToBlock(i, subLabel);
            }

            break;
        }
        case IF: {
            /*
             * Find the number of calls to primitive instructions in the body of
             * the IF.
             */

            // TODO - fill in case
            Statement body = s.newInstance();
            Statement.Condition c = s.disassembleIf(body);
            count = countOfPrimitiveCalls(body);
            s.assembleIf(c, body);
            break;
        }
        case IF_ELSE: {
            /*
             * Add up the number of calls to primitive instructions in the
             * "then" and "else" bodies of the IF_ELSE.
             */

            // TODO - fill in case
            Statement thenBody = s.newInstance();
            Statement elseBody = s.newInstance();
            Statement.Condition c = s.disassembleIfElse(thenBody, elseBody);
            count = countOfPrimitiveCalls(thenBody) + countOfPrimitiveCalls(elseBody);
            s.assembleIfElse(c, thenBody, elseBody);
            break;
        }
        case WHILE: {
            /*
             * Find the number of calls to primitive instructions in the body of
             * the WHILE.
             */

            // TODO - fill in case
            Statement body = s.newInstance();
            Statement.Condition c = s.disassembleWhile(body);
            count = countOfPrimitiveCalls(body);
            s.assembleWhile(c, body);
            break;
        }
        case CALL: {
            /*
             * This is a leaf: the count can only be 1 or 0. Determine whether
             * this is a call to a primitive instruction or not.
             */

            // TODO - fill in case
            String name = s.disassembleCall();
            if (name.equals("move") || name.equals("turnleft") || name.equals("turnright")
                    || name.equals("infect") || name.equals("skip")) {
                count++;
            }
            s.assembleCall(name);

            break;
        }
        default: {
            // this will never happen...can you explain why?
            break;
        }
    }
    return count;
}
