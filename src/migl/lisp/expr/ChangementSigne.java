package migl.lisp.expr;

import java.math.BigDecimal;

import migl.lisp.LispError;

public class ChangementSigne extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal value = new BigDecimal(this.getListe().get(0).getEvaluation().toString());

        if (this.getListe().size() > 2)
            throw new LispError("Invalid number of operands");

        if (this.getListe().size() == 1)
            return value.multiply(new BigDecimal(-1));
        else
            return value.subtract(new BigDecimal(this.getListe().get(1).getEvaluation().toString()));
    }

}
