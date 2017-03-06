package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;
import migl.lisp.expr.LispOperator;

public class Pow extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal a, b;
        Object eval1, eval2;
        if (this.getListe().size() != 2)
            throw new LispError("Invalid number of operands");

        eval1 = this.getListe().get(0).getEvaluation();
        eval2 = this.getListe().get(1).getEvaluation();

        if (eval1 instanceof String || eval1 instanceof LispBoolean || eval2 instanceof String
                || eval2 instanceof LispBoolean)
            throw new LispError("Invalid type for pow function");

        a = new BigDecimal(eval1.toString());
        b = new BigDecimal(eval2.toString());

        try {
            b = new BigDecimal(b.intValueExact());
        } catch (ArithmeticException e) {
            throw new LispError("The second member of pow must be an integer", e);
        }

        return a.pow(b.intValue()).doubleValue();
    }

}
