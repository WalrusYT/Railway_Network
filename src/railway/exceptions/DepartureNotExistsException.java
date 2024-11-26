package railway.exceptions;

import java.io.Serial;

/**
 * Exception if there is no departure station in a line
 */
public class DepartureNotExistsException extends Exception{
    /**
     * Serial Version UID of the Class
     */
    @Serial
    private static final long serialVersionUID = 0L;
    public DepartureNotExistsException() {
        super("Estação de partida inexistente.");
    }
}
