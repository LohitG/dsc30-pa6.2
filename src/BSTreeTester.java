import org.junit.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
public class BSTreeTester {
    @Test
    public void basicBSTFindKey() {
        BSTree<String> test = new BSTree<String>();
        test.insert("lol");
        test.insert("bye");
        test.insert("dummy");
        test.insert("silly");
        assertTrue(test.findKey("bye"));
        assertTrue(test.findKey("lol"));
        assertTrue(test.findKey("dummy"));
        assertFalse(test.findKey("lolll"));
        assertTrue(test.findKey("silly"));
        assertEquals("lol", test.getRoot().getKey());
    }

    @Test
    public void BSTSizeHeightTest() {
        BSTree<Integer> test = new BSTree<Integer>();
        test.insert(8);
        test.insert(3);
        assertEquals(2, test.getSize());
        test.insert(1);
        assertEquals(2,test.findHeight());
        test.insert(6);
        test.insert(4);
        assertEquals(5, test.getSize());
        assertEquals(3,test.findHeight());
        test.insert(7);
        test.insert(10);
        assertEquals(3,test.findHeight());
        test.insert(14);
        test.insert(13);
        assertEquals(9, test.getSize());
        assertEquals(new Integer(8), test.getRoot().getKey());
    }

    @Test
    public void BSTDataTest() {
        BSTree<Integer> test = new BSTree<Integer>();
        test.insert(8);
        test.insertData(8, 9);
        test.insertData(8, 10);
        test.insert(3);
        test.insertData(3, 4);
        test.insertData(3, 5);
        assertEquals(new Integer(10), test.findDataList(8).get(1));
        assertEquals(new Integer(9), test.findDataList(8).get(0));
        assertEquals(new Integer(5), test.findDataList(3).get(1));
        assertEquals(new Integer(8), test.getRoot().getKey());

    }

    @Test (expected = NullPointerException.class)
    public void testBTSException() {
        BSTree<Integer> test = new BSTree<Integer>();
        test.insert(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBTSInsertDataException() {
        BSTree<Integer> test = new BSTree<Integer>();
        test.insert(2);
        test.insertData(1,3);
    }

    @Test
    public void testBSTIterator() {
        BSTree<Integer> test = new BSTree<Integer>();
        test.insert(8);
        test.insert(3);
        test.insert(1);
        test.insert(6);
        test.insert(4);
        test.insert(7);
        test.insert(10);
        test.insert(14);
        test.insert(13);
        Iterator<Integer> iter = test.iterator();
        assertTrue(iter.hasNext());
        assertEquals(new Integer(1),iter.next());
        assertEquals(new Integer(3),iter.next());
        assertEquals(new Integer(4),iter.next());
        assertEquals(new Integer(6),iter.next());
        assertEquals(new Integer(7),iter.next());
        assertEquals(new Integer(8),iter.next());
        assertEquals(new Integer(10),iter.next());
        assertEquals(new Integer(13),iter.next());
        assertEquals(new Integer(14),iter.next());
    }

    @Test
    public void testBSTIterator2() {
        BSTree<Integer> test = new BSTree<Integer>();
        test.insert(3);
        test.insert(4);
        test.insert(5);
        Iterator<Integer> iter = test.iterator();
        assertEquals(new Integer(3), iter.next());
        assertEquals(new Integer(4), iter.next());
    }

    @Test (expected = NoSuchElementException.class)
    public void testBSTIterator3() {
        BSTree<Integer> test = new BSTree<Integer>();
        test.insert(3);
        Iterator<Integer> iter = test.iterator();
        assertEquals(new Integer(3), iter.next());
        iter.next();
    }
}
