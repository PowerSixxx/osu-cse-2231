import java.util.Comparator;

import components.queue.Queue;

/**
 * Inserts the given {@code T} in the {@code Queue<T>} sorted according to the
 * given {@code Comparator<T>} and maintains the {@code Queue<T>} sorted.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @param q
 *            the {@code Queue} to insert into
 * @param x
 *            the {@code T} to insert
 * @param order
 *            the {@code Comparator} defining the order for {@code T}
 * @updates q
 * @requires <pre>
 * IS_TOTAL_PREORDER([relation computed by order.compare method])  and
 * IS_SORTED(q, [relation computed by order.compare method])
 * </pre>
 * @ensures <pre>
 * perms(q, #q * <x>)  and
 * IS_SORTED(q, [relation computed by order.compare method])
 * </pre>
 */
private static <T> void insertInOrder(Queue<T> q, T x, Comparator<T> order) {
    // Create a temporary queue to store all the elements follow the rule of comparator
    Queue<T> temp = q.newInstance();
    while (q.length() != 0 && order.compare(x, q.front()) > 0) {
        temp.enqueue(q.dequeue());
    }
    temp.enqueue(x);
    temp.append(q);
    q.transferFrom(temp);
}

/**
 * Sorts {@code this} according to the ordering provided by the {@code compare}
 * method from {@code order}.
 *
 * @param order
 *            ordering by which to sort
 * @updates this
 * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
 * @ensures <pre>
 * perms(this, #this)  and
 * IS_SORTED(this, [relation computed by order.compare method])
 * </pre>
 */
public void sort(Comparator<T> order) {
    Queue<T> temp = this.newInstance();
    while (this.length() != 0) {
        insertInOrder(temp, this.dequeue(), order);
    }
    this.transferFrom(temp);
}
