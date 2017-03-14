package migl.lisp.expr;

import java.util.ArrayList;

import migl.lisp.LispError;

public class LambdaExecutor extends LispOperator {

    private Lambda lambda;
    private java.util.List<LispExpression> variables;

    public LambdaExecutor(Lambda lambda) {
        super();
        this.variables = new ArrayList<>();
        this.lambda = lambda;
    }

    @Override
    public void add(LispExpression expr) throws LispError {
        if (variables.size() == lambda.getListeVariable().size()) {
            throw new LispError("");
        }

        this.variables.add(expr);
    }

    @Override
    public Object getEvaluation() throws LispError {
        java.util.List<LispExpression> save = new ArrayList<>();
        Object resultat;

        if (variables.size() != lambda.getListeVariable().size()) {
            throw new LispError("Invalid number of arguments");
        }

        for (int i = 0; i < variables.size(); i++) {
            save.add(LispVariableContainer.get(lambda.getListeVariable().get(i)));
            LispVariableContainer.set(lambda.getListeVariable().get(i), variables.get(i));
        }

        resultat = this.lambda.getEvaluation();

        for (int i = 0; i < variables.size(); i++) {
            LispVariableContainer.set(lambda.getListeVariable().get(i), save.get(i));
        }

        variables.clear();

        return resultat;
    }

    @Override
    public String getIdentifier() {
        return lambda.getIdentifier();
    }

}
