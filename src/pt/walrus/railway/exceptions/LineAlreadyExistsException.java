package pt.walrus.railway.exceptions;

/**
 * Exception if there is already a line in a system with the same name
 */
public class LineAlreadyExistsException extends Exception{
    public LineAlreadyExistsException () {
        super("Linha existente.");
    }
}
