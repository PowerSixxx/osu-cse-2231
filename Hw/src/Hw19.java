import org.w3c.dom.Node;

;
/**
 * Retreats the position in {@code this} by one.
 *
 * @updates this
 * @requires this.left /= <>
 * @ensures <pre>
 * this.left * this.right = #this.left * #this.right  and
 * |this.left| = |#this.left| - 1
 * </pre>
 */
public void retreat() {
    Node lastNode = this.preFront;
    while (lastNode.next != this.lastLeft) {
        lastNode = lastNode.next;
    }
    this.lastLeft = lastNode;
    this.leftLength--;
    this.rightLength++;
}
