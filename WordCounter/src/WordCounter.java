import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * WordCounter Program: The purpose for this program is to read the user input
 * file, ordered them alphabetically, counts their occurrences, finally outputs
 * the results as an HTML table.
 *
 * @author Baowen Liu
 */
public final class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
        // no code needed here
    }

    /**
     * Override comparator and ordered by alphabetically. Not depended on case.
     */
    private static final class Compare implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }
    }

    /**
     * Outputs the HTML header and table structure.
     *
     * @param out
     *            the output stream
     * @param inFile
     *            the name of input file
     */
    private static void outputHeader(SimpleWriter out, String inFile) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<html><head><title>Words Counted in");
        out.println(inFile + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Words Counted in");
        out.println(inFile + "</h2><hr/>");
        out.println("<table border=\"1\">");
        out.println("<tr><th>Words</th>");
        out.println("<th>Counts</th>");
        out.println("</tr>");
    }

    /**
     * Outputs the HTML footer.
     *
     * @param out
     *            the output stream
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        // Close the table, body, and html
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Adds each character from the given string into the separator set.
     *
     * @param separatorStr
     *            String containing all separator characters
     * @param separatorSet
     *            Set stores the separator characters
     */
    private static void generateElements(String separatorStr,
            Set<Character> separatorSet) {
        assert separatorStr != null : "Violation of: str is not null";
        assert separatorSet != null : "Violation of: charSet is not null";
        // For loop to trace all the separator character
        for (int i = 0; i < separatorStr.length(); i++) {
            char c = separatorStr.charAt(i);
            if (!separatorSet.contains(c)) {
                separatorSet.add(c);
            }
        }
    }

    /**
     * Extracts the next word or separator starting at a given position.
     *
     * @param inputText
     *            the input string
     * @param startPosition
     *            the position to start scanning
     * @param separatorCharacters
     *            set of separator characters
     * @return the next word or separator
     */
    private static String nextWordOrSeparator(String inputText, int startPosition,
            Set<Character> separatorCharacters) {
        assert inputText != null : "Violation of: inputText is not null";
        assert separatorCharacters != null
                : "Violation of: separatorCharacters is not null";
        assert 0 <= startPosition : "Violation of: 0 <= startPosition";
        assert startPosition < inputText.length()
                : "Violation of: startPosition < |inputText|";

        // Start with the first character
        char startingCharacter = inputText.charAt(startPosition);
        String extractedSegment = "" + startingCharacter;
        int currentPosition = startPosition + 1;

        if (!separatorCharacters.contains(startingCharacter)) {
            // Case 1: starting with a word character
            // Collect the consecutive non-separator characters
            while (currentPosition < inputText.length()
                    && !separatorCharacters.contains(inputText.charAt(currentPosition))) {
                extractedSegment += inputText.charAt(currentPosition);
                currentPosition++;
            }
        } else {
            // Case 2: starting with a separator
            // Collect the consecutive separator characters
            while (currentPosition < inputText.length()
                    && separatorCharacters.contains(inputText.charAt(currentPosition))) {
                extractedSegment += inputText.charAt(currentPosition);
                currentPosition++;
            }
        }
        return extractedSegment;
    }

    /**
     * Sorts the words then write the words and counts into a structured table.
     *
     * @param words
     *            queue of words
     * @param wordsMap
     *            map of words and counts
     * @param alphabetical
     *            comparator for sorting like alphabetical
     * @param fileOutput
     *            output stream
     */
    public static void wordSorting(Queue<String> words, Map<String, Integer> wordsMap,
            Comparator<String> alphabetical, SimpleWriter fileOutput) {
        // Count occurrences of words
        while (words.length() != 0) {
            String word = words.dequeue();
            // To find if wordsMap has this word
            // if not, add the word and count 1
            if (!wordsMap.hasKey(word)) {
                wordsMap.add(word, 1);
            } else { // if it has, only update counts
                int oldValue = wordsMap.value(word);
                int newValue = oldValue + 1;
                wordsMap.replaceValue(word, newValue);
            }
        }
        // Create temporary containers
        Queue<String> tempWords = words.newInstance();
        Map<String, Integer> tempMap = wordsMap.newInstance();
        // Transfer data from wordsMap to temporary structures
        while (wordsMap.iterator().hasNext()) {
            // Return an arbitrary (key, value) pair
            Pair<String, Integer> tempPair = wordsMap.removeAny();
            // Copy (word, count) into tempMap
            tempMap.add(tempPair.key(), tempPair.value());
            // Save words into temporary queue tempWords
            tempWords.enqueue(tempPair.key());
        }
        // Clear the original wordsMap since date is already copied
        wordsMap.clear();
        // Sort the temporary word queue alphabetically
        tempWords.sort(alphabetical);
        // Output the sorted words and counts
        while (tempWords.iterator().hasNext()) {
            String word = tempWords.dequeue();
            // Look up (word, count) pair in tempMap
            Pair<String, Integer> orderedDictionary = tempMap.remove(word);
            // Output the HTML format
            fileOutput.println("<tr>");
            fileOutput.println("<td>" + orderedDictionary.key() + "</td>");
            fileOutput.println("<td>" + orderedDictionary.value() + "</td>");
            fileOutput.println("</tr>");
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        // Create console I/O streams
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        // Define separators: space, tab, punctuation, backslash, etc.
        // Call generateElements method to build a separator set
        final String separatorStr = " \t,--.!;\\:";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);
        // Ask reading file name from user
        out.println("Please enter the name of the input file: ");
        String inFile = in.nextLine();
        SimpleReader inFileReader = new SimpleReader1L(inFile);
        // Ask output file name from user
        out.println("Please enter the name of the output file: ");
        String outFile = in.nextLine();
        SimpleWriter fileOutput = new SimpleWriter1L(outFile);
        // Initialize data structures
        Queue<String> words = new Queue1L<>();
        Map<String, Integer> wordMap = new Map1L<>();
        // Read input file line by line and extract words
        while (!inFileReader.atEOS()) {
            String readLine = inFileReader.nextLine();
            int position = 0;
            while (position < readLine.length()) {
                String word = nextWordOrSeparator(readLine, position, separatorSet);
                // Enqueue words not separators
                if (!word.isEmpty() && !separatorSet.contains(word.charAt(0))) {
                    words.enqueue(word);
                }
                // Update position
                position += word.length();
            }
        }
        // Sort words alphabetically
        Comparator<String> alphabetical = new Compare();
        words.sort(alphabetical);
        // Generate HTML output
        outputHeader(fileOutput, inFile);
        wordSorting(words, wordMap, alphabetical, fileOutput);
        outputFooter(fileOutput);
        // Close streams
        in.close();
        out.close();
        inFileReader.close();
        fileOutput.close();
    }

}
