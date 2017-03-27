package migl.lisp;

import org.junit.Before;

/**
 * Classe abstraite permettant l'initialisation d'un interpreteur Lisp pour
 * l'exécution de test
 * 
 * @author xavier
 *
 */
public abstract class AbstractTDD {

    protected Lisp lisp;

    /**
     * Initialise l'interpréteur Lisp pour les tests
     */
    @Before
    public void init() {
        lisp = LispFactory.makeIntepreter();
    }
}
