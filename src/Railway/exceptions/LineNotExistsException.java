package Railway.exceptions;

public class LineNotExistsException extends Exception{
    public LineNotExistsException () {
        super("Linha inexistente.");
    }
}
