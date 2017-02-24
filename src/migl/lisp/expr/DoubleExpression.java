package migl.lisp.expr;

import migl.lisp.LispError;

/**
 * A double considered as an expression
 * 
 * @author xavierheugue
 */
public class DoubleExpression implements LispExpression {

    private Double value;

    public DoubleExpression(Double value) {
        this.value = value;
    }

    @Override
    public Object getEvaluation() throws LispError {
        return value;
    }

}
