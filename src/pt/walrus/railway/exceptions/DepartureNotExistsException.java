package pt.walrus.railway.exceptions;

public class DepartureNotExistsException extends Exception{
    public DepartureNotExistsException() {
        super("Estação de partida inexistente.");
    }
}
