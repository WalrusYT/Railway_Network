package dataStructures;

import java.io.Serial;
import java.io.Serializable;

/**
 * Advanced BSTree Data Type implementation
 * @author AED team
 * @version 1.0
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value 
 */
public abstract class AdvancedBSTree<K extends Comparable<K> & Serializable, V extends Serializable>
        extends BinarySearchTree<K,V> {
    /**
     * Serial Version UID of the Class
     */
    @Serial
    private static final long serialVersionUID = 0L;
    /**
     * Performs a single left rotation rooted at Y node.
     * Node X was a right  child of Y before the rotation,
     * then Y becomes the left child of X after the rotation.
     * @param y - root of the rotation
     * @pre: y has a right child
     */
    protected void rotateLeft(BSTNode<Entry<K,V>> y) {
        // TODO
        //  a single rotation modifies a constant number of parent-child relationships,
        //  it can be implemented in O(1) time
        if (y.getRight() == null) return;
        BSTNode<Entry<K,V>> x = y.getRight(); // right child of the rotation root
        BSTNode<Entry<K,V>> z = y.getParent(); // parent of the rotation root
        if (z == null) {
            root = x;
        } else {
            if (z.getLeft() == y) { // update the parent of the rotation root child
                z.setLeft(x);
            } else {
                z.setRight(x);
            }
        }
        BSTNode<Entry<K,V>> xLeftSubtree = x.getLeft();
        x.setLeft(y); // set the left child of the rotation root
        x.setParent(z); // set the new parent of the rotation root
        y.setParent(x); // set the rotation root as a parent of the left child
        y.setRight(xLeftSubtree);
        if (xLeftSubtree != null) xLeftSubtree.setParent(y);
    }

    /**
     * Performs a single right rotation rooted at Y node.
     * Node X was a left child of Y before the rotation,
     * then Y becomes the right child of X after the rotation.
     * @param y - root of the rotation
     * @pre: y has a left child
     */
    protected void rotateRight(BSTNode<Entry<K,V>> y) {
        // TODO
        //  a single rotation modifies a constant number of parent-child relationships,
        //  it can be implemented in O(1) time
        if (y.getLeft() == null) return;
        BSTNode<Entry<K,V>> x = y.getLeft();
        BSTNode<Entry<K,V>> z = y.getParent();
        if (z == null) {
            root = x;
        } else {
            if (z.getLeft() == y) { // update the parent of the rotation root child
                z.setLeft(x);
            } else {
                z.setRight(x);
            }
        }
        BSTNode<Entry<K,V>> xRightSubtree = x.getRight();
        x.setRight(y);
        x.setParent(z);
        y.setParent(x);
        y.setLeft(xRightSubtree);
        if (xRightSubtree != null) xRightSubtree.setParent(y);
    }

    /**
     * Performs a tri-node restructuring (a single or double rotation rooted at X node).
     * Assumes the nodes are in one of following configurations:
     *
     * @param x - root of the rotation
     * <pre>
     *          z=c       z=c        z=a         z=a
     *         /  \      /  \       /  \        /  \
     *       y=b  t4   y=a  t4    t1  y=c     t1  y=b
     *      /  \      /  \           /  \         /  \
     *    x=a  t3    t1 x=b        x=b  t4       t2 x=c
     *   /  \          /  \       /  \             /  \
     *  t1  t2        t2  t3     t2  t3           t3  t4
     * </pre>
     * @return the new root of the restructured subtree
     */
    protected BSTNode<Entry<K,V>> restructure(BSTNode<Entry<K,V>> x) {
        // the modification of a tree T caused by a trinode restructuring operation
        // can be implemented through case analysis either as a single rotation or as a double rotation.
        // The double rotation arises when position x has the middle of the three relevant keys
        // and is first rotated above its parent Y, and then above what was originally its grandparent Z.
        // In any of the cases, the trinode restructuring is completed with O(1)running time
        //TODO
        if (x == null) return x;
        BSTNode<Entry<K,V>> y = x.getParent();
        if (y == null) return x;
        BSTNode<Entry<K,V>> z = y.getParent();
        if (z == null) return x;
        if (y == z.getLeft() && x == y.getLeft()) {
            rotateRight(z);
            return y;
        }
        if (y == z.getRight() && x == y.getRight()) {
            rotateLeft(z);
            return y;
        }
        if (y == z.getRight() && x == y.getLeft()) {
            rotateRight(y);
            rotateLeft(z);
            return x;
        }
        if (y == z.getLeft() && x == y.getRight()) {
            rotateLeft(y);
            rotateRight(z);
            return x;
        }
        return x;
    }
}

