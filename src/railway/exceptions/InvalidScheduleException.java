package railway.exceptions;

import java.io.Serial;

/**
 * Exception in case of an invalid schedule
 */
public class InvalidScheduleException extends Exception {
    /**
     * Serial Version UID of the Class
     */
    @Serial
    private static final long serialVersionUID = 0L;
    public InvalidScheduleException() {
        super("Horário inválido.");
    }
}
