package migl.lisp.expr.math;

import java.math.BigInteger;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;

public class BigIntegerExpression implements LispExpression {

    private BigInteger value;

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
