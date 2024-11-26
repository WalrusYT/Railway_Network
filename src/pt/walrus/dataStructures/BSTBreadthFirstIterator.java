package pt.walrus.dataStructures;

/**
 * The iterator through the binary search tree
 * traversing the nodes in the order of children from left to right
 *
 * @param <K> Generic type Key
 * @param <V> Generic type Value
 */
public class BSTBreadthFirstIterator<K,V> implements Iterator<Entry<K,V>>{
    /**
     * Root of the binary search tree
     */
    protected BSTNode<Entry<K,V>> root;
    /**
     * Auxiliary queue to store nodes
     */
    protected Queue<BSTNode<Entry<K,V>>> q;

    /**
     * Constructs a new iterator through the binary search tree
     *
     * @param root the root of the binary search tree
     */
    BSTBreadthFirstIterator(BSTNode<Entry<K,V>> root){
        this.root=root;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext()) throw new NoSuchElementException();
        BSTNode<Entry<K,V>> node = q.dequeue();
        if (node.left != null) q.enqueue(node.left);
        if (node.right != null) q.enqueue(node.right);
        return node.getElement();
    }

    @Override
    public void rewind() {
        q = new QueueInList<>();
        q.enqueue(root);
    }
}