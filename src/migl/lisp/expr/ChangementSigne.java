package migl.lisp.expr;

import java.math.BigDecimal;

public class ChangementSigne extends LispOperator {

    @Override
    public void add(LispExpression expr) {
        if (this.getListe().size() == 1)
            throw new IllegalStateException("Too many operands");
        super.add(expr);
    }

    @Override
    public Object getEvaluation() {
        BigDecimal value = new BigDecimal(this.getListe().get(0).getEvaluation().toString());

        return value.multiply(new BigDecimal(-1));
    }

}
