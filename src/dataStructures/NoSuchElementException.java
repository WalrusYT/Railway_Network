package dataStructures;

import java.io.Serial;

/**
 * @author fernanda
 *
 */
public class NoSuchElementException extends RuntimeException {
	/**
	 * Serial Version UID of the Class
	 */
	@Serial
	private static final long serialVersionUID = 0L;
	public NoSuchElementException( )
	{
	super();
	}
	public NoSuchElementException( String msg )
	{
	super(msg);
	}
}
