package migl.lisp.expr.math;

import java.math.BigDecimal;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;
import migl.lisp.expr.LispVariableContainer;
import migl.lisp.expr.StringExpression;
import migl.lisp.expr.logic.LispLogicExpression;

/**
 * Addition en Lisp
 * 
 * @author xavier
 *
 */
public class Addition extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal somme = new BigDecimal(0);

        if (this.getListe().isEmpty())
            return 0;
        if (this.getListe().size() < 2)
            return this.getListe().get(0).getEvaluation();

        for (LispExpression e : this.getListe()) {
            if (e.getEvaluation() instanceof LispLogicExpression)
                throw new IllegalStateException("An addition cannot contain a boolean type");
            else if (e instanceof StringExpression) {
                e = LispVariableContainer.get((String) e.getEvaluation());
            }

            somme = somme.add(new BigDecimal(e.getEvaluation().toString()));
        }
        return somme;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("(+");

        for (LispExpression e : this.getListe()) {
            builder.append(" ");
            builder.append(e.toString());
        }

        builder.append(")");

        return builder.toString();
    }

    @Override
    public String getIdentifier() {
        return "+";
    }
}
