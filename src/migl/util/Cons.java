package migl.util;

/**
 * Building block for implementing lists.
 * 
 * A "cons" is simply a pair (L,R) holding a specific value on the left hand
 * side (L) and the right hand side (R).
 * 
 * @author leberre
 *
 * @param <L>
 *            the type of the left hand side of the pair
 * @param <R>
 *            the type of the right hand side of the pair
 */
public class Cons<L, R> {

    /**
     * The left hand side of the pair.
     */
    private final L car;

    /**
     * The right hand side of the pair.
     */
    private final R cdr;

    /**
     * Private default constructor to prevent its use outside the class.
     */
    private Cons() {
        this.car = null;
        this.cdr = null;
    }

    /**
     * Generic constructor for the cons data structure.
     * 
     * @param car
     *            the left hand side.
     * @param cdr
     *            the right hand side.
     */
    public Cons(L car, R cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    /**
     * Retrieve the left hand side of the pair.
     * 
     * @return the left hand side of the pair if any.
     */
    public L car() {
        return car;
    }

    /**
     * Retrieve the right hand side of the pair.
     * 
     * @return the right hand side of the pair if any.
     */
    public R cdr() {
        return cdr;
    }

    /**
     * Typical textual "dotted" representation of a cons : ( L . R ).
     * 
     * @return a dotted textual representation of the cons pair.
     */
    @Override
    public String toString() {
        return "( " + car + " . " + cdr + " )";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((car == null) ? 0 : car.hashCode());
        result = prime * result + ((cdr == null) ? 0 : cdr.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cons<?, ?> other = (Cons<?, ?>) obj;
        if (car == null) {
            if (other.car != null)
                return false;
        } else if (!car.equals(other.car))
            return false;
        if (cdr == null) {
            if (other.cdr != null)
                return false;
        } else if (!cdr.equals(other.cdr))
            return false;
        return true;
    }

    /**
     * Uses type inference to build an empty cons of the expected type.
     * 
     * @return an empty cons of the expected type.
     */
    public static final <U, V> Cons<U, V> nil() {
        return new Cons<>();
    }

}
