package migl.lisp.expr.logic;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;

public class LispBooleanExpression implements LispExpression, LispLogicExpression {

    private LispBoolean value;

    public LispBooleanExpression(LispBoolean value) {
        this.value = value;
    }

    @Override
    public Object getEvaluation() throws LispError {
        return getLogicEvaluation();
    }

    @Override
    public LispBoolean getLogicEvaluation() throws LispError {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
