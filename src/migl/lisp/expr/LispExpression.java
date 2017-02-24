package migl.lisp.expr;

public interface LispExpression {
    Object getEvaluation();

    default void add(LispExpression expr) {
        throw new UnsupportedOperationException();
    }

    default void remove(LispExpression expr) {
        throw new UnsupportedOperationException();
    }
}
