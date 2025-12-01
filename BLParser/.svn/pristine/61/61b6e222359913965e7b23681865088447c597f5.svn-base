import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Baowen Liu, Chen Lou
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens, Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION")
                : "" + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        Reporter.assertElseFatalError(tokens.length() > 0,
                "Expected \"INSTRUCTION\" token, but no tokens left.");
        String instructionToken = tokens.dequeue();
        Reporter.assertElseFatalError(instructionToken.equals("INSTRUCTION"),
                "Expected \"INSTRUCTION\" token");

        // instruction name
        Reporter.assertElseFatalError(tokens.length() > 0,
                "Missing instruction name after INSTRUCTION.");
        String instructionName = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(instructionName),
                "Expected valid identifier for instruction name");

        // not a primitive instruction
        Reporter.assertElseFatalError(
                !instructionName.equals("move") && !instructionName.equals("turnright")
                        && !instructionName.equals("turnleft")
                        && !instructionName.equals("infect")
                        && !instructionName.equals("skip"),
                "Instruction name cannot be a primitive instruction: " + instructionName);

        // "IS"
        Reporter.assertElseFatalError(tokens.length() > 0,
                "Missing \"IS\" after instruction name.");
        String isToken = tokens.dequeue();
        Reporter.assertElseFatalError(isToken.equals("IS"),
                "Expected \"IS\" after instruction name");

        // block (no BEGIN here!)
        body.parseBlock(tokens);

        // "END"
        Reporter.assertElseFatalError(tokens.length() > 0,
                "Missing \"END\" after instruction body.");
        String endToken = tokens.dequeue();
        Reporter.assertElseFatalError(endToken.equals("END"),
                "Expected \"END\" after statement body");

        // ending name
        Reporter.assertElseFatalError(tokens.length() > 0,
                "Missing instruction name after END.");
        String endName = tokens.dequeue();
        Reporter.assertElseFatalError(endName.equals(instructionName),
                "Ending name \"" + endName + "\" does not match beginning name \""
                        + instructionName + "\"");

        return instructionName;

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0
                : "" + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        this.clear();

        String programToken = tokens.dequeue(); // Read "PROGRAM"
        Reporter.assertElseFatalError(programToken.equals("PROGRAM"),
                "Error: expected 'PROGRAM' at beginning, found '" + programToken + "'.");
        // It will fail if it is not in proper format

        Reporter.assertElseFatalError(tokens.length() > 0,
                "Error: missing program name after PROGRAM."); // Check if it is empty,
        // if yes, then error
        String programName = tokens.dequeue(); // Get Program name
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(programName),
                "Error: '" + programName + "' is not a valid BL identifier.");
        this.setName(programName);

        Reporter.assertElseFatalError(tokens.length() > 0,
                "Error: missing 'IS' after program name."); // check if it is empty
        String isToken = tokens.dequeue(); // check is
        Reporter.assertElseFatalError(isToken.equals("IS"),
                "Error: expected 'IS' after program name, found '" + isToken + "'.");

        Map<String, Statement> context = this.newContext(); // create context

        while (tokens.front().equals("INSTRUCTION")) {
            Statement instructionBody = this.newBody();
            String instructionName = parseInstruction(tokens, instructionBody);
            // Get instruction name

            Reporter.assertElseFatalError(!context.hasKey(instructionName),
                    "Error: duplicate instruction name '" + instructionName + "'.");
            context.add(instructionName, instructionBody); // Add
        }

        Reporter.assertElseFatalError(tokens.length() > 0,
                "Error: missing 'BEGIN' before program body.");
        String beginToken = tokens.dequeue(); // remove begin
        Reporter.assertElseFatalError(beginToken.equals("BEGIN"),
                "Error: expected 'BEGIN' after context, found '" + beginToken + "'.");

        Statement mainBody = this.newBody();
        mainBody.parseBlock(tokens);
        this.swapBody(mainBody);

        Reporter.assertElseFatalError(tokens.length() > 0,
                "Error: missing 'END' at end of program.");
        String endToken = tokens.dequeue(); // check END
        Reporter.assertElseFatalError(endToken.equals("END"),
                "Error: expected 'END' after main body, found '" + endToken + "'.");

        Reporter.assertElseFatalError(tokens.length() > 0,
                "Error: missing program name after END.");
        String endName = tokens.dequeue(); // check PROGRAM name
        Reporter.assertElseFatalError(endName.equals(programName), "Error: ending name '"
                + endName + "' does not match beginning name '" + programName + "'.");

        Reporter.assertElseFatalError(
                tokens.length() == 1 && tokens.front().equals(Tokenizer.END_OF_INPUT),
                "Error: unexpected tokens after end of program.");

        this.swapContext(context);

    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
