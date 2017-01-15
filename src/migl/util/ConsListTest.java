package migl.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConsListTest {

    private static Class<? extends ConsList<Integer>> listimpl;

    private static ConsList<Integer> list;

    @SuppressWarnings("unchecked")
    @BeforeClass
    public static void findImpl() throws ClassNotFoundException {
        listimpl = (Class<? extends ConsList<Integer>>) Class.forName("migl.util.ConsListImpl");
    }

    @Before
    public void init() throws InstantiationException, IllegalAccessException {
        list = listimpl.newInstance();
    }

    @Test
    public void testListIsEmptyByDefault() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testPrepend() {
        assertEquals(0, list.size());
        list = list.prepend(1);
        assertEquals(1, list.size());
        assertEquals(1, list.car().intValue());
        list = list.prepend(2);
        assertEquals(2, list.size());
        assertEquals(2, list.car().intValue());
        list = list.prepend(3);
        assertEquals(3, list.size());
        assertEquals(3, list.car().intValue());
    }

    @Test
    public void testPrependSharesSublist() {
        ConsList<Integer> list2 = list.prepend(2);
        assertEquals(list, list2.cdr());
        assertSame(list, list2.cdr());
        ConsList<Integer> list3 = list2.prepend(1);
        assertEquals(list2, list3.cdr());
        assertSame(list2, list3.cdr());
    }

    @Test
    public void testAppend() {
        assertEquals(0, list.size());
        list = list.append(1);
        assertEquals(1, list.size());
        assertEquals(1, list.car().intValue());
        list = list.append(2);
        assertEquals(2, list.size());
        assertEquals(1, list.car().intValue());
        list = list.append(3);
        assertEquals(3, list.size());
        assertEquals(1, list.car().intValue());
    }

    @Test
    public void testPrependDoesShareSublist() {
        ConsList<Integer> list2 = list.append(1);
        assertEquals(list, list2.cdr());
        assertNotSame(list, list2.cdr());
        ConsList<Integer> list3 = list2.append(2);
        assertEquals(list2.car(), list3.car());
        assertSame(list2, list3.cdr());
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("()", list.toString());
    }

    @Test
    public void testToStringSingleton() {
        list = list.prepend(1);
        assertEquals("(1)", list.toString());
    }

    @Test
    public void testToStringTwoElements() {
        list = list.prepend(2);
        list = list.prepend(1);
        assertEquals("(1, 2)", list.toString());
    }
}
