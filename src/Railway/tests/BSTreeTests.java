package Railway.tests;

import dataStructures.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BSTreeTests {

    @Test
    public void iterator1Test() {
        OrderedDictionary<Integer, String> tree = new BinarySearchTree<>();
        tree.insert(5, "a");
        tree.insert(10, "b");
        tree.insert(1, "c");
        tree.insert(6, "d");
        Iterator<Entry<Integer, String>> it = tree.iterator();
        while (it.hasNext()) {
            Entry<Integer, String> entry = it.next();
            System.out.println(entry.getKey());
        }
    }
    @Test
    public void iterator2Test() {
        OrderedDictionary<String, String> tree = new BinarySearchTree<>();
        tree.insert("f", "smth");
        tree.insert("d", "smth");
        tree.insert("g", "smth");
        tree.insert("e", "smth");
        tree.insert("j", "smth");
        tree.insert("i", "smth");
        tree.insert("h", "smth");
        tree.insert("a", "smth");
        tree.insert("k", "smth");
        tree.insert("c", "smth");
        tree.insert("b", "smth");
        Iterator<Entry<String, String>> it = tree.iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            System.out.println(entry.getKey());
        }
    }

    @Test
    public void setTest() {
        Set<String> set = new TreeSet<>();
        set.add("c");
        set.add("a");
        set.add("d");
        set.add("d");
        set.add("a");
        set.add("e");
        set.add("b");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
