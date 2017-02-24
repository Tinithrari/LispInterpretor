package migl.lisp.expr;

import java.math.BigDecimal;

import migl.lisp.LispError;

public class Multiplication extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal resultat;
        if (this.getListe().size() < 2)
            throw new IllegalStateException("There's too few arguments for the multiplication");

        resultat = new BigDecimal(this.getListe().get(0).getEvaluation().toString());
        this.getListe().remove(0);

        for (LispExpression e : this.getListe()) {
            resultat = resultat.multiply(new BigDecimal(e.getEvaluation().toString()));
        }
        return resultat;
    }

}
