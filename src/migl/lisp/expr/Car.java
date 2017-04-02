package migl.lisp.expr;

import migl.lisp.LispError;

/**
 * Décorateur d'une consexpression pour récupérer le premier argument
 * 
 * @author xavier
 *
 */
public class Car extends LispOperator {

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
        return cons.getCons().car().getEvaluation();
    }

    @Override
    public String getIdentifier() {
        return "car";
    }

}
