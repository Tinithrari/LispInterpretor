package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;
import migl.lisp.expr.LispOperator;

public class Tan extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal number;
        Object eval;
        if (this.getListe().size() != 1)
            throw new LispError("Invalid number of operands");

        eval = this.getListe().get(0).getEvaluation();

        if (eval instanceof String || eval instanceof LispBoolean)
            throw new LispError("Invalid type for tan function");

        number = new BigDecimal(eval.toString());

        return number.precision() == 1 ? Math.tan(number.intValue()) : Math.tan(number.doubleValue());
    }

    @Override
    public String getIdentifier() {
        return "tan";
    }

}
