import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This project is only for recursion practice.
 */
public final class RecursionPractice {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private RecursionPractice() {
    }

    /**
     * Computes the factorial of a non-negative integer n using recursion. n! =
     * n × (n-1) × (n-2) × ... × 1.
     *
     * @param n
     * @return result
     */
    public static int factorial(int n) {
        int result;
        if (n == 0) {
            return 1;
        } else {
            result = n * factorial(n - 1);
        }
        return result;
    }

    /**
     * Reverse the original string. "hello" -> "olleh".
     *
     * @param s
     * @return reversed
     */
    public static String reverse(String s) {
        String reversed = "";
        if (s.length() <= 1) {
            return s;
        } else {
            reversed = reverse(s.substring(1)) + s.charAt(0);
        }
        return reversed;
    }

    /**
     * Print the stars.
     *
     * @param out
     * @param n
     *            number of lines to print
     */
    public static void printStars(SimpleWriter out, int n) {
        if (n == 0) {
            return;
        } else {
            for (int i = 0; i < n; i++) {
                out.print("*");
            }
            out.println();
            printStars(out, n - 1);
        }
    }

    /**
     * Computes the n-th Fibonacci number using recursion. fib(n) = fib(n-1) +
     * fib(n-2), for n >= 2
     *
     * @param n
     * @return result
     */
    public static int fib(int n) {
        int result;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            result = fib(n - 1) + fib(n - 2);
        }
        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        String ans = "";

        while (!ans.equals("q")) {
            out.println("What can I do for you (select one of the following): ");
            out.println("Factorial: fa");
            out.println("Fibonacci: fi");
            out.println("Print stars: s");
            out.println("Reverse strings: r");
            ans = in.nextLine();

            if (ans.equals("fa")) {
                out.println("Enter a number to calculate factorial: ");
                int n = in.nextInteger();
                out.println(factorial(n));
            }

            if (ans.equals("fi")) {
                out.println("Enter a number to calculate fibonacci number: ");
                int n = in.nextInteger();
                out.println(fib(n));
            }

            if (ans.equals("r")) {
                out.println("Enter a string you want to reverse: ");
                String s = in.nextLine();
                out.println(reverse(s));
            }

            if (ans.equals("s")) {
                out.println("How many stars lines you want to print: ");
                int n = in.nextInteger();
                printStars(out, n);
            }
            out.println();
            out.println();
        }

        out.close();
        in.close();
    }
}
