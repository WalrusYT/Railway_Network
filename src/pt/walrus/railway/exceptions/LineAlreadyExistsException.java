package pt.walrus.railway.exceptions;

public class LineAlreadyExistsException extends Exception{
    public LineAlreadyExistsException () {
        super("Linha existente.");
    }
}
