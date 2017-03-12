package migl.lisp.expr;

import java.util.ArrayList;
import java.util.List;

import migl.lisp.LispError;

public abstract class LispOperator implements LispExpression {

    private List<LispExpression> liste;

    public LispOperator() {
        this.liste = new ArrayList<LispExpression>();
    }

    @Override
    public void add(LispExpression expr) throws LispError {
        if (expr == null)
            throw new IllegalArgumentException("The expression must be non-null");
        liste.add(expr);
    }

    @Override
    public void remove(LispExpression expr) {
        if (expr == null)
            throw new IllegalArgumentException("The expression must be non-null");
        liste.remove(expr);
    }

    public List<LispExpression> getListe() {
        return liste;
    }

    public abstract String getIdentifier();
}
