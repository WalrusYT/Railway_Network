package pt.walrus.dataStructures;

/**
 * The type Bst preorder iterator.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class BSTPreorderIterator<K,V> implements Iterator<Entry<K,V>> {

	/**
	 * Root of the binary search tree
	 */
	protected BSTNode <Entry<K,V>> root;

	/**
	 * Auxiliary stack to store the nodes
	 */
	protected Stack<BSTNode<Entry<K,V>>> st;

	/**
	 * Constructs a new iterator through the binary search tree
	 *
	 * @param root the root of the binary search tree
	 */
	public BSTPreorderIterator(BSTNode<Entry<K,V>>root){
		this.root=root;
		rewind();
	}

	@Override
	public boolean hasNext(){
		 return !st.isEmpty();
	}

	@Override
	public Entry<K,V> next( ) throws NoSuchElementException {
		if (!hasNext()) throw new NoSuchElementException();
		BSTNode<Entry<K,V>> node=st.pop();
		if (node.getRight()!=null) st.push(node.getRight());
		if (node.getLeft()!=null) st.push(node.getLeft());
		return node.getElement();
	}

	@Override
	public void rewind( ){
		st=new StackInList<>();
		if (this.root!= null) st.push(this.root);
	}
}
