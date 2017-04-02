package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;
import migl.lisp.expr.LispVariableContainer;
import migl.lisp.expr.StringExpression;

/**
 * Division en Lisp
 * 
 * @author xavier
 *
 */
public class Division extends LispOperator {

    @Override
    public void add(LispExpression expr) throws LispError {
        if (this.getListe().size() == 2)
            throw new LispError("Invalid number of operands");
        super.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal dividende;
        BigDecimal diviseur;

        if (this.getListe().get(0) instanceof StringExpression) {
            this.getListe().set(0, LispVariableContainer.get((String) this.getListe().get(0).getEvaluation()));
        }
        if (this.getListe().get(1) instanceof StringExpression) {
            this.getListe().set(1, LispVariableContainer.get((String) this.getListe().get(1).getEvaluation()));
        }

        dividende = new BigDecimal(this.getListe().get(0).getEvaluation().toString());
        diviseur = new BigDecimal(this.getListe().get(1).getEvaluation().toString());

        if (diviseur.equals(BigDecimal.ZERO))
            throw new LispError("Division by zero");

        return dividende.scale() == 0 && diviseur.scale() == 0 ? dividende.divide(diviseur).intValue()
                : dividende.divide(diviseur).setScale(Math.max(dividende.scale(), diviseur.scale()));
    }

    @Override
    public String getIdentifier() {
        return "/";
    }

}
