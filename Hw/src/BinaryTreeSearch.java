import components.binarytree.BinaryTree;

public static int removeSmallest(BinaryTree<Integer> t) {
    BinaryTree<Integer> left = t.newInstance();
    BinaryTree<Integer> right = t.newInstance();
    int root = t.disassemble(left, right);
    int ans;
    if (left.size() == 0) { // 左边子树为empty
        ans = root;
        t.transferFrom(right); // 把右边子树移上来
    } else {
        ans = removeSmallest(left); // 否则，递归移除左边的最小值
        t.assemble(root, left, right);
    }
    return ans;
}
