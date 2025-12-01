package components.waitingline;

import java.util.Iterator;

public abstract class WaitingLineSecondary<T> implements WaitingLine<T> {

    private static <T> void rotate(WaitingLine<T> q, int n) {
        for (int i = 0; i < n; i++) {
            T t = q.removeFromLine();
            q.addToLine(t);
        }
    }

    @Override
    public void append(WaitingLine<T> q) {
        while (q.length() > 0) {
            T t = q.removeFromLine();
            this.addToLine(t);
        }
        q.clear();
    }

    @Override
    public int pos(T x) {
        int i = 0;
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            if (it.next().equals(x)) {
                return i;
            }
            i++;
        }
        return 0;
    }

    @Override
    public void insertAtFront(T x) {
        this.addToLine(x);
        rotate(this, this.length() - 1);
    }

    @Override
    public void moveToFront(T x) {
        T removed = this.remove(x);
        this.insertAtFront(removed);
    }

    //String a = "hello"
    //a.equals("string")
    //a.equals(queue)
    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        /*
         * you can not write: null.equals(obj) OR you can not write:
         * b.equals(null)
         */
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WaitingLine<?>)) {
            return false;
        }
        WaitingLine<?> wObj = (WaitingLine<?>) obj;
        if (this.length() != wObj.length()) {
            return false;
        }
        Iterator<T> itThis = this.iterator();
        Iterator<?> itObj = wObj.iterator();
        while (itThis.hasNext()) {
            if (!itThis.next().equals(itObj.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        Iterator<T> it = this.iterator();
        int hash = Integer.MIN_VALUE;
        while (it.hasNext()) {
            hash += it.next().hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("<");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            str.append(it.next().toString());
            if (it.hasNext()) {
                str.append(", ");
            }
        }
        str.append(">");
        return str.toString();
    }
    // for (String s : s)
}
