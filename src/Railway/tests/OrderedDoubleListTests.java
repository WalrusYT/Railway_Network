package Railway.tests;

import dataStructures.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderedDoubleListTests {
    public static final Comparator<Integer> COMPARATOR = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };

    @Test
    public void insertTest() {
        Dictionary<Integer, String> dict = new OrderedDoubleList<>(COMPARATOR);
        dict.insert(1, "a"); dict.insert(2, "b"); dict.insert(3, "c");
        assertEquals(3, dict.size());
        assertEquals("a", dict.find(1));
        assertEquals("b", dict.find(2));
        assertEquals("c", dict.find(3));
    }

    @Test
    public void insertExistingTest() {
        Dictionary<Integer, String> dict = new OrderedDoubleList<>(COMPARATOR);
        dict.insert(1, "a"); dict.insert(2, "b"); dict.insert(3, "c");
        dict.insert(3, "d");
        assertEquals(3, dict.size());
        assertEquals("d", dict.find(3));
    }

    @Test
    public void removeTest() {
        Dictionary<Integer, String> dict = new OrderedDoubleList<>(COMPARATOR);
        dict.insert(1, "a"); dict.insert(2, "b"); dict.insert(3, "c");
        assertEquals("c", dict.remove(3));
        assertEquals(2, dict.size());
        assertEquals("a", dict.remove(1));
        assertEquals(1, dict.size());
        assertEquals("b", dict.remove(2));
        assertEquals(0, dict.size());
    }

    @Test
    public void removeEmptyTest() {
        Dictionary<Integer, String> dict = new OrderedDoubleList<>(COMPARATOR);
        assertNull(dict.remove(2));
        assertEquals(0, dict.size());
    }

    @Test
    public void removeNonExistentTest() {
        Dictionary<Integer, String> dict = new OrderedDoubleList<>(COMPARATOR);
        dict.insert(1, "a"); dict.insert(2, "b"); dict.insert(3, "c");
        assertNull(dict.remove(4));
        assertEquals(3, dict.size());
    }

    @Test
    public void insertAfterRemovingTest() {
        Dictionary<Integer, String> dict = new OrderedDoubleList<>(COMPARATOR);
        dict.insert(1, "a");
        dict.remove(1);
        dict.insert(2, "b");
        assertEquals(1, dict.size());
        assertEquals("b", dict.find(2));
    }

    @Test
    public void orderTest() {
        Dictionary<Integer, String> dict = new OrderedDoubleList<>(COMPARATOR);
        dict.insert(5, "a"); dict.insert(1, "b"); dict.insert(9, "c"); dict.insert(4, "d");
        Iterator<Entry<Integer, String>> iterator = dict.iterator();
        Entry<Integer, String> entry = iterator.next();
        assertEquals(1, entry.getKey()); assertEquals("b", entry.getValue());
        entry = iterator.next();
        assertEquals(4, entry.getKey()); assertEquals("d", entry.getValue());
        entry = iterator.next();
        assertEquals(5, entry.getKey()); assertEquals("a", entry.getValue());
        entry = iterator.next();
        assertEquals(9, entry.getKey()); assertEquals("c", entry.getValue());
    }
}
