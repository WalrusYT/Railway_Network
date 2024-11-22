package pt.walrus.railway.tests;

import pt.walrus.dataStructures.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BSTreeTests {

    @Test
    public void insertSizeTest() {
        Dictionary<Integer, String> tree = new BinarySearchTree<>();
        assertEquals(0, tree.size());
        tree.insert(1, "a");
        assertEquals(1, tree.size());
        tree.insert(2, "b");
        assertEquals(2, tree.size());
        tree.insert(3, "c");
        assertEquals(3, tree.size());
    }

    @Test
    public void insertAndFindTest() {
        Dictionary<Integer, String> tree = new BinarySearchTree<>();
        tree.insert(1, "a");
        tree.insert(2, "b");
        tree.insert(3, "c");
        assertEquals("a", tree.find(1));
        assertEquals("b", tree.find(2));
        assertEquals("c", tree.find(3));
    }

    @Test
    public void insertAndRemoveTwiceTest() {
        Dictionary<Integer, String> tree = new BinarySearchTree<>();
        assertNull(tree.insert(1, "a"));
        assertNull(tree.insert(2, "b"));
        assertEquals("a", tree.remove(1));
        assertEquals("b", tree.remove(2));
        assertNull(tree.find(1));
        assertNull(tree.find(2));
    }

    @Test
    public void insertDuplicateTest() {
        Dictionary<Integer, String> tree = new BinarySearchTree<>();
        assertNull(tree.insert(1, "a"));
        assertNull(tree.insert(2, "b"));
        assertEquals("a", tree.insert(1, "a"));
        assertEquals(2, tree.size());
        assertEquals("b", tree.insert(2, "c"));
        assertEquals(2, tree.size());
        assertEquals("a", tree.find(1));
        assertEquals("c", tree.find(2));
    }

    @Test
    public void removeTest() {
        Dictionary<Integer, String> tree = new BinarySearchTree<>();
        tree.insert(1, "a");
        tree.insert(2, "b");
        tree.insert(3, "c");
        assertEquals("c", tree.remove(3));
        assertEquals(2, tree.size());
        assertNull(tree.find(3));
        assertEquals("a", tree.remove(1));
        assertEquals(1, tree.size());
        assertNull(tree.find(1));
        assertEquals("b", tree.remove(2));
        assertEquals(0, tree.size());
        assertNull(tree.find(2));
        assertNull(tree.remove(1));
        assertEquals(0, tree.size());
    }

    @Test
    public void insertAfterRemoveTest() {
        Dictionary<Integer, String> tree = new BinarySearchTree<>();
        tree.insert(1, "a");
        tree.insert(2, "b");
        tree.remove(1);
        assertNull(tree.insert(1, "a"));
        assertEquals("a", tree.remove(1));
        assertNull(tree.insert(1, "b"));
        assertEquals("b", tree.remove(2));
        assertNull(tree.insert(2, "a"));
        assertEquals("a", tree.find(2));
        assertEquals("b", tree.find(1));
    }

    @Test
    public void iterator1Test() {
        Dictionary<Integer, String> tree = new BinarySearchTree<>();
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
        Dictionary<String, String> tree = new BinarySearchTree<>();
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
    @Test
    public void testRemove() {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        // Populate the tree
        bst.insert(50, "Root");
        bst.insert(30, "Left Child");
        bst.insert(70, "Right Child");
        bst.insert(20, "Left-Left Child");
        bst.insert(40, "Left-Right Child");
        bst.insert(60, "Right-Left Child");
        bst.insert(80, "Right-Right Child");

        // Check initial tree size
        assertEquals(7, bst.size());

        // Test 1: Remove a leaf node (20)
        assertEquals("Left-Left Child", bst.remove(20)); // Leaf node
        assertEquals(6, bst.size());
        assertNull(bst.find(20)); // Node should no longer exist

        // Test 2: Remove a node with one child (30)
        assertEquals("Left Child", bst.remove(30)); // Node with one child
        assertEquals(5, bst.size());
        assertNull(bst.find(30)); // Node should no longer exist

        // Test 3: Remove a node with two children (50)
        assertEquals("Root", bst.remove(50)); // Node with two children
        assertEquals(4, bst.size());
        assertNull(bst.find(50)); // Node should no longer exist

        // Test 4: Attempt to remove a non-existent node (100)
        assertNull(bst.remove(100)); // Non-existent key
        assertEquals(4, bst.size()); // Size should remain unchanged
    }
    @Test
    public void insertAndRemoveTwiceTest() {
        Dictionary<Integer, String> tree = new BinarySearchTree<>();
        assertNull(tree.insert(1, "a"));
        assertNull(tree.insert(2, "b"));
        assertEquals("a", tree.remove(1));
        assertEquals("b", tree.remove(2));
        assertNull(tree.find(1));
        assertNull(tree.find(2));
    }
}
