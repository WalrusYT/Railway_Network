package Railway.exceptions;

public class LineAlreadyExistsException extends Exception{
    public LineAlreadyExistsException () {
        super("Linha existente.");
    }
}
