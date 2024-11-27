package pt.walrus.dataStructures;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

/**
 * AVL tree implementation
 * 
 * @author AED team
 * @version 1.0
 *
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value 
 */
public class AVLTree<K extends Comparable<K>, V> 
    extends AdvancedBSTree<K,V> implements OrderedDictionary<K,V>
{
    /**
     * Serial Version UID of the Class
     */
    private static final long serialVersionUID = 0L;
    AVLTree(AVLNode<Entry<K,V>> node) {
        root = node;
    }

    public AVLTree() {
        this(null);
    }

    /**
     * Rebalance method called by insert and remove.  Traverses the path from
     * zPos to the root. For each node encountered, we recompute its height
     * and perform a trinode restructuring if it's unbalanced.
     * the rebalance is completed with O(log n) running time
     */
    void rebalance(AVLNode<Entry<K,V>> zPos) {
        if (zPos.isInternal())
            zPos.setHeight();
        // Improve if possible...
        zPos = (AVLNode<Entry<K, V>>) zPos.getParent();
        while (zPos!=null) {  // traverse up the tree towards the root
            zPos.setHeight();
            if (!zPos.isBalanced()) {
                // perform a trinode restructuring at zPos's tallest grandchild
                //If yPos (zPos.tallerChild()) denote the child of zPos with greater height.
                //Finally, let xPos be the child of yPos with greater height
                AVLNode<Entry<K,V>> xPos = zPos.tallerChild().tallerChild();
                if (xPos == null) {
                    zPos = (AVLNode<Entry<K, V>>) zPos.getParent();
                    continue;
                }

                zPos = (AVLNode<Entry<K, V>>) restructure(xPos); // tri-node restructure (from parent class)

                if (zPos.getLeft() != null) ((AVLNode<Entry<K, V>>) zPos.getLeft()).setHeight();  // recompute heights
                if (zPos.getRight() != null) ((AVLNode<Entry<K, V>>) zPos.getRight()).setHeight();
                zPos.setHeight();
            }
            zPos = (AVLNode<Entry<K, V>>) zPos.getParent();
        }
    }

    @Override
    public V insert( K key, V value )
    {
        //TODO
        V valueToReturn=null;
        AVLNode<Entry<K,V>> newNode=null; // node where the new entry is being inserted (if find(key)==null)
        AVLNode<Entry<K,V>> node = (AVLNode<Entry<K,V>>) findNode(key);
        // insert the new Entry (if find(key)==null)
        Entry<K,V> newEntry = new EntryClass<>(key, value);
        if (node == null || !node.getElement().getKey().equals(key)) {
            // means that we need to insert a new entry
            newNode = new AVLNode<>(newEntry);
            this.linkSubtreeInsert(newNode, node);
            currentSize ++;
        } else {
            valueToReturn = node.getElement().getValue();
            node.setElement(newEntry);
        }
        // or set the new value (if find(key)!=null)
        if(newNode != null) //(if find(key)==null)
            rebalance(newNode); // rebalance up from the insertion node
        return valueToReturn;
    }

   @Override
    public V remove( K key )
     {
         // TODO
         V valueToReturn=null;
         AVLNode<Entry<K,V>> node=null; // father of node where the key was
         BSTNode<Entry<K, V>> removeNode = this.findNode(key);
         if (removeNode == null || removeNode.getElement().getKey().compareTo(key) != 0)
             return null;
         valueToReturn = removeNode.getElement().getValue();
         node = (AVLNode<Entry<K, V>>) removeNode.getParent();
         if (removeNode.getLeft() == null) {
             this.linkSubtreeRemove(removeNode.getRight(), node, removeNode);
         } else if (removeNode.getRight() == null) {
             this.linkSubtreeRemove(removeNode.getLeft(), node, removeNode);
         } else {
             AVLNode<Entry<K, V>> minNode = (AVLNode<Entry<K, V>>) this.minNode(removeNode.getRight());
             removeNode.setElement( minNode.getElement() );
             this.linkSubtreeRemove(minNode.getRight(), minNode.getParent(), minNode);
         }
         currentSize --;
         if(node != null) //(if find(key)==null)
             rebalance(node); // rebalance up from the node
         return valueToReturn;
    }

    /*
    Custom serialization to prevent StackOverflowError during deserialization
    Simply writes out the size of a tree and its elements into the ObjectOutputStream
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(this.currentSize);
        Iterator<Entry<K, V>> iterator = this.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            out.writeObject(entry.getKey());
            out.writeObject(entry.getValue());
        }
    }

    /*
    Reads the size of a tree and then reads and inserts that amount of elements into the tree.
    This does not guarantee the same structure of a tree, but it does preserve all the elements
    and fixes the deserialization problem
     */
    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            K key = (K) in.readObject();
            V value = (V) in.readObject();
            this.insert(key, value);
        }
    }
}
