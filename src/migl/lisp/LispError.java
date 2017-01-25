package migl.lisp;

/**
 * Generic exception for our lisp interpreter.
 * 
 * @author leberre
 *
 */
public class LispError extends Exception {

    /**
     * Fake serial version UID
     */
    private static final long serialVersionUID = 1L;

    public LispError() {
        super();
    }

    public LispError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LispError(String message, Throwable cause) {
        super(message, cause);
    }

    public LispError(String message) {
        super(message);
    }

    public LispError(Throwable cause) {
        super(cause);
    }

}
