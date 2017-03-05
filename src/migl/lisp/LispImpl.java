package migl.lisp;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

import migl.lisp.expr.LispExpression;
import migl.lisp.expr.LispExpressionFactory;
import migl.util.ConsList;
import migl.util.ConsListFactory;

/**
 * Implémentation Lisp
 * 
 * @author xavier_heugue
 *
 */
public class LispImpl implements Lisp {

    private static Object getEltValue(String expr) throws LispError {
        try {
            return BigInteger.valueOf(Long.parseLong(expr));
        } catch (NumberFormatException ex) {
            try {
                return Double.parseDouble(expr);
            } catch (NumberFormatException ex2) {
                try {
                    return LispBoolean.valueOf(expr);
                } catch (IllegalArgumentException ex3) {
                    if (expr.contains(")") && !expr.contains("("))
                        throw new LispError("Missing '('");
                    return expr;
                }
            }
        }
    }

    private static ConsList<Object> parseList(String expr, boolean sublist) throws LispError {
        ConsList<Object> list = ConsListFactory.nil();
        StringBuilder bStr = new StringBuilder();
        int i;
        // TODO Refaire cette fonction
        for (i = 1; i < expr.length() && expr.charAt(i) != ')'; i++) {
            if (expr.charAt(i) == '(') {
                int fin;
                for (fin = i; fin < expr.length() && expr.charAt(fin) != ')'; fin++)
                    ;
                if (fin == expr.length())
                    throw new LispError("Missing )");
                list = list.append(parseList(expr.substring(i, fin + 1), true));
                i = fin;
            } else if (expr.charAt(i) == ' ' || expr.charAt(i) == '\t') {
                if (bStr.length() != 0) {
                    list = list.append(getEltValue(bStr.toString()));
                    bStr = new StringBuilder();
                }
            } else {
                bStr.append(expr.charAt(i));
            }
        }
        if (i == expr.length())
            throw new LispError("Missing closing parenthesis");
        else if (expr.length() != i + 1 && !sublist) {
            for (i += 1; i < expr.length(); i++)
                if (expr.charAt(i) != ' ' && expr.charAt(i) != '\t')
                    throw new LispError("Strings contains an unexpected character");
        } else if (expr.length() != i + 1)
            throw new LispError("Strings contains an unexpected character");

        if (bStr.length() != 0)
            list = list.append(getEltValue(bStr.toString()));
        return list;
    }

    @Override
    public Object parse(String expr) throws LispError {
        if (expr.length() == 0 || expr == null)
            throw new LispError("Cannot parse empty list");
        if (expr.charAt(0) == ' ' || expr.charAt(0) == '\t')
            return parse(expr.substring(1));
        if (expr.charAt(0) == '(')
            return parseList(expr, false);
        if (expr.contains(")")) {
            throw new LispError("Missing )");
        }

        return getEltValue(expr);
    }

    @Override
    public Object evaluate(Object ex) throws LispError {
        LispExpression expr;
        if (ex instanceof ConsList) {
            ConsList<Object> list = (ConsList<Object>) ex;
            try {
                expr = LispExpressionFactory.createExpression(list);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                throw new LispError("Synthax error", e);
            }
        } else if (ex instanceof BigInteger) {
            BigInteger bint = (BigInteger) ex;
            expr = LispExpressionFactory.createExpression(bint);
        } else if (ex instanceof Double) {
            Double d = (Double) ex;
            expr = LispExpressionFactory.createExpression(d);
        } else if (ex instanceof LispBoolean) {
            LispBoolean lB = (LispBoolean) ex;
            expr = LispExpressionFactory.createExpression(lB);
        } else if (ex instanceof String) {
            String str = (String) ex;
            expr = LispExpressionFactory.createExpression(str);
        } else {
            throw new LispError("Synthax error");
        }

        return expr.getEvaluation();
    }

}
