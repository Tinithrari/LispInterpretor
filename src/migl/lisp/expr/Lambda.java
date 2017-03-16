package migl.lisp.expr;

import java.util.ArrayList;

import migl.lisp.LispError;

public class Lambda extends LispOperator {

    private java.util.List<String> listeVariable;
    private LispOperator operator;

    public Lambda() {
        super();
        this.listeVariable = new ArrayList<>();
    }

    @Override
    public void add(LispExpression expr) throws LispError {
        if (operator != null) {
            operator.add(expr);
        } else {
            if (expr instanceof StringExpression) {
                listeVariable.add((String) expr.getEvaluation());
            } else if (!(expr instanceof LispOperator)) {
                throw new LispError("");
            } else {
                if (!(expr instanceof Lambda))
                    operator = (LispOperator) expr;
                else
                    operator = new LambdaExecutor((Lambda) expr);
            }
        }
    }

    public java.util.List<String> getListeVariable() {
        return listeVariable;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean firstVar = true;

        builder.append("lambda (");

        for (int i = 0; i < listeVariable.size(); i++) {
            if (!firstVar)
                builder.append(" ");
            builder.append(listeVariable.get(i));
            firstVar = false;
        }

        builder.append(") ");

        builder.append(operator.toString());

        return builder.toString();
    }

    @Override
    public Object getEvaluation() throws LispError {
        return operator.getEvaluation();
    }

    @Override
    public String getIdentifier() {
        return "lambda";
    }

}
