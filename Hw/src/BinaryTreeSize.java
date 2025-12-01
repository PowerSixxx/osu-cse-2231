import components.binarytree.BinaryTree;

/**
 * Returns the size of the given {@code BinaryTree<T>}.
 *
 * @param <T>
 *            the type of the {@code BinaryTree} node labels
 * @param t
 *            the {@code BinaryTree} whose size to return
 * @return the size of the given {@code BinaryTree}
 * @ensures size = |t|
 */
public static <T> int size1(BinaryTree<T> t) {
    BinaryTree<T> left = t.newInstance();
    BinaryTree<T> right = t.newInstance();
    int size = 0;

    if (t.height() != 0) {
        T root = t.disassemble(left, right);
        size = 1 + size1(left) + size1(right);
        t.assemble(root, left, right);
    }
    return size;
}

/**
 * Returns the size of the given {@code BinaryTree<T>}.
 *
 * @param <T>
 *            the type of the {@code BinaryTree} node labels
 * @param t
 *            the {@code BinaryTree} whose size to return
 * @return the size of the given {@code BinaryTree}
 * @ensures size = |t|
 */
public static <T> int size2(BinaryTree<T> t) {
    int size = 0;
    for (T x : t) {
        size++;
    }
    return size;

}
