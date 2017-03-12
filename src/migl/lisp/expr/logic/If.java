package migl.lisp.expr.logic;

import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;

public class If extends LispOperator {

    @Override
    public void add(LispExpression expr) throws LispError {

        if (this.getListe().size() == 0 && !(expr instanceof LispLogicExpression))
            throw new LispError("First argument must be a boolean expression");

        if (this.getListe().size() == 3)
            throw new LispError("Too much arguments");

        super.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        if (this.getListe().size() < 3)
            throw new LispError("Too few arguments");

        LispLogicExpression condition = (LispLogicExpression) this.getListe().get(0);
        LispExpression a = this.getListe().get(1);
        LispExpression b = this.getListe().get(2);

        return condition.getLogicEvaluation().value() ? a.getEvaluation() : b.getEvaluation();
    }

    @Override
    public String getIdentifier() {
        return "if";
    }
}
