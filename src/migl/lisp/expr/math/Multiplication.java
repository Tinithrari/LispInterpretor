package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;
import migl.lisp.expr.LispVariableContainer;
import migl.lisp.expr.StringExpression;

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
            if (e instanceof StringExpression) {
                e = LispVariableContainer.get((String) e.getEvaluation());
            }
            BigDecimal number = new BigDecimal(e.getEvaluation().toString());
            resultat = resultat.multiply(number);
        }

        return resultat.scale() > 1 ? resultat.doubleValue() : resultat;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("(*");

        for (LispExpression e : this.getListe()) {
            builder.append(" ");
            builder.append(e.toString());
        }

        builder.append(")");

        return builder.toString();
    }

    @Override
    public String getIdentifier() {
        return "*";
    }

}
