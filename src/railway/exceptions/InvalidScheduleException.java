package railway.exceptions;

/**
 * Exception in case of an invalid schedule
 */
public class InvalidScheduleException extends Exception {
    public InvalidScheduleException() {
        super("Horário inválido.");
    }
}
