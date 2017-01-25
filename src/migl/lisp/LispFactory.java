package migl.lisp;

/**
 * Simple factory to access the interpreted implementation.
 * 
 * @author leberre
 *
 */
public class LispFactory {

    private LispFactory() {
        // to prevent instance creation
    }

    /**
     * Create a new instance of the interpreter.
     * 
     * @return a new lisp interpreter.
     */
    public static Lisp makeIntepreter() {
        throw new UnsupportedOperationException();
    }
}
