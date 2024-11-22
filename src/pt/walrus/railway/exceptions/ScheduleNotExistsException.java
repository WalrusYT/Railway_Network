package pt.walrus.railway.exceptions;

public class ScheduleNotExistsException extends Exception{
    public ScheduleNotExistsException () {
        super("Horário inexistente.");
    }
}
