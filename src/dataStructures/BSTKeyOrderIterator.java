package dataStructures;

class BSTKeyOrderIterator<K,V> implements Iterator<Entry<K,V>> {

	
	protected BSTNode<Entry<K,V>> root;

	protected Stack<BSTNode<Entry<K,V>>> p;


	BSTKeyOrderIterator(BSTNode<Entry<K,V>> root){
		this.root=root;
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
