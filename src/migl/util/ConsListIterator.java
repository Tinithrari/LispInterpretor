package migl.util;

import java.util.Iterator;

/**
 * An iterator for the conslist
 * 
 * @author xavier_heugue
 *
 * @param <E>
 *            Type contains by the conslist
 */
public class ConsListIterator<E> implements Iterator<E> {

    private ConsList<E> cursor;

    /**
     * constructor of the conslist
     * 
     * @param list
     *            The list to iterate
     */
    public ConsListIterator(ConsList<E> list) {
        if (list == null || list.isEmpty())
            throw new IllegalArgumentException("The list must be initialized and not empty");

        // Initialize the list reference
        this.cursor = list;
    }

    @Override
    public boolean hasNext() {
        return this.cursor.cdr() != null;
    }

    @Override
    public E next() {
        // If there is no next element, throw exception
        if (!this.hasNext())
            throw new IllegalStateException("The list has been iterate");

        // Update the cursor
        this.cursor = this.cursor.cdr();

        // Return the element
        return this.cursor.car();
    }

}
