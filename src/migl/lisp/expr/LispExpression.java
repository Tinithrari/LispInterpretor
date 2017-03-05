package migl.lisp.expr;

import migl.lisp.LispError;

public interface LispExpression {
    Object getEvaluation() throws LispError;

    default void add(LispExpression expr) throws LispError {
        throw new UnsupportedOperationException();
    }

    default void remove(LispExpression expr) {
        throw new UnsupportedOperationException();
    }
}
