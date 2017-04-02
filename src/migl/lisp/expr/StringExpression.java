package migl.lisp.expr;

import migl.lisp.LispError;

/**
 * Expression contenant une chaine de caractère non reconnue
 * 
 * @author xavier
 *
 */
public class StringExpression implements LispExpression {

    private String value;

    /**
     * Créé l'expression
     * 
     * @param value
     *            Chaine de caractère
     */
    public StringExpression(String value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Object getEvaluation() throws LispError {
        return this.value;
    }

}
