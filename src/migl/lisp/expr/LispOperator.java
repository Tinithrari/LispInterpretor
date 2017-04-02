package migl.lisp.expr;

import java.util.ArrayList;
import java.util.List;

import migl.lisp.LispError;

/**
 * Opérateur Lisp
 * 
 * @author xavier
 *
 */
public abstract class LispOperator implements LispExpression {

    private List<LispExpression> liste;

    /**
     * Constructeur pour initialiser la liste d'argument
     */
    public LispOperator() {
        this.liste = new ArrayList<>();
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
