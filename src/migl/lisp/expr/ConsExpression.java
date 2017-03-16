package migl.lisp.expr;

import migl.lisp.LispError;
import migl.util.Cons;

public class ConsExpression extends LispOperator {

    private Cons<LispExpression, LispExpression> cons;
    private int nbElt;

    public ConsExpression() {
        cons = Cons.nil();
        nbElt = 0;
    }

    @Override
    public void add(LispExpression expr) throws LispError {
        switch (nbElt) {
        case 0:
            cons = new Cons<LispExpression, LispExpression>(expr, null);
            break;
        case 1:
            if (expr != null)
                cons = new Cons<LispExpression, LispExpression>(cons.car(), expr);
            break;
        default:
            throw new LispError("Too much arguments");

        }
        nbElt++;
    }

    @Override
    public Object getEvaluation() throws LispError {
        if (nbElt < 2)
            throw new LispError("Invalid number of operands");

        StringBuilder builder = new StringBuilder("");

        if (cons.car() == null)
            builder.append("(()");
        else
            builder.append("(" + cons.car().getEvaluation());

        if (cons.cdr() != null) {
            ConsExpression expr2 = null;

            if (cons.cdr() instanceof ConsExpression)
                expr2 = (ConsExpression) cons.cdr();

            if (expr2 != null && expr2.cons.car() != null && expr2.cons.cdr() == null) {
                builder.append(" ");
                builder.append(expr2.cons.car().getEvaluation());
            } else {
                builder.append(" . ");
                builder.append(cons.cdr().getEvaluation());
            }
        }

        builder.append(")");

        return builder.toString();
    }

    public Cons<LispExpression, LispExpression> getCons() {
        return cons;
    }

    @Override
    public String getIdentifier() {
        return "cons";
    }

}
