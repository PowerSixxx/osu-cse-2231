import components.stack.Stack;
import components.stack.Stack2;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack2Test extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        // TODO - fill in body

        // This line added just to make the program compilable.
        return new Stack2<String>();
    }

    @Override
    protected final Stack<String> constructorRef() {

        // TODO - fill in body

        // This line added just to make the program compilable.
        return new Stack3<String>();
    }

}
