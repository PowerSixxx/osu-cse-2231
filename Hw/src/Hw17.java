import components.binarytree.BinaryTree;

/**
 * Checks if the given {@code BinaryTree<Integer>} satisfies the heap ordering
 * property according to the <= relation.
 *
 * @param t
 *            the binary tree
 * @return true if the given tree satisfies the heap ordering property; false
 *         otherwise
 * @ensures <pre>
 * satisfiesHeapOrdering = [t satisfies the heap ordering property]
 * </pre>
 */
private static boolean satisfiesHeapOrdering(BinaryTree<Integer> t) {
    boolean satisfy = true;
    BinaryTree<Integer> left = t.newInstance();
    BinaryTree<Integer> right = t.newInstance();
    if (t.size() != 0) {
        int root = t.disassemble(left, right);
        boolean okHere = true;
        if (left.size() != 0 && root > left.root()) {
            okHere = false;
        }
        if (right.size() != 0 && root > right.root()) {
            okHere = false;
        }

        if (okHere) {
            satisfy = satisfiesHeapOrdering(left) && satisfiesHeapOrdering(right);
        } else {
            satisfy = false;
        }
        t.assemble(root, left, right);
    }
    return satisfy;
}
