package migl.lisp.expr.logic;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;

/**
 * Représente le NON logique en Lisp
 * 
 * @author xavier
 *
 */
public class Not extends LispOperator implements LispLogicExpression {

    @Override
    public void add(LispExpression expr) throws LispError {
        if (!(expr instanceof LispLogicExpression))
            throw new LispError("Operator should be a boolean expression");
        if (this.getListe().size() == 1)
            throw new LispError("Too many operands");
        super.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        if (this.getListe().isEmpty())
            throw new LispError("Not must have one operand");
        return this.getLogicEvaluation();
    }

    @Override
    public LispBoolean getLogicEvaluation() throws LispError {
        LispLogicExpression expr = (LispLogicExpression) this.getListe().get(0);
        return (expr.getLogicEvaluation()).value() ? LispBoolean.FALSE : LispBoolean.TRUE;
    }

    @Override
    public String getIdentifier() {
        return "not";
    }

}
