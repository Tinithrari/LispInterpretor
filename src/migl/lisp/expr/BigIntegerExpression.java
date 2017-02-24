package migl.lisp.expr;

import java.math.BigInteger;

import migl.lisp.LispError;

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

}
