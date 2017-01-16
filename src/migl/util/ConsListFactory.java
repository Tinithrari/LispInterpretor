package migl.util;

/**
 * Factory to create new lists.
 * 
 * The methods take advantage of type inference to simplify the use of the
 * methods in the user code.
 * 
 * The body of the methods must be completed by the students.
 * 
 * @author leberre
 *
 */
public final class ConsListFactory {

    private ConsListFactory() {
        // pour empêcher la création d'instances de cette classe
    }

    /**
     * Create a new empty list.
     * 
     * @return an empty list
     */
    public static <T> ConsList<T> nil() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     * Create a new list containing a single element
     * 
     * @param t
     *            an object
     * @return a list containing only t
     */
    public static <T> ConsList<T> signleton(T t) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     * Create a new list containing the elements given in parameter
     * 
     * @param ts
     *            a variable number of elements
     * @return a list containing those elements
     */
    @SafeVarargs
    public static <T> ConsList<T> singleton(T... ts) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
