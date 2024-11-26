package railway.exceptions;

/**
 * Exception in case of an impossible route of the schedule
 */
public class ImpossibleRouteException extends Exception{
    public ImpossibleRouteException() {
        super("Percurso impossível.");
    }
}
