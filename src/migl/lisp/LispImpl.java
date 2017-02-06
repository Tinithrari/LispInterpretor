package migl.lisp;

import java.math.BigInteger;

import migl.util.ConsList;
import migl.util.ConsListFactory;

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
                    if (expr.contains(")"))
                        throw new LispError("Missing '('");
                    return expr;
                }
            }
        }
    }

    private static ConsList<Object> createList(String expr) throws LispError {
        ConsList<Object> list = ConsListFactory.nil();
        StringBuilder bStr = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == ' ') {
                String str = bStr.toString();
                if (!str.equals("")) {
                    list = list.append(getEltValue(str));
                    bStr = new StringBuilder();
                }
            } else if (expr.charAt(i) == '(' && bStr.toString().length() == 0) {
                int parentheseLevel = 0, j;
                for (j = i + 1; j < expr.length() && !(expr.charAt(j) == ')' && parentheseLevel == 0); j++) {
                    bStr.append(expr.charAt(j));
                    if (expr.charAt(j) == '(') {
                        parentheseLevel++;
                    } else if (expr.charAt(j) == ')') {
                        parentheseLevel--;
                    }
                }

                if (expr.length() == j)
                    throw new LispError("Missing ')'");
                list = list.append(createList(bStr.toString()));
                i += bStr.toString().length() + 1;
                bStr = new StringBuilder();
            } else {
                bStr.append(expr.charAt(i));
            }
        }

        if (bStr.toString().length() != 0) {
            list = list.append(getEltValue(bStr.toString()));
        }

        return list;
    }

    @Override
    public Object parse(String expr) throws LispError {
        if (expr.charAt(0) != '(') {
            return getEltValue(expr);
        } else {
            ConsList<Object> list;
            if (!expr.endsWith(")")) {
                throw new LispError("Missing ')'");
            }
            list = createList(expr.substring(1, expr.length() - 1));
            return list;
        }
    }

    @Override
    public Object evaluate(Object ex) throws LispError {
        // TODO Auto-generated method stub
        return null;
    }

}
