package dataStructures;

/**
 * BinarySearchTree order iterator by keys.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
class BSTKeyOrderIterator<K,V> implements Iterator<Entry<K,V>> {


	/**
	 * The Root of the tree
	 */
	protected BSTNode<Entry<K,V>> root;

	/**
	 * An auxiliary stack to pop nodes in order
	 */
	protected Stack<BSTNode<Entry<K,V>>> p;


	/**
	 * Instantiates a new Bst key order iterator.
	 *
	 * @param root the root
	 */
	public BSTKeyOrderIterator(BSTNode<Entry<K,V>> root){
		this.root = root;
		rewind();
	}

	/**
	 * Pushes the path to minimum BSTNode
	 * @param node the root of the pushing
	 */
	private void pushPathToMinimum(BSTNode<Entry<K,V>> node) {
		//TODO
		while (node != null) {
			p.push(node);
			node = node.left;
		}
	}

	//O(1) para todos os casos

	@Override
	public boolean hasNext(){
		return !p.isEmpty();
	}

	@Override
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
	@Override
	public void rewind( ){
		p = new StackInList<BSTNode<Entry<K,V>>>();
		pushPathToMinimum(root);
	}
}