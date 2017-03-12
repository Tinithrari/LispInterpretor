package migl.lisp.expr;

import java.util.HashMap;

public class LispVariableContainer {

    private static HashMap<String, LispExpression> variables;

    private LispVariableContainer() {
    }

    public static void init() {
        variables = new HashMap<String, LispExpression>();
    }

    public static void set(String name, LispExpression value) {
        if (variables == null) {
            variables = new HashMap<String, LispExpression>();
        }

        variables.put(name, value);
    }

    public static LispExpression get(String name) {
        if (variables == null) {
            variables = new HashMap<String, LispExpression>();
        }

        return variables.get(name);
    }

}
