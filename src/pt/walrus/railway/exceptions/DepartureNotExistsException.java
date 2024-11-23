package pt.walrus.railway.exceptions;

/**
 * Exception if there is no departure station in a line
 */
public class DepartureNotExistsException extends Exception{
    public DepartureNotExistsException() {
        super("Estação de partida inexistente.");
    }
}
