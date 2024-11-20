package dataStructures;  

/**
 * Separate Chaining Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class SepChainHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public SepChainHashTable( int capacity )
    {
        maxSize = capacity;
        int arraySize = HashTable.nextPrime((int) (1.1 * maxSize));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            //TODO: Original comentado para nao dar erro de compilacao.
            table[i] = new OrderedDoubleList<>(new Comparator<K>() {
                @Override
                public int compare(K o1, K o2) {
                    return  o1.compareTo(o2);
                }
            });
        currentSize = 0;
    }                                      


    public SepChainHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() )
            //TODO: left as an exercise.
        	//Original commented, to compile.
            this.rehash();
        //TODO: Left as an exercise.
        V oldValue = find(key);
        table[hash(key)].insert(key, value);
        if (oldValue == null) currentSize++;
        return oldValue;
    }

    private void rehash() {
        Iterator<Entry<K,V>> it = this.iterator();
        SepChainHashTable<K, V> newHashTable = new SepChainHashTable<>(maxSize * 2);
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            newHashTable.insert(entry.getKey(), entry.getValue());
        }
        this.maxSize = newHashTable.maxSize;
        this.table = newHashTable.table;
    }

    @Override
    public V remove( K key )
    {
        //TODO: Left as an exercise.
        V value = table[hash(key)].remove(key);
        if (value != null) currentSize--;
        return value;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
        return new SepChainHashTableIterator<>(table);
    } 
}
































