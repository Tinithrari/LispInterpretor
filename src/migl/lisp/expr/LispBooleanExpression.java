package migl.lisp.expr;

import migl.lisp.LispBoolean;

public class LispBooleanExpression implements LispExpression {

    private LispBoolean value;

    public LispBooleanExpression(LispBoolean value) {
        this.value = value;
    }

    @Override
    public Object getEvaluation() {
        return value;
    }

}
