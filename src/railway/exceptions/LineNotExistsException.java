package railway.exceptions;

import java.io.Serial;

/**
 * Exception if there is no line with a given name in the system
 */
public class LineNotExistsException extends Exception{
    /**
     * Serial Version UID of the Class
     */
    @Serial
    private static final long serialVersionUID = 0L;
    public LineNotExistsException () {
        super("Linha inexistente.");
    }
}
