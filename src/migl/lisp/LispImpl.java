package migl.lisp;

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

        for (i = 1; i < expr.length() && expr.charAt(i) != ')'; i++) {
            if (expr.charAt(i) == '(') {
                int fin;
                int level;

                for (fin = i, level = 0; fin < expr.length() && !(expr.charAt(fin) == ')' && level == 1); fin++) {
                    if (expr.charAt(fin) == '(') {
                        level++;
                    } else if (expr.charAt(fin) == ')') {
                        level--;
                    }
                }
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
        if (i == expr.length()) {
            System.out.println("<" + expr + ">");
            throw new LispError("Missing closing parenthesis");
        } else if (expr.length() != i + 1 && !sublist) {
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
        String trimmedExpr = expr.trim();
        if (trimmedExpr.length() == 0)
            throw new LispError("Cannot parse empty list");
        if (trimmedExpr.charAt(0) == ' ' || trimmedExpr.charAt(0) == '\t')
            return parse(trimmedExpr.substring(1));
        if (trimmedExpr.charAt(0) == '(')
            return parseList(trimmedExpr, false);
        if (trimmedExpr.contains(")")) {
            throw new LispError("Missing )");
        }

        return getEltValue(trimmedExpr);
    }

    @Override
    public Object evaluate(Object ex) throws LispError {
        LispExpression expr;
        if (ex instanceof ConsList) {
            ConsList<Object> list = (ConsList<Object>) ex;
            try {
                expr = LispExpressionFactory.createExpression(list, true);
            } catch (IllegalArgumentException | SecurityException e) {
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
            expr = LispExpressionFactory.createExpression(str, true);
        } else {
            throw new LispError("Synthax error");
        }

        return expr.getEvaluation();
    }

}
