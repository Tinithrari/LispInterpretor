package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;
import migl.lisp.expr.LispOperator;

public class Cos extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal number;
        Object eval;
        if (this.getListe().size() != 1)
            throw new LispError("Invalid number of operands");

        eval = this.getListe().get(0).getEvaluation();

        if (eval instanceof String || eval instanceof LispBoolean)
            throw new LispError("Invalid type for cos function");

        number = new BigDecimal(eval.toString());

        return number.precision() == 1 ? Math.cos(number.intValue()) : Math.cos(number.doubleValue());
    }

}
