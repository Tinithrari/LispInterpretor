package migl.lisp.expr;

import java.math.BigDecimal;

public class Division extends LispOperator {

    @Override
    public void add(LispExpression expr) {
        if (this.getListe().size() == 2)
            throw new IllegalStateException("Too manny operands");
        super.add(expr);
    }

    @Override
    public Object getEvaluation() {
        BigDecimal dividende, diviseur;

        dividende = new BigDecimal(this.getListe().get(0).getEvaluation().toString());
        diviseur = new BigDecimal(this.getListe().get(1).getEvaluation().toString());

        if (diviseur.equals(0))
            throw new IllegalStateException("Division by zero");

        return dividende.divide(diviseur);
    }

}
