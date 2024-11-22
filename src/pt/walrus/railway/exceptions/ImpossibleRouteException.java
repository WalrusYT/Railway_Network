package pt.walrus.railway.exceptions;

public class ImpossibleRouteException extends Exception{
    public ImpossibleRouteException() {
        super("Percurso impossível.");
    }
}
