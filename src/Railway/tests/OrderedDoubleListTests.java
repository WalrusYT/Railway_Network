package Railway.tests;

import Railway.dataStructures.Dictionary;
import Railway.dataStructures.Entry;
import Railway.dataStructures.Iterator;
import Railway.dataStructures.OrderedDoubleList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderedDoubleListTests {
    @Test
    public void insertTest() {
        Dictionary<Integer, String> dict = new OrderedDoubleList<>();
        dict.insert(1, "a"); dict.insert(2, "b"); dict.insert(3, "c");
        assertEquals(dict.size(), 3);
        assertEquals(dict.find(3), "a");
        assertEquals(dict.find(1), "b");
        assertEquals(dict.find(2), "c");
    }

    @Test
    public void removeTest() {
        Dictionary<Integer, String> dict = new OrderedDoubleList<>();
        dict.insert(1, "a"); dict.insert(2, "b"); dict.insert(3, "c");
        assertEquals(dict.remove(3), "c");
        assertEquals(dict.size(), 2);
        assertEquals(dict.remove(1), "a");
        assertEquals(dict.size(), 1);
        assertEquals(dict.remove(2), "b");
        assertEquals(dict.size(), 0);
    }

    @Test
    public void orderTest() {
        Dictionary<Integer, String> dict = new OrderedDoubleList<>();
        dict.insert(5, "a"); dict.insert(1, "b"); dict.insert(9, "c"); dict.insert(4, "d");
        Iterator<Entry<Integer, String>> iterator = dict.iterator();
        Entry<Integer, String> entry = iterator.next();
        assertEquals(entry.getKey(), 1); assertEquals(entry.getValue(), "b");
        entry = iterator.next();
        assertEquals(entry.getKey(), 4); assertEquals(entry.getValue(), "d");
        entry = iterator.next();
        assertEquals(entry.getKey(), 5); assertEquals(entry.getValue(), "a");
        entry = iterator.next();
        assertEquals(entry.getKey(), 9); assertEquals(entry.getValue(), "c");
    }
}
