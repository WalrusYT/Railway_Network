package Railway;

import Railway.exceptions.InvalidScheduleException;
import Railway.exceptions.ScheduleNotExistsException;
import java.io.Serializable;

/**
 * This interface represents a line in the Railway Network.
 */
public interface Line extends Serializable, ProtectedLine, Comparable<Line> {

    /**
     * Adds a {@link Schedule} schedule to the line
     * @param schedule schedule that should be inserted
     */
    void addSchedule(Schedule schedule) throws InvalidScheduleException;

    /**
     * Removes a schedule with the given terminal of {@link ScheduleClass.ScheduleEntry}
     * @param entry entry that points to the terminal station of the schedule
     * @throws ScheduleNotExistsException if the {@link Schedule} schedule does not exist.
     */
    void removeSchedule(ScheduleClass.ScheduleEntry entry) throws ScheduleNotExistsException;

    boolean isScheduleValid(Schedule schedule);
}
