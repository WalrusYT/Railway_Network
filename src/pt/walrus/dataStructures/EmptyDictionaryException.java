package pt.walrus.dataStructures;

/**
 * Empty dictionary exception.
 */
public class EmptyDictionaryException extends RuntimeException
{

    /**
     * The Serial version uid.
     */
    static final long serialVersionUID = 0L;


    /**
     * Instantiates a new Empty dictionary exception.
     */
    public EmptyDictionaryException( )
    {
        super();
    }

    /**
     * Instantiates a new Empty dictionary exception.
     *
     * @param message the message
     */
    public EmptyDictionaryException( String message )
    {
        super(message);
    }

}

