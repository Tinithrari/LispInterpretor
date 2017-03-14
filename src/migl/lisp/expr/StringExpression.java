package migl.lisp.expr;

import migl.lisp.LispError;

public class StringExpression implements LispExpression {

    private String value;

    public StringExpression(String value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Object getEvaluation() throws LispError {
        return this.value;
    }

}
