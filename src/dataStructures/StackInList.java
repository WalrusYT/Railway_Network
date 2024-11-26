package dataStructures;

import java.io.Serial;
import java.io.Serializable;

/**
 * The type Stack in list.
 *
 * @param <E> the type parameter
 */
public class StackInList<E extends Serializable> implements Stack<E>
{

    /**
     * The Serial version uid.
     */
    @Serial
    private static final long serialVersionUID = 0L;


    /**
     * The List.
     */
// Memory of the stack: a list.
    protected List<E> list;


    /**
     * Instantiates a new Stack in list.
     */
    public StackInList( )
    {     
        list = new DoubleList<E>();
    }


    // Returns true iff the stack contains no elements.
    public boolean isEmpty( )
    {     
        return list.isEmpty();
    }


    // Returns the number of elements in the stack.
    public int size( )
    {     
        return list.size();
    }


    // Returns the element at the top of the stack.
    public E top( ) throws EmptyStackException 
    {     
        if ( list.isEmpty() )
            throw new EmptyStackException();
        
        return list.getFirst();
    }


    // Inserts the specified element onto the top of the stack.
    public void push( E element )
    { 
        list.addFirst(element);
    }


    // Removes and returns the element at the top of the stack.
    public E pop( ) throws EmptyStackException 
    {     
        if ( list.isEmpty() )
            throw new EmptyStackException();

        return list.removeFirst();
    }


}
