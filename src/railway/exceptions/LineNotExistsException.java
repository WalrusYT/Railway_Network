package railway.exceptions;

/**
 * Exception if there is no line with a given name in the system
 */
public class LineNotExistsException extends Exception{
    public LineNotExistsException () {
        super("Linha inexistente.");
    }
}
