package migl.lisp;

import java.math.BigInteger;

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
        System.out.println(expr);
        StringBuilder bStr = new StringBuilder();
        int i;

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
            throw new LispError("OK...Missing )");
        }

        return getEltValue(expr);
    }

    @Override
    public Object evaluate(Object ex) throws LispError {
        // TODO Auto-generated method stub
        return null;
    }

}
