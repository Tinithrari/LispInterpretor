package migl.lisp.expr.logic;

import java.math.BigDecimal;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;
import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispOperator;
import migl.lisp.expr.StringExpression;

/**
 * Egalité en Lisp
 * 
 * @author xavier
 *
 */
public class Equals extends LispOperator implements LispLogicExpression {

    @Override
    public void add(LispExpression expr) throws LispError {
        if (expr instanceof LispLogicExpression || expr instanceof StringExpression)
            throw new LispError("Invalid Argument");

        if (this.getListe().size() == 2)
            throw new LispError("Too many arguments");

        super.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        if (this.getListe().size() != 2)
            throw new LispError("Too few arguments");
        return this.getLogicEvaluation();
    }

    @Override
    public LispBoolean getLogicEvaluation() throws LispError {
        BigDecimal a = new BigDecimal(this.getListe().get(0).getEvaluation().toString());
        BigDecimal b = new BigDecimal(this.getListe().get(1).getEvaluation().toString());

        return LispBoolean.valueOf(a.doubleValue() == b.doubleValue());
    }

    @Override
    public String getIdentifier() {
        return "=";
    }

}
