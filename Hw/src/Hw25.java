import components.map.Map;
import components.program.Program;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Refactors the given {@code Statement} by renaming every occurrence of
 * instruction {@code oldName} to {@code newName}. Every other statement is left
 * unmodified.
 *
 * @param s
 *            the {@code Statement}
 * @param oldName
 *            the name of the instruction to be renamed
 * @param newName
 *            the new name of the renamed instruction
 * @updates s
 * @requires [newName is a valid IDENTIFIER]
 * @ensures <pre>
 * s = [#s refactored so that every occurrence of instruction oldName
 *   is replaced by newName]
 * </pre>
 */
public static void renameInstruction(Statement s, String oldName, String newName) {
    switch (s.kind()) {
        case BLOCK: {
            int length = s.lengthOfBlock();
            for (int i = 0; i < length; i++) {
                Statement subTree = s.removeFromBlock(i); // Not sure i or 0 here
                renameInstruction(subTree, oldName, newName);
                s.addToBlock(i, subTree);
            }
            break;
        }
        case IF: {
            Statement ifBody = s.newInstance();
            Condition ifCondition = s.disassembleIf(ifBody);
            renameInstruction(ifBody, oldName, newName);
            s.assembleIf(ifCondition, ifBody);

            break;
        }
        case IF_ELSE: {
            Statement ifBody = s.newInstance();
            Statement elseBody = s.newInstance();
            Condition ifElseCondition = s.disassembleIfElse(ifBody, elseBody);
            renameInstruction(ifBody, oldName, newName);
            renameInstruction(elseBody, oldName, newName);
            s.assembleIfElse(ifElseCondition, ifBody, elseBody);

            break;
        }
        case WHILE: {
            Statement whileBody = s.newInstance();
            Condition whileCondition = s.disassembleWhile(whileBody);
            renameInstruction(whileBody, oldName, newName);
            s.assembleWhile(whileCondition, whileBody);

            break;
        }
        case CALL: {
            String call = s.disassembleCall();
            if (call.equals(oldName)) {
                s.assembleCall(newName);
            } else {
                s.assembleCall(call);
            }
            break;
        }
        default:
            break;
    }
}

/**
 * Refactors the given {@code Program} by renaming instruction {@code oldName},
 * and every call to it, to {@code newName}. Everything else is left unmodified.
 *
 * @param p
 *            the {@code Program}
 * @param oldName
 *            the name of the instruction to be renamed
 * @param newName
 *            the new name of the renamed instruction
 * @updates p
 * @requires <pre>
 * oldName is in DOMAIN(p.context)  and
 * [newName is a valid IDENTIFIER]  and
 * newName is not in DOMAIN(p.context)
 * </pre>
 * @ensures <pre>
 * p = [#p refactored so that instruction oldName and every call
 *   to it are replaced by newName]
 * </pre>
 */
public static void renameInstruction(Program p, String oldName, String newName) {
    Map<String, Statement> context = p.newContext();
    Map<String, Statement> c = p.newContext();
    p.swapContext(context);
    while (context.size() > 0) {
        Map.Pair<String, Statement> pair = context.removeAny();
        if (pair.key().equals(oldName)) {
            c.add(newName, pair.value());
        } else {
            renameInstruction(pair.value(), oldName, newName);
            c.add(pair.key(), pair.value());
        }
    }
    Statement body = p.newBody();
    p.swapBody(body);
    renameInstruction(body, oldName, newName);
    p.swapBody(body);
    p.swapContext(c);
}
