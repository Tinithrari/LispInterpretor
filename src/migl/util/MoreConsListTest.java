package migl.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class MoreConsListTest {

    private ConsList<Integer> list;

    @Before
    public void init() {
        list = ConsListFactory.nil();
    }

    @Test(timeout = 20000)
    public void testPrepend() {
        for (int i = 0; i < 10000; i++) {
            list = list.prepend(i);
        }
        assertEquals(10000, list.size());
        ConsList<Integer> list2 = list.prepend(666);
        assertSame(list, list2.cdr());
    }

    @Test(timeout = 20000)
    public void testEqualsEfficiency() {
        for (int i = 0; i <= 10000; i++) {
            list = list.prepend(i);
        }
        ConsList<Integer> list2 = list.prepend(666);
        assertEquals(list, list2.cdr());
    }

    @Test
    public void testIteratorOnEmptyList() {
        for (Integer i : list) {
            fail("there should be no element " + i + " in the list!!!");
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextOnEmptyList() {
        list.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorWithNonEmptyList() {
        list = list.prepend(4).prepend(3).prepend(2).prepend(1);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next().intValue());
        assertTrue(it.hasNext());
        assertEquals(2, it.next().intValue());
        assertTrue(it.hasNext());
        assertEquals(3, it.next().intValue());
        assertTrue(it.hasNext());
        assertEquals(4, it.next().intValue());
        assertFalse(it.hasNext());
        it.next();
    }

    @Test
    public void testSingletonFactory() {
        ConsList<Integer> list2 = ConsListFactory.singleton(5);
        assertEquals(1, list2.size());
        assertEquals(5, list2.car().intValue());
    }

    @Test
    public void testListFromVarargs() {
        ConsList<Integer> list2 = ConsListFactory.asList(6, 66, 666);
        assertEquals(3, list2.size());
        Iterator<Integer> it = list2.iterator();
        assertTrue(it.hasNext());
        assertEquals(6, it.next().intValue());
        assertTrue(it.hasNext());
        assertEquals(66, it.next().intValue());
        assertTrue(it.hasNext());
        assertEquals(666, it.next().intValue());
        assertFalse(it.hasNext());
    }

    @Test
    public void testMap() {
        ConsList<Integer> list2 = ConsListFactory.asList(6, 66, 666);
        ConsList<String> list3 = ConsListFactory.asList("6", "66", "666");
        assertEquals(list2, list3.map(p -> Integer.valueOf(p)));
    }
}
