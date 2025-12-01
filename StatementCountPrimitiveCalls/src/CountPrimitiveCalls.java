import components.statement.Statement;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

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
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
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
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
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
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                // TODO - fill in case
                String name = s.disassembleCall();
                if (name.equals("move") || name.equals("turnleft")
                        || name.equals("turnright") || name.equals("infect")
                        || name.equals("skip")) {
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

//    /**
//     * Reports the number of calls to a given instruction, {@code instr}, in a
//     * given {@code Statement}.
//     *
//     * @param s
//     *            the {@code Statement}
//     * @param instr
//     *            the instruction name
//     * @return the number of calls to {@code instr} in {@code s}
//     * @ensures countOfInstructionCalls = [number of calls to instr in s]
//     */
//    public static int countOfInstructionCalls(Statement s, String instr) {
//        return 1;
//    }
//
//    /**
//     * Refactors the given {@code Statement} by renaming every occurrence of
//     * instruction {@code oldName} to {@code newName}. Every other statement is
//     * left unmodified.
//     *
//     * @param s
//     *            the {@code Statement}
//     * @param oldName
//     *            the name of the instruction to be renamed
//     * @param newName
//     *            the new name of the renamed instruction
//     * @updates s
//     * @requires [newName is a valid IDENTIFIER]
//     * @ensures <pre>
//     * s = [#s refactored so that every occurrence of oldName is
//     *   replaced by newName]
//     * </pre>
//     */
//    public static void renameInstruction(Statement s, String oldName, String newName) {
//
//    }
}
