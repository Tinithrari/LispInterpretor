package migl.lisp.expr.math;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;

/**
 * A double considered as an expression
 * 
 * @author xavierheugue
 */
public class DoubleExpression implements LispExpression {

    private Double value;

    /**
     * Constitue l'expression à partir d'un double
     * 
     * @param value
     */
    public DoubleExpression(Double value) {
        this.value = value;
    }

    @Override
    public Object getEvaluation() throws LispError {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
