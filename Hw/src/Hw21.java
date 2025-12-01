import java.util.Iterator;

import components.sequence.Sequence;
import components.tree.Tree;

/**
 * Returns the size of the given {@code Tree<T>}.
 *
 * @param <T>
 *            the type of the {@code Tree} node labels
 * @param t
 *            the {@code Tree} whose size to return
 * @return the size of the given {@code Tree}
 * @ensures size = |t|
 */
public static <T> int size(Tree<T> t) {

    int size = 0;
    if (t.height() == 0) { // Base case
        size = 0;
    } else {
        Sequence<Tree<T>> subTree = t.newSequenceOfTree();
        T root = t.disassemble(subTree);
        size++; // Add root size 1 to the total size
        /*
         * The subTree may larger than 2, like: A with subtree B C D. Use for
         * loop is to calculate each subTree's size. Since subTree is
         * Sequence<Tree<T>>, can use sequence's method length() to calculate.
         */
        for (int i = 0; i < subTree.length(); i++) {
            size = size + size(subTree.entry(i));
        }

        t.assemble(root, subTree); // Restore the original tree
    }
    return size;
}

/**
 * Returns the size of the given {@code Tree<T>}.
 *
 * @param <T>
 *            the type of the {@code Tree} node labels
 * @param t
 *            the {@code Tree} whose size to return
 * @return the size of the given {@code Tree}
 * @ensures size = |t|
 */
public static <T> int size2(Tree<T> t) {
    int size = 0;
    // Set up an iterator to iterate the entire tree (pre-order)
    Iterator<T> it = t.iterator();
    // If there is still a node left then iterate it
    while (it.hasNext()) {
        it.next(); // Update to the next node
        size++; // Update size and add 1
    }
    return size;
}

/**
 * Returns the height of the given {@code Tree<T>}.
 *
 * @param <T>
 *            the type of the {@code Tree} node labels
 * @param t
 *            the {@code Tree} whose height to return
 * @return the height of the given {@code Tree}
 * @ensures height = ht(t)
 */
public static <T> int height(Tree<T> t) {
    // Empty tree height is zero (Base case)
    int ans = 0;
    if (t.size() == 0) {
        ans = 0;
    } else {
        // Set up a subTree variable to store all of the subTrees
        Sequence<Tree<T>> subTree = t.newSequenceOfTree();
        // Disassemble
        T root = t.disassemble(subTree);
        int largest = 0; // Record the largest height in subtree
        // Iterate all of the subtrees to search the largest one
        for (int i = 0; i < subTree.length(); i++) {
            int temp = height(subTree.entry(i));
            if (temp > largest) { // Compare the current height with largest one
                largest = temp;
            }
        }
        t.assemble(root, subTree);
        ans = 1 + largest;
    }
    return ans;
}

/**
 * Returns the largest integer in the given {@code Tree<Integer>}.
 *
 * @param t
 *            the {@code Tree<Integer>} whose largest integer to return
 * @return the largest integer in the given {@code Tree<Integer>}
 * @requires |t| > 0
 * @ensures <pre>
 * max is in labels(t)  and
 * for all i: integer where (i is in labels(t)) (i <= max)
 * </pre>
 */
public static int max(Tree<Integer> t) {
    // Set up a subTree variable to store all of the subTrees
    Sequence<Tree<Integer>> subTree = t.newSequenceOfTree();
    // Disassemble
    int root = t.disassemble(subTree);
    int largest = root;
    for (int i = 0; i < subTree.length(); i++) {
        int temp = max(subTree.entry(i));
        if (temp > largest) {
            largest = temp;
        }
    }
    t.assemble(root, subTree);
    return largest;
}

/**
 * Returns the {@code String} prefix representation of the given
 * {@code Tree<T>}.
 *
 * @param <T>
 *            the type of the {@code Tree} node labels
 * @param t
 *            the {@code Tree} to convert to a {@code String}
 * @return the prefix representation of {@code t}
 * @ensures treeToString = [the String prefix representation of t]
 */
public static <T> String treeToString(Tree<T> t) {
    if (t.size() == 0) {
        return "()";
    }

    Sequence<Tree<T>> subTree = t.newSequenceOfTree();
    T root = t.disassemble(subTree);
    String subtrees = "";
    for (int i = 0; i < subTree.length(); i++) {
        subtrees += treeToString(subTree.entry(i));
    }
    String result = root.toString() + "(" + subtrees + ")";
    t.assemble(root, subTree);
    return result;
}
