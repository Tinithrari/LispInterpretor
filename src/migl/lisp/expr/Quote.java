package migl.lisp.expr;

import migl.lisp.LispError;

/**
 * Chaine de caractère à ne pas évaluer
 * 
 * @author xavier
 *
 */
public class Quote extends LispOperator {

    @Override
    public void add(LispExpression expr) throws LispError {
        if (this.getListe().size() == 1)
            throw new LispError("Too much arguments");
        super.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        if (this.getListe().size() != 1)
            throw new LispError("Too few arguments");

        return this.getListe().get(0).toString();
    }

    @Override
    public String getIdentifier() {
        return "quote";
    }

}
