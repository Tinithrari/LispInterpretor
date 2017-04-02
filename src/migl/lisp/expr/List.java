package migl.lisp.expr;

import migl.lisp.LispError;

/**
 * Liste d'élément lisp
 * 
 * @author xavier
 *
 */
public class List extends LispOperator {

    @Override
    public Object getEvaluation() throws LispError {
        StringBuilder builder = new StringBuilder();
        boolean first = true;

        builder.append("(");

        for (LispExpression e : this.getListe()) {
            if (!first)
                builder.append(" ");
            builder.append(e.getEvaluation());
            first = false;
        }
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String getIdentifier() {
        return "list";
    }

}
