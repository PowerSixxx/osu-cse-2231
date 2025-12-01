import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Baowen Liu, Chen Lou
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer.isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF")
                : "" + "Violation of: <\"IF\"> is proper prefix of tokens";

        // TODO - fill in body
        // Consume "IF"
        String ifWord = tokens.dequeue();
        // Next token must be a valid condition string
        Reporter.assertElseFatalError(Tokenizer.isCondition(tokens.front()),
                "Wrong condition");
        Condition condition = parseCondition(tokens.dequeue());
        // Expect "THEN" after condition
        Reporter.assertElseFatalError(tokens.front().equals("THEN"),
                "Error: Expected THEN, found: " + "\"" + tokens.front() + "\"");

        // Consume "THEN"
        String thenWord = tokens.dequeue();
        Statement ifBlock = s.newInstance();

        // decent recursive parsing
        ifBlock.parseBlock(tokens);

        Reporter.assertElseFatalError(
                tokens.front().equals("ELSE") || tokens.front().equals("END"),
                "expecting 'END' or 'ELSE'");
        if (tokens.front().equals("ELSE")) {
            // Consume "ELSE"
            String elseWord = tokens.dequeue();
            Statement elseBlock = s.newInstance();
            elseBlock.parseBlock(tokens);
            // Assemble IF-ELSE into s
            s.assembleIfElse(condition, ifBlock, elseBlock);
            Reporter.assertElseFatalError(tokens.front().equals("END"),
                    "expecting 'END'");
            String endWord = tokens.dequeue(); // consume END
        } else {
            /*
             * No ELSE, this is a simple IF Now must see "END"
             */
            Reporter.assertElseFatalError(tokens.front().equals("END"),
                    "expecting 'END'");
            s.assembleIf(condition, ifBlock);
            String endWord = tokens.dequeue();
        }
        /*
         * Finally must see ending "IF" Grammar requires matching END IF
         */
        String endIfWord = tokens.dequeue();
        Reporter.assertElseFatalError(endIfWord.equals("IF"), "Expecting end 'IF'");
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE")
                : "" + "Violation of: <\"WHILE\"> is proper prefix of tokens";

        // TODO - fill in body
        // Consume leading "WHILE"
        String whileWord = tokens.dequeue();
        // Next token must be a valid condition
        Reporter.assertElseFatalError(Tokenizer.isCondition(tokens.front()),
                "Unepecting condition here");
        Condition condition = parseCondition(tokens.dequeue()); // condition
        // Expect "DO"
        String doWord = tokens.dequeue();
        Reporter.assertElseFatalError(doWord.equals("DO"), "expecting 'DO'");
        /*
         * Parse the WHILE block body parseBlock will stop right before seeing
         * "END"
         */
        Statement whileBlock = s.newInstance();
        whileBlock.parseBlock(tokens);
        // Assemble the WHILE statement
        s.assembleWhile(condition, whileBlock);
        // Now the next token must be "END"
        String endWord = tokens.dequeue();
        Reporter.assertElseFatalError(endWord.equals("END"), "expecting 'END'");

        Reporter.assertElseFatalError(whileWord.equals("WHILE"), "expecting 'WHILE'");
        // whileWord = tokens.dequeue();
    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && Tokenizer.isIdentifier(tokens.front())
                : "" + "Violation of: identifier string is proper prefix of tokens";

        // TODO - fill in body
        String call = tokens.dequeue();
        s.assembleCall(call);

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0
                : "" + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body
        /*
         * Dispatch to the correct parsing helper based on the first token.
         */
        Reporter.assertElseFatalError(
                tokens.front().equals("IF") || tokens.front().equals("WHILE")
                        || Tokenizer.isIdentifier(tokens.front()),
                "Error: Expect a IF, WHILE, or Identifier, found: " + tokens.front());
        if (tokens.front().equals("IF")) {
            // Parse IF/IF-ELSE statement
            parseIf(tokens, this);
        } else if (tokens.front().equals("WHILE")) {
            // Parse WHILE statement
            parseWhile(tokens, this);
        } else {
            // Parse CALL statement
            parseCall(tokens, this);
        }

    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0
                : "" + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body

        int i = 0;
        while (!tokens.front().equals("ELSE") && !tokens.front().equals("END")
                && !tokens.front().equals(Tokenizer.END_OF_INPUT)) {
            Statement s = this.newInstance();
            s.parse(tokens);
            this.addToBlock(i, s);
            i++;
        }

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
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
