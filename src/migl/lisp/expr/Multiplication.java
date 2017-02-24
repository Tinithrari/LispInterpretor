package migl.lisp.expr;

import java.math.BigDecimal;

public class Multiplication extends LispOperator {

    @Override
    public Object getEvaluation() {
        BigDecimal resultat;
        if (this.getListe().size() < 2)
            throw new IllegalStateException("There's too few arguments for the multiplication");

        resultat = new BigDecimal(this.getListe().get(0).toString());
        this.getListe().remove(0);

        for (LispExpression e : this.getListe()) {
            resultat.multiply(new BigDecimal(e.getEvaluation().toString()));
        }
        return resultat;
    }

}
