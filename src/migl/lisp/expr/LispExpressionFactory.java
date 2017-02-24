package migl.lisp.expr;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

import migl.lisp.LispBoolean;
import migl.util.ConsList;

public final class LispExpressionFactory {

    private LispExpressionFactory() {
    }

    public static LispExpression createExpression(ConsList<Object> data) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        LispExpression expr = createExpression((String) (data.car()));
        data = data.cdr();

        for (Object o : data) {
            expr.add((LispExpression) LispExpressionFactory.class.getDeclaredMethod("createExpression", o.getClass())
                    .invoke(null, o));
        }

        return expr;
    }

    public static LispExpression createExpression(BigInteger data) {
        return new BigIntegerExpression(data);
    }

    public static LispExpression createExpression(Double data) {
        return new DoubleExpression(data);
    }

    public static LispExpression createExpression(LispBoolean data) {
        return new LispBooleanExpression(data);
    }

    public static LispExpression createExpression(String data) {
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
        default:
            return new StringExpression(data);

        }
    }
}
