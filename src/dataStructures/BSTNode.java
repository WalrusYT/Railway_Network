package dataStructures;

import java.io.Serial;
import java.io.Serializable;

/**
 * BST node implementation
 *
 * @param <E> Generic Value
 * @author AED team
 * @version 1.0
 */
public class BSTNode<E extends Serializable> implements Serializable
{

    /**
     * Serializable class a version number
     */
    @Serial
    private static final long serialVersionUID = 0L;
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
     * @param elem   the element of the node
     * @param parent the parent of the node
     * @param left   the left child of the node
     * @param right  the right child of the node
     */
    public BSTNode( E elem, BSTNode<E> parent, BSTNode<E> left, BSTNode<E> right )
    {                                                                
        this.element = elem;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    /**
     * Initializes a new Binary Search Node without links
     *
     * @param elem the element of the node
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
     * Determines whether this node has any children
     *
     * @return true, if either left or right child isn't null, false otherwise
     */
    public boolean isInternal() {
        return left != null || right != null;
    }

    /**
     * Sets the left child with the given node
     *
     * @param left the node that should be a left child of the node
     */
    public void setLeft(BSTNode<E> left) {
        this.left = left;
    }

    /**
     * Sets the right child with the given node
     *
     * @param right the node that should be a right child of the node
     */
    public void setRight(BSTNode<E> right) {
        this.right = right;
    }

    /**
     * Sets element of the node
     *
     * @param element the element that should be stored in the node
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Sets parent of the node with the given parent node
     *
     * @param parent the node of the parent
     */
    public void setParent(BSTNode<E> parent) {
        this.parent = parent;
    }
}
