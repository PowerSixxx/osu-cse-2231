import components.queue.Queue;

/**
 * Finds {@code x} in {@code q} and, if such exists, moves it to the front of
 * {@code q}.
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

    Queue<T> left = q.newInstance();
    Queue<T> right = q.newInstance();

    while (q.length() != 0) {
        T digit = q.dequeue();
        if (digit.equals(x)) {
            left.enqueue(digit);
        } else {
            right.enqueue(digit);
        }
    }
    left.append(right);
    q.transferFrom(left);
}
