package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispError;
import migl.lisp.expr.LispOperator;
import migl.lisp.expr.LispVariableContainer;
import migl.lisp.expr.StringExpression;

/**
 * Opérateur de changement de signe et de soustraction
 * 
 * @author xavier
 *
 */
public class ChangementSigne extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal value;
        if (this.getListe().get(0) instanceof StringExpression) {
            value = new BigDecimal(
                    LispVariableContainer.get((String) this.getListe().get(0).getEvaluation()).toString());
        } else {
            value = new BigDecimal(this.getListe().get(0).getEvaluation().toString());
        }

        if (this.getListe().size() > 2)
            throw new LispError("Invalid number of operands");

        if (this.getListe().size() == 1) {
            return value.multiply(new BigDecimal(-1));
        } else {
            if (this.getListe().get(1) instanceof StringExpression) {
                this.getListe().set(1, LispVariableContainer.get((String) this.getListe().get(1).getEvaluation()));
            }
            return value.subtract(new BigDecimal(this.getListe().get(1).getEvaluation().toString()));
        }
    }

    @Override
    public String getIdentifier() {
        return "-";
    }

}
