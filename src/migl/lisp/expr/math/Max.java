package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;
import migl.lisp.expr.LispVariableContainer;
import migl.lisp.expr.StringExpression;
import migl.lisp.expr.logic.LispBooleanExpression;

public class Max extends LispOperator {

    @Override
    public void add(LispExpression expr) throws LispError {
        if (expr instanceof LispBooleanExpression)
            throw new IllegalArgumentException("The argument must be a number");
        super.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal a, b;
        if (this.getListe().size() != 2)
            throw new LispError("Invalid number of operands");

        if (this.getListe().get(0) instanceof StringExpression) {
            this.getListe().set(0, LispVariableContainer.get((String) this.getListe().get(0).getEvaluation()));
        }

        if (this.getListe().get(1) instanceof StringExpression) {
            this.getListe().set(1, LispVariableContainer.get((String) this.getListe().get(1).getEvaluation()));
        }

        a = new BigDecimal(this.getListe().get(0).getEvaluation().toString());
        b = new BigDecimal(this.getListe().get(1).getEvaluation().toString());

        return a.max(b);
    }

    @Override
    public String getIdentifier() {
        return "max";
    }

}
