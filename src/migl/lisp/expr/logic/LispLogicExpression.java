package migl.lisp.expr.logic;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;

/**
 * Expression logique Lisp
 * 
 * @author xavier
 *
 */
@FunctionalInterface
public interface LispLogicExpression {
    /***
     * Récupère la valeur booléene de l'expression logique
     * 
     * @return Vrai si l'expression est vrai, faux sinon
     * @throws LispError
     *             Lance une erreur en cas de problème
     */
    LispBoolean getLogicEvaluation() throws LispError;
}
