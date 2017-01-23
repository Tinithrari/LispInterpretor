package migl.util;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Implementation of the interface {@link migl.util.ConsList}
 * 
 * @author xavier
 *
 * @param <E>
 *            Type contains by the ConsList
 */
public class ConsListImpl<E> implements ConsList<E> {

    private Cons<E, ConsListImpl<E>> head;

    /**
     * Default Constructor
     */
    public ConsListImpl() {
        this.head = null;
    }

    /**
     * Construct a ConsList with an element inside of it
     * 
     * @param e
     *            The element to add into the list
     */
    public ConsListImpl(E e) {
        this.head = new Cons<>(e, null);
    }

    /**
     * Constructor with a head field
     * 
     * @param head
     */
    private ConsListImpl(Cons<E, ConsListImpl<E>> head) {
        assert head != null;
        this.head = head;
    }

    @Override
    public Iterator<E> iterator() {
        if (this.isEmpty())
            throw new IllegalStateException("The list must be initialized to get an iterator");
        return new ConsListIterator<>(this);
    }

    @Override
    public ConsList<E> prepend(E e) {
        ConsList<E> newElt;

        // If head is not initialized, return
        if (head == null) {
            this.head = new Cons<>(e, null);
            return this;
        }

        // Create a new List header
        newElt = new ConsListImpl<>(new Cons<>(e, this));

        return newElt;
    }

    @Override
    public ConsList<E> append(E e) {
        ConsListImpl<E> newElt;

        if (this.head != null && this.head.cdr() != null) {
            this.head.cdr().append(e);
        } else {
            // If head is not initialized, return
            if (head == null) {
                this.head = new Cons<>(e, null);
            } else {
                newElt = new ConsListImpl<>(e);
                this.head = new Cons<>(this.head.car(), newElt);
            }
        }

        return this;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public E car() {
        return this.head == null ? null : this.head.car();
    }

    @Override
    public ConsList<E> cdr() {
        return (this.head == null) ? null : ((this.head.cdr() == null) ? this : this.head.cdr());
    }

    @Override
    public int size() {
        if (head == null)
            return 0;
        if (head.cdr() == null)
            return 1;
        return 1 + this.head.cdr().size();
    }

    @Override
    public <T> ConsList<T> map(Function<E, T> f) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        boolean first = true;
        ConsListImpl<E> ptr = this;
        buffer.append("(");

        for (; ptr != null && ptr.head != null; ptr = ptr.head.cdr()) {
            if (!first)
                buffer.append(", ");
            buffer.append(ptr.car());
            first = false;
        }

        buffer.append(")");
        return buffer.toString();
    }
}
