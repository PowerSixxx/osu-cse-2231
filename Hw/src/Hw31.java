import components.map.Map;
import components.program.Program.Instruction;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Generates the sequence of virtual machine instructions ("byte codes")
 * corresponding to {@code s} and appends it at the end of {@code cp}.
 *
 * @param s
 *            the {@code Statement} for which to generate code
 * @param context
 *            the {@code Context} in which to find user defined instructions
 * @param cp
 *            the {@code Sequence} containing the generated code
 * @updates cp
 * @ensures
 *
 *          <pre>
 * if [all instructions called in s are either primitive or
 *     defined in context]  and
 *    [context does not include any calling cycles, i.e., recursion] then
 *  cp = #cp * s[the sequence of virtual machine "byte codes" corresponding to s]
 * else
 *  [reports an appropriate error message to the console and terminates client]
 *          </pre>
 */
private static void generateCodeForStatement(Statement s, Map<String, Statement> context,
        Sequence<Integer> cp) {

    final int dummy = 0;

    switch (s.kind()) {
        case BLOCK: {

            // TODO - fill in case
            Statement current = s.newInstance();
            for (int index = 0; index < s.lengthOfBlock(); index++) {
                current = s.removeFromBlock(index);
                generateCodeForStatement(current, context, cp);
                s.addToBlock(index, current);
            }

            break;
        }
        case IF: {
            Statement b = s.newInstance();
            Condition c = s.disassembleIf(b);
            cp.add(cp.length(), conditionalJump(c).byteCode());
            int jump = cp.length();
            cp.add(cp.length(), dummy);
            generateCodeForStatement(b, context, cp);
            cp.replaceEntry(jump, cp.length());
            s.assembleIf(c, b);
            break;
        }
        case IF_ELSE: {

            // TODO - fill in case
            Statement b1 = s.newInstance();
            Statement b2 = s.newInstance();
            Condition c = s.disassembleIfElse(b1, b2);
            cp.add(cp.length(), conditionalJump(c).byteCode());
            int condJump = cp.length();
            cp.add(cp.length(), dummy);
            generateCodeForStatement(b1, context, cp);
            cp.add(cp.length(), Instruction.valueOf("JUMP").byteCode());
            int jump = cp.length();
            cp.add(cp.length(), dummy);
            cp.replaceEntry(condJump, cp.length());
            generateCodeForStatement(b2, context, cp);
            cp.replaceEntry(jump, cp.length());
            s.assembleIfElse(c, b1, b2);
            break;
        }
        case WHILE: {

            // TODO - fill in case
            Statement b = s.newInstance();
            Condition c = s.disassembleWhile(b);
            int test = cp.length();
            cp.add(cp.length(), conditionalJump(c).byteCode());
            int jump = cp.length();
            cp.add(cp.length(), dummy);
            generateCodeForStatement(b, context, cp);
            cp.add(cp.length(), Instruction.valueOf("JUMP").byteCode());
            cp.add(cp.length(), test);
            s.assembleWhile(c, b);
            cp.replaceEntry(jump, cp.length());
            break;
        }
        case CALL: {

            // TODO - fill in case
            String label = s.disassembleCall();
            if (context.hasKey(label)) {
                generateCodeForStatement(context.value(label), context.newInstance(), cp);
            } else {
                label = label.toUpperCase();
                cp.add(cp.length(), Instruction.valueOf(label).byteCode());
                label = label.toLowerCase();
            }
            s.assembleCall(label);
            break;
        }
        default: {
            // this will never happen...
            break;
        }
    }
}

@Override
public Sequence<Integer> generatedCode() {

    // Resulting code sequence
    Sequence<Integer> cp = new Sequence1L<>();

    // Disassemble this program
    String name = this.name();
    Statement body = this.newBody();
    Map<String, Statement> context = this.newContext();
    this.disassemble(name, body, context);

    // Generate code for the body
    generateCodeForStatement(body, context, cp);

    // Append the HALT instruction
    cp.add(cp.length(), Instruction.HALT.byteCode());

    // Restore this Program
    this.assemble(name, body, context);

    return cp;
}
