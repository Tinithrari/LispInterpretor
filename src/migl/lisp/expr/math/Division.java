package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;

public class Division extends LispOperator {

    @Override
    public void add(LispExpression expr) throws LispError {
        if (this.getListe().size() == 2)
            throw new IllegalStateException("Too manny operands");
        super.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal dividende, diviseur;

        dividende = new BigDecimal(this.getListe().get(0).getEvaluation().toString());
        diviseur = new BigDecimal(this.getListe().get(1).getEvaluation().toString());

        if (diviseur.equals(BigDecimal.ZERO))
            throw new LispError("Division by zero");

        return dividende.scale() == 0 && diviseur.scale() == 0 ? dividende.divide(diviseur).intValue()
                : dividende.divide(diviseur).setScale(Math.max(dividende.scale(), diviseur.scale()));
    }

}
