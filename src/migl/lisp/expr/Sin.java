package migl.lisp.expr;

import java.math.BigDecimal;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;

public class Sin extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal number;
        Object eval;
        if (this.getListe().size() != 1)
            throw new LispError("Invalid number of operands");

        eval = this.getListe().get(0).getEvaluation();

        if (eval instanceof String || eval instanceof LispBoolean)
            throw new LispError("Invalid type for sin function");

        number = new BigDecimal(eval.toString());

        return number.precision() == 1 ? Math.sin(number.intValue()) : Math.sin(number.doubleValue());
    }

}
