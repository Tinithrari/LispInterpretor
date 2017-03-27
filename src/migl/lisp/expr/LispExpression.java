package migl.lisp.expr;

import migl.lisp.LispError;

/**
 * Contrat pour les expressions Lisp
 * 
 * @author xavier
 *
 */
@FunctionalInterface
public interface LispExpression {
    /**
     * Evelue l'expression
     * 
     * @return Le résultat de l'expression
     * @throws LispError
     */
    Object getEvaluation() throws LispError;

    /**
     * Ajoute un élément composant la LispExpression
     * 
     * @param expr
     *            l'expression a ajouté
     * @throws LispError
     *             En cas d'erreur
     */
    default void add(LispExpression expr) throws LispError {
        throw new UnsupportedOperationException();
    }

    /**
     * Supprime un élément de la lispexpression
     * 
     * @param expr
     *            l'expression a supprimé
     */
    default void remove(LispExpression expr) {
        throw new UnsupportedOperationException();
    }
}
