import components.map.Map.Pair;
import components.queue.Queue;

/**
 * Finds pair with first component {@code key} and, if such exists, moves it to
 * the front of {@code q}.
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
    Queue<Pair<K, V>> newQueue = q.newInstance();
    while (q.length() != 0 && !q.front().key().equals(key)) {
        newQueue.enqueue(q.dequeue());
    } // q will only remain what we want to search
    q.append(newQueue);
}
