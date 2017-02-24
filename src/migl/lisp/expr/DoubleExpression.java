package migl.lisp.expr;

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
    public Object getEvaluation() {
        return value;
    }

}
