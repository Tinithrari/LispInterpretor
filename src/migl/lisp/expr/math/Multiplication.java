package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;

public class Multiplication extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal resultat;

        if (this.getListe().isEmpty())
            return 1;

        if (this.getListe().size() == 1)
            return this.getListe().get(0).getEvaluation();

        resultat = new BigDecimal(1);

        for (LispExpression e : this.getListe()) {
            BigDecimal number = new BigDecimal(e.getEvaluation().toString());
            resultat = resultat.multiply(number);
        }

        return resultat.scale() > 1 ? resultat.doubleValue() : resultat;
    }

}
