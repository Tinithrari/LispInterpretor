package migl.lisp.expr.math;

import java.math.BigInteger;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;

/**
 * Expression lisp contenant un bigint
 * 
 * @author xavier
 *
 */
public class BigIntegerExpression implements LispExpression {

    private BigInteger value;

    /**
     * Construit une expression à partir d'un big int
     * 
     * @param value
     *            Le bigint constituant l'expression
     */
    public BigIntegerExpression(BigInteger value) {
        super();
        this.value = value;
    }

    @Override
    public Object getEvaluation() throws LispError {
        return value.intValue();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
