package migl.lisp.expr;

import java.util.HashMap;

/**
 * Environnement de stockage des variables lisp
 * 
 * @author xavier
 *
 */
public class LispVariableContainer {

    private static HashMap<String, LispExpression> variables;

    private LispVariableContainer() {
    }

    /**
     * Initialise la structure contenant les variables
     */
    public static void init() {
        variables = new HashMap<>();
    }

    /**
     * Définit une variable
     * 
     * @param name
     *            nom de la variable
     * @param value
     *            valeur de la variable
     */
    public static void set(String name, LispExpression value) {
        if (variables == null) {
            init();
        }

        variables.put(name, value);
    }

    /**
     * Récupère la valeur d'une variable
     * 
     * @param name
     *            Nom de la variable
     * @return Valeur de la variable
     */
    public static LispExpression get(String name) {
        if (variables == null) {
            init();
        }

        return variables.get(name);
    }

}
