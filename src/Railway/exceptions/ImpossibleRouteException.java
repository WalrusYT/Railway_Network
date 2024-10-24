package Railway.exceptions;

public class ImpossibleRouteException extends Exception{
    public ImpossibleRouteException() {
        super("Percurso impossível.");
    }
}
