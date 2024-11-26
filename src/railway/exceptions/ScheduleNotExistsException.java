package railway.exceptions;

/**
 * Exception if there is no schedule in the system
 */
public class ScheduleNotExistsException extends Exception{
    public ScheduleNotExistsException () {
        super("Horário inexistente.");
    }
}
