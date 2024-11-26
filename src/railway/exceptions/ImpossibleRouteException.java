package railway.exceptions;

import java.io.Serial;

/**
 * Exception in case of an impossible route of the schedule
 */
public class ImpossibleRouteException extends Exception{
    /**
     * Serial Version UID of the Class
     */
    @Serial
    private static final long serialVersionUID = 0L;
    public ImpossibleRouteException() {
        super("Percurso impossível.");
    }
}
