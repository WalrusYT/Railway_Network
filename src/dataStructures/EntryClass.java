package dataStructures;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author AED
 *
 */
public class EntryClass<K extends Serializable, V extends Serializable>
		implements Entry<K, V>, Serializable {
	protected K key;
	protected V value;
	/**
	 * Serializable class a version number
	 */
	@Serial
	private static final long serialVersionUID = 0L;
	
	public EntryClass(K k, V v){
		key=k;
		value=v;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	public void setValue(V value2) {
		value=value2;
	}
	
	

}
