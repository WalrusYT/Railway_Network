package dataStructures;

public class BSTBreadthFirstIterator<K,V> implements Iterator<Entry<K,V>>{
    protected BSTNode<Entry<K,V>> root;
    protected Queue<BSTNode<Entry<K,V>>> q;
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
