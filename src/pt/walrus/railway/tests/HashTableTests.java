package pt.walrus.railway.tests;

import pt.walrus.dataStructures.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HashTableTests {
    @Test
    public void insertTest() {
        Dictionary<Integer, String> dict = new SepChainHashTable<>();
        dict.insert(1, "a"); dict.insert(2, "b"); dict.insert(3, "c");
        assertEquals(3, dict.size());
        assertEquals("a", dict.find(1));
        assertEquals("b", dict.find(2));
        assertEquals("c", dict.find(3));
        for (int i = 0; i < 100; i++) {
            dict.insert(i, "abc" + i);
            dict.insert(i, "_abc" + i);
            dict.insert(i, "__abc" + i);
        }
        System.out.println(dict.find(98));
    }

    @Test
    public void insertExistingTest() {
        Dictionary<Integer, String> dict = new SepChainHashTable<>();
        dict.insert(1, "a"); dict.insert(2, "b"); dict.insert(3, "c");
        dict.insert(3, "d");
        assertEquals(3, dict.size());
        assertEquals("d", dict.find(3));
    }

    @Test
    public void removeTest() {
        Dictionary<Integer, String> dict = new SepChainHashTable<>();
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
        Dictionary<Integer, String> dict = new SepChainHashTable<>();
        assertNull(dict.remove(2));
        assertEquals(0, dict.size());
    }

    @Test
    public void removeNonExistentTest() {
        Dictionary<Integer, String> dict = new SepChainHashTable<>();
        dict.insert(1, "a"); dict.insert(2, "b"); dict.insert(3, "c");
        assertNull(dict.remove(4));
        assertEquals(3, dict.size());
    }

    @Test
    public void insertAfterRemovingTest() {
        Dictionary<Integer, String> dict = new SepChainHashTable<>();
        dict.insert(1, "a");
        dict.remove(1);
        dict.insert(2, "b");
        assertEquals(1, dict.size());
        assertEquals("b", dict.find(2));
    }

    @Test
    public void iteratorTest() {
        var expected = java.util.List.of(
                new EntryClass<>(1, "b"),
                new EntryClass<>(4, "d"),
                new EntryClass<>(5, "a"),
                new EntryClass<>(9, "c")
        );
        Dictionary<Integer, String> dict = new SepChainHashTable<>();
        for (Entry<Integer, String> e : expected) {
            dict.insert(e.getKey(), e.getValue());
        }
        Iterator<Entry<Integer, String>> iterator = dict.iterator();
        var data = new ArrayList<Entry<Integer, String>>();
        while (iterator.hasNext()) {
            Entry<Integer, String> en = iterator.next();
            data.add(en);
            System.out.println(en.getKey());
        }
        var actual = data.stream().sorted(java.util.Comparator.comparingInt(Entry::getKey)).toList();
        assertEquals(expected, actual);
    }

    @Test
    public void rehashTest() {
        Dictionary<Integer, String> dict = new SepChainHashTable<>(2);
        for (int i = 0; i < 100; i++) {
            dict.insert(i, "abc" + i);
        }
        assertEquals("abc2", dict.find(2));
        assertEquals(100, dict.size());
        assertEquals(dict.remove(50), "abc50");
        assertEquals(99, dict.size());
        assertNull(dict.remove(101));
        assertEquals(99, dict.size());
        for (int i = 0; i < 50; i++) {
            dict.remove(i);
        }
        for (int i = 100; i < 150; i++) {
            dict.insert(i, "abc" + i);
        }
    }
}
