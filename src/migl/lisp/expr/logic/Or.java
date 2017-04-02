package migl.lisp.expr.logic;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;

/**
 * OU logique en Lisp
 * 
 * @author xavier
 *
 */
public class Or extends LispOperator implements LispLogicExpression {

    @Override
    public void add(LispExpression expr) throws LispError {
        if (!(expr instanceof LispLogicExpression))
            throw new LispError("Operator should be a boolean");
        if (this.getListe().size() == 2)
            throw new LispError("Too many operands");
        super.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        if (this.getListe().size() != 2)
            throw new LispError("Too few operands");

        return getLogicEvaluation();
    }

    @Override
    public LispBoolean getLogicEvaluation() throws LispError {
        LispLogicExpression a = (LispLogicExpression) this.getListe().get(0);
        LispLogicExpression b = (LispLogicExpression) this.getListe().get(1);

        return (a.getLogicEvaluation()).value() || (b.getLogicEvaluation()).value() ? LispBoolean.TRUE
                : LispBoolean.FALSE;
    }

    @Override
    public String getIdentifier() {
        return "or";
    }

}
