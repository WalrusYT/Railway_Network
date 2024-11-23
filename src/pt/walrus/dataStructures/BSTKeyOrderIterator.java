package pt.walrus.dataStructures;

/**
 * The type Bst key order iterator.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
class BSTKeyOrderIterator<K,V> implements Iterator<Entry<K,V>> {


	/**
	 * The Root.
	 */
	protected BSTNode<Entry<K,V>> root;

	/**
	 * The P.
	 */
	protected Stack<BSTNode<Entry<K,V>>> p;


	/**
	 * Instantiates a new Bst key order iterator.
	 *
	 * @param root the root
	 */
	BSTKeyOrderIterator(BSTNode<Entry<K,V>> root){
		this.root = root;
		rewind();
	}

	private void pushPathToMinimum(BSTNode<Entry<K,V>> node) {
		//TODO
		while (node != null) {
			p.push(node);
			node = node.left;
		}
	}

	//O(1) para todos os casos
	public boolean hasNext(){
		return !p.isEmpty();
	}


	public Entry<K,V> next( ) throws NoSuchElementException {
		if (!hasNext()) throw new NoSuchElementException();
		else {
			//TODO
			BSTNode<Entry<K,V>> node;
			node = p.pop();
			pushPathToMinimum(node.right);
			return node.getElement();
		}
	}

	public void rewind( ){
		p = new StackInList<BSTNode<Entry<K,V>>>();
		pushPathToMinimum(root);
	}
}