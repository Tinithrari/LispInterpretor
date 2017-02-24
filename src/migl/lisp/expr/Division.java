package migl.lisp.expr;

import java.math.BigDecimal;

import migl.lisp.LispError;

public class Division extends LispOperator {

    @Override
    public void add(LispExpression expr) {
        if (this.getListe().size() == 2)
            throw new IllegalStateException("Too manny operands");
        super.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal dividende, diviseur;

        dividende = new BigDecimal(this.getListe().get(0).getEvaluation().toString());
        diviseur = new BigDecimal(this.getListe().get(1).getEvaluation().toString());

        if (diviseur.doubleValue() == 0d)
            throw new LispError("Division by zero");

        return dividende.precision() == 1 && diviseur.precision() == 1 ? dividende.divide(diviseur).intValue()
                : dividende.divide(diviseur);
    }

}
