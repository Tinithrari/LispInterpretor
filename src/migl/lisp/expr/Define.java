package migl.lisp.expr;

import migl.lisp.LispError;

public class Define extends LispOperator {

    private StringExpression name;
    private LispExpression value;

    @Override
    public void add(LispExpression expr) throws LispError {
        if (!(expr instanceof StringExpression) && name == null) {
            LispOperator op = null;
            if (expr instanceof LispOperator)
                op = (LispOperator) expr;
            throw new LispError((op != null ? op.getIdentifier() : expr) + " is not a valid identifier");
        } else if (expr instanceof StringExpression && name == null) {
            this.name = (StringExpression) expr;
        } else if (name != null && value != null) {
            throw new LispError("too much operands");
        } else {
            this.value = expr;
        }
    }

    @Override
    public Object getEvaluation() throws LispError {
        if (this.name == null || this.value == null) {
            throw new LispError("too few operands");
        }

        LispVariableContainer.set((String) name.getEvaluation(),
                value instanceof Lambda ? new LambdaExecutor((Lambda) value) : value);

        return value instanceof Lambda ? value.toString() : value.getEvaluation();
    }

    @Override
    public String getIdentifier() {
        return "define";
    }

}
