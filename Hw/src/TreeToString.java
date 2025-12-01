import components.binarytree.BinaryTree;

/**
 * Returns the {@code String} prefix representation of the given
 * {@code BinaryTree<T>}.
 *
 * @param <T>
 *            the type of the {@code BinaryTree} node labels
 * @param t
 *            the {@code BinaryTree} to convert to a {@code String}
 * @return the prefix representation of {@code t}
 * @ensures treeToString = [the String prefix representation of t]
 */
public static <T> String treeToString(BinaryTree<T> t) {
    BinaryTree<T> left = t.newInstance();
    BinaryTree<T> right = t.newInstance();
    String line = "";

    if (t.height() != 0) {
        T root = t.disassemble(left, right);
        line = root.toString() + "(" + treeToString(left) + treeToString(right) + ")";
        t.assemble(root, left, right);
    } else {
        line = line + "()";
    }
    return line;
}

/**
 * Returns a copy of the the given {@code BinaryTree}.
 *
 * @param t
 *            the {@code BinaryTree} to copy
 * @return a copy of the given {@code BinaryTree}
 * @ensures copy = t
 */
public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {
    BinaryTree<Integer> left = t.newInstance();
    BinaryTree<Integer> right = t.newInstance();
    BinaryTree<Integer> copy = t.newInstance();
    if (t.height() != 0) {
        int root = t.disassemble(left, right);
        copy.assemble(root, copy(left), copy(right));
        t.assemble(root, left, right);
    }
    return copy;
}
