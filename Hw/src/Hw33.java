@Override
public final boolean equals(Object obj) {
    if (obj == this) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (!(obj instanceof Queue<?>)) {
        return false;
    }
    Queue<?> q = (Queue<?>) obj;
    if (this.lengthOfLine() != q.length()) {
        return false;
    }
    Iterator<T> it1 = this.iterator();
    Iterator<?> it2 = q.iterator();
    while (it1.hasNext()) {
        T x1 = it1.next();
        Object x2 = it2.next();
        if (!x1.equals(x2)) {
            return false;
        }
    }
    return true;
}

@Override
public int hashCode() {
//    final int samples = 2;
//    final int a = 37;
//    final int b = 17;
//    int result = 0;
//    int n = 0;
//    Iterator<T> it = this.iterator();
//    while (n < samples && it.hasNext()) {
//        n++;
//        T x = it.next();
//        result = a * result + b * x.hashCode();
//    }
//    return result;
    int h = 0;
    Iterator<T> i = iterator();
    while (i.hasNext()) {
        T obj = i.next();
        if (obj != null) {
            h += obj.hashCode();
        }
    }
    return h;
}

@Override
public String toString() {
    StringBuilder result = new StringBuilder("<");
    Iterator<T> it = this.iterator();
    while (it.hasNext()) {
        result.append(it.next());
        if (it.hasNext()) {
            result.append(",");
        }
    }
    result.append(">");
    return result.toString();//abc
    // <a, b, c> -> a, b, c
}

/**
 * Find the position of the {@code entry} in {@code this}
 *
 * @param entry
 *            the entry being looked for
 * @return the position of the {@code entry} in {@code this}
 * @requires
 *
 *           <pre>
 * {@code  this /= <>}
 *           </pre>
 *
 * @ensures
 *
 *          <pre>
 *
 * {@code position = position of customer in this}
 *          </pre>
 */
@Override
public int findThePosition(T entry) {

    int length = this.lengthOfLine();
    int position = 0;
    for (int i = 0; i < length; i++) {
        if (this.frontLine().equals(entry)) {
            position = i;
        }
        // Consume the first element and make it to the last element
        this.addLine(this.removeFrontLine());
    }
    return position;

}

/**
 * Replaces the entry in {@code this} at position {@code pos} with {@code x} ,
 * and returns the old entry.
 *
 * @param pos
 *            the position to replace
 * @param x
 *            the new entry at position {@code pos}
 * @return the old entry at position {@code pos}
 * @aliases reference {@code x}
 * @updates this
 * @clear x
 * @requires
 *
 *           <pre>
 * {@code  this /= <>, 0 <= pos and pos < |this|}
 *           </pre>
 *
 * @ensures
 *
 *          <pre>
 * {@code this = #this[0, pos) * <x> * #this[pos+1, |#this|) and
 * <replaceEntry> = #this[pos, pos+1)}
 *          </pre>
 */
@Override
public T replaceEntry(int pos, T x) {
    T removed = null;
    int length = this.lengthOfLine();
    for (int i = 0; i < length; i++) {
        if (i == pos) {
            removed = this.removeFrontLine();
            this.addLine(x);
        } else {
            this.addLine(this.removeFrontLine());
        }

    }
    return removed;

}
