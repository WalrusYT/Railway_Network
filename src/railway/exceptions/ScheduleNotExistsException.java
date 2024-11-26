package railway.exceptions;

import java.io.Serial;

/**
 * Exception if there is no schedule in the system
 */
public class ScheduleNotExistsException extends Exception{
    /**
     * Serial Version UID of the Class
     */
    @Serial
    private static final long serialVersionUID = 0L;
    public ScheduleNotExistsException () {
        super("Horário inexistente.");
    }
}
