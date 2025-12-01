import components.binarytree.BinaryTree;

// Question 1:
/**
 * Returns whether {@code x} is in {@code t}.
 *
 * @param <T>
 *            type of {@code BinaryTree} labels
 * @param t
 *            the {@code BinaryTree} to be searched
 * @param x
 *            the label to be searched for
 * @return true if t contains x, false otherwise
 * @requires IS_BST(t)
 * @ensures isInTree = (x is in labels(t))
 */
public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t, T x) {
    BinaryTree<T> left = t.newInstance();
    BinaryTree<T> right = t.newInstance();
    boolean result = false;
    if (t.height() != 0) {
        T root = t.disassemble(left, right);
        int compare = x.compareTo(root);
        if (compare > 0) {
            result = isInTree(right, x);
        } else if (compare < 0) {
            result = isInTree(left, x);
        } else {
            result = true;
        }
        t.assemble(root, left, right);
    }
    return result;
}

// Question 2:
public static <T extends Comparable<T>> void removeFromBST(BinaryTree<T> t, T x) {
    BinaryTree<T> left = t.newInstance();
    BinaryTree<T> right = t.newInstance();

    if (t.height() != 0) {
        T root = t.disassemble(left, right);
        int compare = x.compareTo(root);
        if (compare > 0) {
            removeFromBST(right, x);
            t.assemble(root, left, right);
        } else if (compare < 0) {
            removeFromBST(left, x);
            t.assemble(root, left, right);
        } else {
            if (right.height() == 0) {
                t.transferFrom(left);
            } else if (left.height() == 0) {
                t.transferFrom(right);
            } else {
                T successor = removeSmallest(right);
                t.assemble(successor, left, right);
            }
        }
    }
}

public static <T extends Comparable<T>> T removeSmallest(BinaryTree<T> t) {
    assert t != null : "Violation of: t is not null";
    assert t.height() != 0 : "Violation of: t is not empty";

    BinaryTree<T> left = t.newInstance();
    BinaryTree<T> right = t.newInstance();
    T root = t.disassemble(left, right);

    T ans;
    if (left.height() == 0) {
        ans = root;
        t.transferFrom(right);
    } else {
        ans = removeSmallest(left);
        t.assemble(root, left, right);
    }
    return ans;
}
