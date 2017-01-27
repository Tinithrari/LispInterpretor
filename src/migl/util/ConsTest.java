package migl.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class ConsTest {

    @Test
    public void testNilCons() {
        Cons<String, String> c1 = Cons.nil();
        assertEquals("(null . null)", c1.toString());
    }

    @Test
    public void testSimpleCons() {
        Cons<String, String> c1 = new Cons<>("a", "b");
        assertEquals("(a . b)", c1.toString());
        assertEquals("a", c1.car());
        assertEquals("b", c1.cdr());
    }

    @Test
    public void testNestedCons() {
        Cons<String, Cons<String, String>> c1 = new Cons<>("a", new Cons<>("b", "c"));
        assertEquals("(a . (b . c))", c1.toString());
    }

    @Test
    public void testEquals() {
        Cons<String, String> c1 = new Cons<>("a", "b");
        Cons<String, String> c2 = new Cons<>("a", "b");
        Cons<String, String> c3 = new Cons<>("a", "c");
        Cons<String, String> c4 = new Cons<>("c", "b");
        Cons<String, String> c5 = new Cons<>("c", "d");
        assertEquals(c1, c2);
        assertNotEquals(c1, null);
        assertNotEquals(c1, true);
        assertNotEquals(null, c2);
        assertNotEquals(true, c2);
        assertNotEquals(c1, c3);
        assertNotEquals(c1, c4);
        assertNotEquals(c1, c5);
    }
}
