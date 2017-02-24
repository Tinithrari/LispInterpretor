package migl.lisp.expr;

import java.math.BigDecimal;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;

public class Addition extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        BigDecimal somme = new BigDecimal(0);

        if (this.getListe().isEmpty())
            throw new IllegalStateException("An addition must have operands");
        if (this.getListe().size() < 2)
            throw new IllegalStateException("An addition needs at least two operands");

        for (LispExpression e : this.getListe()) {
            if (e.getEvaluation() instanceof LispBoolean)
                throw new IllegalStateException("An addition cannot contain a boolean type");

            somme = somme.add(new BigDecimal(e.getEvaluation().toString()));
        }
        return somme;
    }

}
