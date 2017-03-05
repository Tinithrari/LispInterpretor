package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;
import migl.lisp.expr.StringExpression;
import migl.lisp.expr.logic.LispBooleanExpression;

public class Max extends LispOperator {

    @Override
    public void add(LispExpression expr) throws LispError {
        if (expr instanceof LispBooleanExpression || expr instanceof StringExpression)
            throw new IllegalArgumentException("The argument must be a number");
        super.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal a, b;
        if (this.getListe().size() != 2)
            throw new LispError("Invalid number of operands");

        a = new BigDecimal(this.getListe().get(0).getEvaluation().toString());
        b = new BigDecimal(this.getListe().get(1).getEvaluation().toString());

        return a.max(b);
    }

}
