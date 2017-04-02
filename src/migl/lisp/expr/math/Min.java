package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;
import migl.lisp.expr.LispOperator;
import migl.lisp.expr.LispVariableContainer;
import migl.lisp.expr.StringExpression;

/**
 * Opérateur minimum en Lisp
 * 
 * @author xavier
 *
 */
public class Min extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal a;
        BigDecimal b;
        Object eval1;
        Object eval2;
        if (this.getListe().size() != 2)
            throw new LispError("Invalid number of operands");

        if (this.getListe().get(0) instanceof StringExpression) {
            this.getListe().set(0, LispVariableContainer.get((String) this.getListe().get(0).getEvaluation()));
        }

        if (this.getListe().get(1) instanceof StringExpression) {
            this.getListe().set(1, LispVariableContainer.get((String) this.getListe().get(1).getEvaluation()));
        }

        eval1 = this.getListe().get(0).getEvaluation();
        eval2 = this.getListe().get(1).getEvaluation();

        if (eval1 instanceof LispBoolean || eval1 instanceof String || eval2 instanceof LispBoolean
                || eval2 instanceof String)
            throw new LispError("Invalid parameters for min");

        a = new BigDecimal(eval1.toString());
        b = new BigDecimal(eval2.toString());

        if (a.scale() == 0 && b.scale() == 0) {
            return Math.min(a.intValue(), b.intValue());
        } else if (a.precision() != 0 && b.precision() != 0) {
            return Math.min(a.doubleValue(), b.doubleValue());
        }
        throw new LispError("Arguments for min are not of the same type");
    }

    @Override
    public String getIdentifier() {
        return "min";
    }

}
