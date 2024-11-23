package pt.walrus.railway;

import pt.walrus.railway.exceptions.InvalidScheduleException;
import pt.walrus.railway.exceptions.ScheduleNotExistsException;

/**
 * This interface represents a line in the pt.walrus.Railway Network.
 */
public interface Line extends ProtectedLine, Comparable<Line> {

    /**
     * Adds a {@link Schedule} schedule to the line
     *
     * @param schedule schedule that should be inserted
     * @throws InvalidScheduleException the invalid schedule exception
     */
    void addSchedule(Schedule schedule) throws InvalidScheduleException;

    /**
     * Removes a schedule with the given terminal of {@link ScheduleClass.ScheduleEntry}
     *
     * @param entry entry that points to the terminal station of the schedule
     * @throws ScheduleNotExistsException if the {@link Schedule} schedule does not exist.
     */
    void removeSchedule(ScheduleClass.ScheduleEntry entry) throws ScheduleNotExistsException;

    /**
     * Is schedule valid boolean.
     *
     * @param schedule the schedule
     * @return the boolean
     */
    boolean isScheduleValid(Schedule schedule);
}
