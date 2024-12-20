package pt.walrus.dataStructures;

import java.io.Serializable;

/**
 * Entry interface
 * @param <K> Generic Key
 * @param <V> Generic Value
 *
 * @author fernanda
 */
public interface Entry<K, V> extends Serializable {
	// Returns the key in the entry.
	K getKey( );
	 
	// Returns the value in the entry.
	V getValue( );
}
