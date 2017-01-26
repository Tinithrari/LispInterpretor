package migl.util;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

import migl.lisp.LispBoolean;
import migl.lisp.LispError;

/**
 * The aim of those tests is to check that a basic lisp String can be translated
 * as a list of Java objects.
 * 
 * The main issue here is to be able to return the proper Java type for each
 * object. Note that Lisp using arbitrary precision arithmetic, it is important
 * to use the BigInteger and Double classes to represent numbers.
 * 
 * Note that all inputs are valid for now. Invalid inputs will be tested in
 * future test cases.
 * 
 * @author leberre
 *
 */
public class LispParserTest extends AbstractTDD {

    @Test
    public void testParsingSingleElement() throws LispError {
        assertEquals("r", lisp.parse("r"));
        assertEquals(BigInteger.valueOf(18), lisp.parse("18"));
        assertEquals(Double.valueOf("-3.14E12"), lisp.parse("-3.14E12"));
    }

    @Test
    public void testParsingSingleElementInList() throws LispError {
        ConsList<Object> expected = ConsListFactory.nil().prepend("r");
        assertEquals(expected, lisp.parse("(r)"));
        expected = ConsListFactory.nil().prepend(BigInteger.valueOf(18));
        assertEquals(expected, lisp.parse("(18)"));
        expected = ConsListFactory.nil().prepend(Double.valueOf("-3.14E12"));
        assertEquals(expected, lisp.parse("(-3.14E12)"));
    }

    @Test
    public void testParsingSingleExpression() throws LispError {
        ConsList<Object> expected = ConsListFactory.nil().prepend("r").prepend("r").prepend("+");
        assertEquals(expected, lisp.parse("(+ r r)"));
        expected = ConsListFactory.nil().prepend(BigInteger.valueOf(2)).prepend("x").prepend(">");
        assertEquals(expected, lisp.parse("(> x 2)"));
        expected = ConsListFactory.nil().prepend(BigInteger.valueOf(10)).prepend("z").prepend("define");
        assertEquals(expected, lisp.parse("(define z 10)"));
    }

    @Test
    public void testParsingSimpleImbricatedExpressions() throws LispError {
        ConsList<Object> l1 = ConsListFactory.nil().prepend("r").prepend("r").prepend("+");
        ConsList<Object> expected = ConsListFactory.nil().prepend(BigInteger.valueOf(10)).prepend(l1).prepend("*");
        assertEquals(expected, lisp.parse("(* (+ r r) 10)"));
        l1 = ConsListFactory.nil().prepend(BigInteger.valueOf(2)).prepend(BigInteger.valueOf(1)).prepend("+");
        expected = ConsListFactory.nil().prepend(l1).prepend("quote");
        assertEquals(expected, lisp.parse("(quote (+ 1 2))"));
    }

    @Test
    public void testParsingOtherLispKeyworkds() throws LispError {
        ConsList<Object> l1 = ConsListFactory.nil().prepend(BigInteger.valueOf(2)).prepend(BigInteger.valueOf(1))
                .prepend(">");
        ConsList<Object> expected = ConsListFactory.nil().prepend(BigInteger.valueOf(5)).prepend(BigInteger.valueOf(3))
                .prepend(l1).prepend("if");
        assertEquals(expected, lisp.parse("(if (> 1 2) 3 5)"));
        expected = ConsListFactory.nil().prepend(BigInteger.valueOf(15)).prepend("bb8").prepend("define");
        assertEquals(expected, lisp.parse("(define bb8 15)"));
    }

    @Test
    public void testTypeOfTheObjectsInTheList() throws LispError {
        @SuppressWarnings("unchecked")
        ConsList<Object> objects = (ConsList<Object>) lisp.parse("(+ 1 2.0)");
        assertEquals(3, objects.size());
        assertEquals(String.class, objects.car().getClass());
        assertEquals(BigInteger.class, objects.cdr().car().getClass());
        assertEquals(Double.class, objects.cdr().cdr().car().getClass());
    }

    @Test
    public void testTypeOfBooleanConstants() throws LispError {
        @SuppressWarnings("unchecked")
        ConsList<Object> objects = (ConsList<Object>) lisp.parse("(or #t #f)");
        assertEquals(3, objects.size());
        assertEquals(String.class, objects.car().getClass());
        assertEquals(LispBoolean.class, objects.cdr().car().getClass());
        assertEquals(LispBoolean.TRUE, objects.cdr().car());
        assertEquals(LispBoolean.class, objects.cdr().cdr().car().getClass());
        assertEquals(LispBoolean.FALSE, objects.cdr().cdr().car());
    }
}
