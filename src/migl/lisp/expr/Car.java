package migl.lisp.expr;

import migl.lisp.LispError;

public class Car extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getIdentifier() {
        return "car";
    }

}
