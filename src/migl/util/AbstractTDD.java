package migl.util;

import org.junit.Before;

import migl.lisp.Lisp;
import migl.lisp.LispFactory;

public abstract class AbstractTDD {

    protected Lisp lisp;

    @Before
    public void init() {
        lisp = LispFactory.makeIntepreter();
    }
}
