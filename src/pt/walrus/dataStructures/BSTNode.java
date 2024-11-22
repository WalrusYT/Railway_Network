package pt.walrus.dataStructures;

import java.io.Serializable;

/**
 * BST node implementation
 *
 * @param <E> Generic Value
 * @author AED team
 * @version 1.0
 */
public class BSTNode<E> implements Serializable
{


    /**
     * Element stored in the node.
     */
    protected E element;


    /**
     * (Pointer to) the left child.
     */
    protected BSTNode<E> left;

    /**
     * (Pointer to) the right child.
     */
    protected BSTNode<E> right;
    /**
     * (Pointer to) the parent node.
     */
    protected BSTNode<E> parent;

    /**
     * Constructor for BST nodes
     *
     * @param elem   the elem
     * @param parent the parent
     * @param left   the left
     * @param right  the right
     */
    public BSTNode( E elem, BSTNode<E> parent, BSTNode<E> left, BSTNode<E> right )
    {                                                                
        this.element = elem;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    /**
     * Instantiates a new Bst node.
     *
     * @param elem the elem
     */
    public BSTNode(E elem) {
        this(elem,null,null,null);
    }

    /**
     * Returns the element of the current node.
     *
     * @return element
     */
    public E getElement( )
    {
        return element;
    }


    /**
     * Returns the left child node of the current node.
     *
     * @return left
     */
    public BSTNode<E> getLeft( )
    {    
        return left;
    }


    /**
     * Returns the right child node of the current node.
     *
     * @return right
     */
    public BSTNode<E> getRight( )
    {
        return right;
    }

    /**
     * Returns the parent node of the current node.
     *
     * @return parent
     */
    public BSTNode<E> getParent()
    {
        return parent;
    }

    /**
     * Is internal boolean.
     *
     * @return the boolean
     */
    public boolean isInternal() {
        return left != null || right != null;
    }

    /**
     * Sets left.
     *
     * @param left the left
     */
    public void setLeft(BSTNode<E> left) {
        this.left = left;
    }

    /**
     * Sets right.
     *
     * @param right the right
     */
    public void setRight(BSTNode<E> right) {
        this.right = right;
    }

    /**
     * Sets element.
     *
     * @param element the element
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Sets parent.
     *
     * @param parent the parent
     */
    public void setParent(BSTNode<E> parent) {
        this.parent = parent;
    }

    /**
     * Gets entry.
     *
     * @return the entry
     */
    public E getEntry() {
        return getElement();
    }
}
