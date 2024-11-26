package railway.exceptions;

import java.io.Serial;

/**
 * Exception if there is already a line in a system with the same name
 */
public class LineAlreadyExistsException extends Exception{
    /**
     * Serial Version UID of the Class
     */
    @Serial
    private static final long serialVersionUID = 0L;
    public LineAlreadyExistsException () {
        super("Linha existente.");
    }
}
