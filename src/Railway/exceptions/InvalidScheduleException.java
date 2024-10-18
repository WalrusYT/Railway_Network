package Railway.exceptions;

public class InvalidScheduleException extends Exception {
    public InvalidScheduleException() {
        super("Horário inválido.");
    }
}
