package migl.lisp.expr;

import migl.lisp.LispError;

/**
 * Opérateur permettant l'accès aux membre droits du Cons
 * 
 * @author xavier
 *
 */
public class Cdr extends LispOperator {

    private ConsExpression cons;

    @Override
    public void add(LispExpression expr) throws LispError {
        if (!(expr instanceof ConsExpression)) {
            throw new LispError("");
        }

        this.cons = (ConsExpression) expr;
    }

    @Override
    public Object getEvaluation() throws LispError {
        return cons.getCons().cdr().getEvaluation();
    }

    @Override
    public String getIdentifier() {
        return "cdr";
    }

}
