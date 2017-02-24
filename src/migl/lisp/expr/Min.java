package migl.lisp.expr;

import java.math.BigDecimal;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;

public class Min extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal a, b;
        Object eval1, eval2;
        if (this.getListe().size() != 2)
            throw new LispError("Invalid number of operands");

        eval1 = this.getListe().get(0).getEvaluation();
        eval2 = this.getListe().get(1).getEvaluation();

        if (eval1 instanceof LispBoolean || eval1 instanceof String || eval2 instanceof LispBoolean
                || eval2 instanceof String)
            throw new LispError("Invalid parameters for min");

        a = new BigDecimal(this.getListe().get(0).getEvaluation().toString());
        b = new BigDecimal(this.getListe().get(1).getEvaluation().toString());

        if (a.precision() == 1 && b.precision() == 1) {
            return Math.min(a.intValue(), b.intValue());
        } else if (a.precision() != 1 && b.precision() != 1) {
            return Math.min(a.doubleValue(), b.doubleValue());
        }
        throw new LispError("Arguments for min are not of the same type");
    }

}
