import components.program.Program;
import components.simplewriter.SimpleWriter;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Pretty prints {@code this} to the given stream {@code out} {@code offset}
 * spaces from the left margin using
 * {@link components.program.Program#INDENT_SIZE Program.INDENT_SIZE} spaces for
 * each indentation level.
 *
 * @param out
 *            the output stream
 * @param offset
 *            the number of spaces to be placed before every nonempty line of
 *            output; nonempty lines of output that are indented further will,
 *            of course, continue with even more spaces
 * @updates out.content
 * @requires out.is_open and 0 <= offset
 * @ensures <pre>
 * out.content =
 *   #out.content * [this pretty printed offset spaces from the left margin
 *                   using Program.INDENT_SIZE spaces for indentation]
 * </pre>
 */
public void prettyPrint(SimpleWriter out, int offset) {
    int indent = Program.INDENT_SIZE;
    switch (this.kind()) {
        case BLOCK: {

            // TODO - fill in case
            int length = this.lengthOfBlock();
            for (int i = 0; i < length; i++) {
                Statement subTree = this.removeFromBlock(i);
                subTree.prettyPrint(out, offset);
                this.addToBlock(i, subTree);
            }

            break;
        }
        case IF: {

            // TODO - fill in case
            Statement subTree = this.newInstance();
            Condition ifCondition = this.disassembleIf(subTree);
            printSpaces(out, offset);

            out.println("IF " + toStringCondition(ifCondition) + " THEN");
            subTree.prettyPrint(out, offset + indent);

            for (int i = 0; i < offset; i++) {
                out.print(" ");
            }
            out.println("END IF");
            this.assembleIf(ifCondition, subTree);
            break;
        }
        case IF_ELSE: {

            // TODO - fill in case
            Statement subTreeIf = this.newInstance();
            Statement subTreeElse = this.newInstance();
            Condition ifElseCondition = this.disassembleIfElse(subTreeIf, subTreeElse);
            printSpaces(out, offset);
            out.println("IF " + toStringCondition(ifElseCondition) + " THEN");
            subTreeIf.prettyPrint(out, offset + indent);

            printSpaces(out, offset);
            out.println("ELSE");
            subTreeElse.prettyPrint(out, offset + indent);

            printSpaces(out, offset);
            out.println("END IF");
            this.assembleIfElse(ifElseCondition, subTreeIf, subTreeElse);

            break;
        }
        case WHILE: {

            // TODO - fill in case
            Statement subTree = this.newInstance();
            Condition whileCondition = this.disassembleWhile(subTree);
            printSpaces(out, offset);
            out.println("WHILE " + toStringCondition(whileCondition) + " DO");
            subTree.prettyPrint(out, offset + indent);

            printSpaces(out, offset);
            out.println("END WHILE");
            this.assembleWhile(whileCondition, subTree);

            break;
        }
        case CALL: {

            // TODO - fill in case
            String call = this.disassembleCall();
            printSpaces(out, offset);
            out.println(call);
            this.assembleCall(call);

            break;
        }
        default: {
            // this will never happen...
            break;
        }
    }
}
