package Railway.exceptions;

public class StationNotExistsException extends Exception{
    public StationNotExistsException () {
        super("Estação de partida inexistente.");
    }
}
