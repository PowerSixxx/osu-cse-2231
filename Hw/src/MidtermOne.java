import components.binarytree.BinaryTree;
import components.map.Map.Pair;
import components.queue.Queue;
import components.sequence.SequenceSecondary;
import components.stack.Stack;

/**
 * This is for CSE 2231 Mid-term One review.
 */
public final class MidtermOne {
    /**
     * No argument constructor--private to prevent instantiation.
     */
    private MidtermOne() {

    }

    public class Sequence3<T> extends SequenceSecondary<T> {
        private Stack<T> left;
        private Stack<T> right;
    }

    /**
     *
     * @param <T>
     * @param left
     * @param right
     * @param newLeftLength
     */
    public static <T> void setLengthOfLeftStack(Stack<T> left, Stack<T> right,
            int newLeftLength) {
        // 递归的时候一定要注意：Base case!!!
        if (left.length() == newLeftLength) {
            return; // 虽然这是void，对于递归也要return
        } else if (left.length() > newLeftLength) {
            right.push(left.pop());
            setLengthOfLeftStack(left, right, newLeftLength);
        } else { //  (left.length() < newLeftLength)
            left.push(right.pop());
            setLengthOfLeftStack(left, right, newLeftLength);
        }
    }

    /**
     * If we don't use recursive method.
     *
     * @param <T>
     * @param left
     * @param right
     * @param newLeftLength
     */
    public static <T> void setLengthOfLeftStack2(Stack<T> left, Stack<T> right,
            int newLeftLength) {
        while (left.length() > newLeftLength) {
            right.push(left.pop());
        }
        while (left.length() < newLeftLength) {
            left.push(right.pop());
        }
    }

    @Override
    public void add(int pos, T x) {
        setLengthOfLeftLengthStack(this.left, this.right, pos);
        this.left.push(x);
    }

    @Override
    public T remove(int pos) {
        setLengthOfLeftLengthStack(this.left, this.right, pos);
        return this.right.pop();
    }

    @Override
    public int length() {
        return this.left.length() + this.right.length();
    }

    // Challenge: Implement flip in constant time (i.e., no loops!)
    @Override
    public void flip() {
        Stack<T> temp = this.left.newInstance();
        temp.transferFrom(this.left);
        this.left.transferFrom(this.right);
        this.right.transferFrom(temp);
    }

    // Map<Integer,T> 表示 Stack
    @Override
    public void push(T x) {
        int k = this.contents.size();
        this.contents.add(k, x);
    }

    @Override
    public T pop() {
        int k = this.contents.size() - 1;
        return this.contents.remove(k);
    }

    @Override
    public int length() {
        return this.contents.size();
    }

    @Override
    public Pair<K, V> removeAny() {
        int i = 0;
        while (i < this.hashTable.length() && this.hashTable[].size() == 0) {
            i++;
        }
        this.size--;
        return this.hashTable[i].removeAny();
    }

    @Override
    public boolean hasKey(K key) {
        int i = mod(key.hashCode(), this.hashTable.length());
        return this.hashTable[i].hasKey(key);
    }

    /**
     * Returns whether the int x is greater than all the labels in tree t.
     *
     * @param x
     * @param t
     * @ensures isGreaterThan = [true if x is greater than everything in t or if
     *          t is empty; false otherwise]
     * @return
     */
    private static boolean isGreaterThan(int x, BinaryTree<Integer> t) {
        assert t != null : "Violation of: t is not null";
        boolean ans = true;
        if (t.size() != 0) {
            BinaryTree<Integer> left = t.newInstance();
            BinaryTree<Integer> right = t.newInstance();
            int root = t.disassemble(left, right);
            if (x <= root) {
                ans = false;
            } else {
                ans = isGreaterThan(x, left) && isGreaterThan(x, right);
            }
            t.assemble(root, left, right);
        }
        return ans;
    }

    @Override
    public void add(int pos, T x) {
        setLengthOfLeftStack(this.left, this.right, pos);
        this.left.push(x);
    }

    @Override
    public T remove(int pos) {
        setLengthOfLeftStack(this.left, this.right, pos);
        return this.right.pop();
    }

    @Override
    public int length() {
        return this.left.length() + this.right.length();
    }

    /**
     * Finds {@code x} in {@code q} and, if such exists, moves it to the front
     * of {@code q}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be searched
     * @param x
     *            the entry to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if <x> is substring of q
     *  then <x> is prefix of q
     * </pre>
     */
    private static <T> void moveToFront(Queue<T> q, T x) {
        assert q != null : "Violation of: q is not null";
        Queue<T> left = q.newInstance();
        Queue<T> right = q.newInstance();
        boolean found = false;
        while (q.length() != 0) {
            T k = q.dequeue();
            if (k.equals(x) && !found) {
                left.enqueue(k);
                found = true;
            } else {
                right.enqueue(k);
            }
        }
        left.append(right);
        q.transferFrom(left);
    }

    @Override
    public T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        moveToFront(this.elements, x);
        T k = this.elements.dequeue();
        return k;
    }

    @Override
    public T removeAny() {
        assert this.size() > 0 : "Violation of: |this| > 0";
        return this.elements.dequeue();
    }

    @Override
    public boolean contains(T x) {
        assert x != null : "Violation of: x is not null";
        boolean ans = false;
        moveToFront(this.elements, x);
        T k = this.elements.dequeue();
        if (k.equals(x)) {
            ans = true
        }
        return ans;
    }

    @Override
    public int size() {
        return this.elements.length();
    }

    /**
     * Finds pair with first component {@code key} and, if such exists, moves it
     * to the front of {@code q}.
     *
     * @param <K>
     *            type of {@code Pair} key
     * @param <V>
     *            type of {@code Pair} value
     * @param q
     *            the {@code Queue} to be searched
     * @param key
     *            the key to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if there exists value: V (<(key, value)> is substring of q)
     *  then there exists value: V (<(key, value)> is prefix of q)
     * </pre>
     */
    private static <K, V> void moveToFront(Queue<Pair<K, V>> q, K key) {
        assert q != null : "Violation of: q is not null";
        assert key != null : "Violation of: key is not null";

        Queue<Pair<K, V>> left = q.newInstance();
        Queue<Pair<K, V>> right = q.newInstance();
        while (q.length() != 0) {
            Pair<K, V> p = q.dequeue();
            if (p.key().equals(key)) {
                left.enqueue(p);
            } else {
                right.enqueue(p);
            }
        }
        left.append(right);
        q.transferFrom(left);
    }

    @Override
    public void add(K key, V value) {
        assert key != null : "Violation of: key is not null";
        assert value != null : "Violation of: value is not null";
        assert !this.hasKey(key) : "Violation of: key is not in DOMAIN(this)";

        Pair<K, V> p = new SimplePair<>(key, value);
        this.pairsQueue.enqueue(p);
    }

    @Override
    public Pair<K, V> remove(K key) {
        assert key != null : "Violation of: key is not null";
        assert this.hasKey(key) : "Violation of: key is in DOMAIN(this)";
        moveToFront(this.pairsQueue, key);
        Pair<K, V> p = this.pairsQueue.dequeue();
        return p;
    }

    @Override
    public V value(K key) {
        assert key != null : "Violation of: key is not null";
        assert this.hasKey(key) : "Violation of: key is in DOMAIN(this)";

        moveToFront(this.pairsQueue, key);
        return this.pairsQueue.front().value();
    }

    @Override
    public boolean hasKey(K key) {
        assert key != null : "Violation of: key is not null";

        boolean found = false;
        moveToFront(this.pairsQueue, key);
        if (this.pairsQueue.length() != 0 && this.pairsQueue.front.key().equals(key)) {
            found = true;
        }
        return found;
    }

    @Override
    public void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";

        if (this.rep.equals("") && k == 0) {
            this.rep = "";
        } else {
            this.rep += Integer.toString(k);
        }
    }

    @Override
    public int divideBy10() {
        int result = 0;
        if (this.rep.equals("")) {
            this.rep = "";
        } else {
            result = this.rep.charAt(this.rep.length() - 1) + '0';
            this.rep = this.rep.subString(0, this.rep.length() - 1);
        }
        return result;
    }

    /**
     * Computes {@code a} mod {@code b} as % should have been defined to work.
     *
     * @param a
     *            the number being reduced
     * @param b
     *            the modulus
     * @return the result of a mod b, which satisfies 0 <= {@code mod} < b
     * @requires b > 0
     * @ensures <pre>
     * 0 <= mod  and  mod < b  and
     * there exists k: integer (a = k * b + mod)
     * </pre>
     */
    public static int mod(int a, int b) {
        assert b > 0 : "Violation of: b > 0";

        int ans = a % b;
        if (ans < 0) {
            ans += b;
        }
        return ans;
    }

    /**
     * Returns a hash code value for the given {@code String}.
     *
     * @param s
     *            the {@code String} whose hash code is computed
     * @return a hash code value for the given {@code String}
     * @ensures hashCode = [hash code value for the given String]
     */
    private static int hashCode(String s) {
        assert s != null : "Violation of: s is not null";

        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            ans += c;
        }
        return ans;
    }

    /**
     * Returns true if the given {@code T} is in the given {@code BinaryTree<T>}
     * or false otherwise.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to search
     * @param x
     *            the {@code T} to search for
     * @return true if the given {@code T} is in the given {@code BinaryTree},
     *         false otherwise
     * @ensures isInTree = [true if x is in t, false otherwise]
     */
    public static <T> boolean isInTree(BinaryTree<T> t, T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        boolean found = false;
        if (t.size() != 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);
            if (root.equals(x)) {
                found = true;
            } else {
                found = isInTree(left, x) || isInTree(right, x);
            }
            t.assemble(root, left, right);
        }
        return found;
    }

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
        boolean ans = false;
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        if (t.size() != 0) {
            T root = t.disassemble(left, right);
            int compare = x.compareTo(root);
            if (compare > 0) {
                ans = isInTree(right, x);
            } else if (compare < 0) {
                ans = isInTree(left, x);
            } else {
                ans = true;
            }
            t.assemble(root, left, right);
        }
        return ans;
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    public static <T> T removeSmallest(BinaryTree<T> t) {
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        T ans;
        if (t.height() != 0) {
            T root = t.disassemble(left, right);
            if (left.height() == 0) {
                ans = root;
                t.transferFrom(right);
            } else {
                ans = removeSmallest(left);
                t.assemble(root, left, right);
            }
        }
        return ans;
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    public static <T> T removeSmallest(BinaryTree<T> t) {
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        T ans;
        if (t.height() != 0) {
            T root = t.disassemble(left, right);
            if (left.height() == 0) {
                ans = root;
                t.transferFrom(right);
            } else {
                ans = removeSmallest(left);
                t.assemble(root, left, right);
            }
        }
        return ans;
    }

}
