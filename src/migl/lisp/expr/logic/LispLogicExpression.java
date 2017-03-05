package migl.lisp.expr.logic;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;

public interface LispLogicExpression {
    LispBoolean getLogicEvaluation() throws LispError;
}
