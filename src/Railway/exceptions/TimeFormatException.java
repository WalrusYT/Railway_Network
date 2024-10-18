package Railway.exceptions;

public class TimeFormatException extends Exception {
    public TimeFormatException() {
        super("Unable to parse time");
    }
}
