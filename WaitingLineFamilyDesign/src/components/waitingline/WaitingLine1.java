package components.waitingline;
import java.util.Iterator;

import components.sequence.Sequence;
import components.sequence.Sequence2L;

public class WaitingLine1<T> extends WaitingLineSecondary<T> {

    private Sequence<T> rep;

    public WaitingLine1() {
        this.createNewRep();
    }

    private void createNewRep() {
        this.rep = new Sequence2L<>();
    }

    @Override
    public T front() {
        return this.rep.entry(0);
    }

    @Override
    public void addToLine(T x) {
        this.rep.add(this.rep.length(), x);
    }

    @Override
    public T removeFromLine() {
        return this.rep.remove(0);
    }

    @Override
    public int length() {
        return this.rep.length();
    }

    @Override
    public boolean contains(T x) {
        Iterator<T> it = this.rep.iterator();
        while (it.hasNext()) {
            T t = it.next();
            if (t.equals(x)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(T x) {
        Iterator<T> it = this.rep.iterator();
        int i = 0;
        while (it.hasNext()) {
            T t = it.next();
            if (t.equals(x)) {
                this.rep.remove(i);
                return t;
            }
            i++;
        }
        return null;
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public WaitingLine<T> newInstance() {
        return new WaitingLine1<T>();
    }

    @Override
    public void transferFrom(WaitingLine<T> b) {
        WaitingLine1<T> localB = (WaitingLine1<T>) b;
        this.rep = localB.rep;
        localB.createNewRep();
    }

    @Override
    public Iterator<T> iterator() {
        return this.rep.iterator();
    }

    @Override
    public void insertAtFront(T x) {
        this.rep.add(0, x);
    }
}
