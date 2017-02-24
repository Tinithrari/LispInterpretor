package migl.lisp.expr;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;

public class LispBooleanExpression implements LispExpression {

    private LispBoolean value;

    public LispBooleanExpression(LispBoolean value) {
        this.value = value;
    }

    @Override
    public Object getEvaluation() throws LispError {
        return value;
    }

}
