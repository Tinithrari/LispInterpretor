package migl.lisp.expr;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;
import migl.lisp.expr.logic.And;
import migl.lisp.expr.logic.Equals;
import migl.lisp.expr.logic.GreaterOrEquals;
import migl.lisp.expr.logic.GreaterThan;
import migl.lisp.expr.logic.If;
import migl.lisp.expr.logic.LesserOrEquals;
import migl.lisp.expr.logic.LesserThan;
import migl.lisp.expr.logic.LispBooleanExpression;
import migl.lisp.expr.logic.Not;
import migl.lisp.expr.logic.Or;
import migl.lisp.expr.math.Abs;
import migl.lisp.expr.math.Addition;
import migl.lisp.expr.math.BigIntegerExpression;
import migl.lisp.expr.math.ChangementSigne;
import migl.lisp.expr.math.Cos;
import migl.lisp.expr.math.Division;
import migl.lisp.expr.math.DoubleExpression;
import migl.lisp.expr.math.Max;
import migl.lisp.expr.math.Min;
import migl.lisp.expr.math.Multiplication;
import migl.lisp.expr.math.Pow;
import migl.lisp.expr.math.Sin;
import migl.lisp.expr.math.Tan;
import migl.util.ConsList;

public final class LispExpressionFactory {

    private LispExpressionFactory() {
    }

    public static LispExpression createExpression(ConsList<Object> data, boolean first) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, LispError {
        LispExpression expr = createExpression((String) (data.car()), first);
        boolean argument = true;
        ConsList<Object> args = data.cdr();

        if (args == null)
            return expr;

        for (Object arg : args) {
            if (arg instanceof ConsList && expr instanceof Lambda && argument) {
                for (Object component : (ConsList<Object>) arg) {
                    expr.add(new StringExpression((String) component));
                }
                argument = false;
            } else if (arg instanceof ConsList)
                expr.add(createExpression((ConsList<Object>) arg, false));
            else
                expr.add((LispExpression) LispExpressionFactory.class
                        .getDeclaredMethod("createExpression", arg.getClass(), boolean.class).invoke(null, arg, false));
        }

        return expr;
    }

    public static LispExpression createExpression(BigInteger data, boolean first) {
        return new BigIntegerExpression(data);
    }

    public static LispExpression createExpression(Double data, boolean first) {
        return new DoubleExpression(data);
    }

    public static LispExpression createExpression(LispBoolean data, boolean first) {
        return new LispBooleanExpression(data);
    }

    public static LispExpression createExpression(String data, boolean first) throws LispError {
        if (data == null)
            return null;
        switch (data) {
        case "+":
            return new Addition();
        case "*":
            return new Multiplication();
        case "-":
            return new ChangementSigne();
        case "/":
            return new Division();
        case "max":
            return new Max();
        case "sin":
            return new Sin();
        case "cos":
            return new Cos();
        case "tan":
            return new Tan();
        case "abs":
            return new Abs();
        case "pow":
            return new Pow();
        case "min":
            return new Min();
        case "not":
            return new Not();
        case "and":
            return new And();
        case "or":
            return new Or();
        case ">":
            return new GreaterThan();
        case ">=":
            return new GreaterOrEquals();
        case "<":
            return new LesserThan();
        case "<=":
            return new LesserOrEquals();
        case "=":
            return new Equals();
        case "quote":
            return new Quote();
        case "if":
            return new If();
        case "cons":
            return new ConsExpression();
        case "define":
            return new Define();
        case "lambda":
            return new Lambda();
        case "set!":
            return new Set();
        case "car":
            return new Car();
        case "cdr":
            return new Cdr();
        case "list":
            return new List();
        default:
            LispExpression contenu = LispVariableContainer.get(data);

            if (contenu == null && first) {
                throw new LispError(data + " is undefined");
            }

            return first ? contenu : new StringExpression(data);

        }
    }
}
