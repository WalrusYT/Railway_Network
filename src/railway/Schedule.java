package railway;

import dataStructures.Iterator;
import java.io.Serializable;

/**
 * This interface represents a schedule in the Railway Network.
 */
public interface Schedule extends Serializable {
    /**
     * Returns a train number
     *
     * @return a number of the train of the schedule
     */
    int getTrainNumber();

    /**
     * Returns a train of the schedule
     * @return a {@link Train} number of the train
     */
    Train getTrain();

    /**
     * Returns an iterator of entries of the schedule
     *
     * @return a {@link Iterator<ScheduleClass.ScheduleEntry>}  to iterate through the schedule
     */
    Iterator<ScheduleClass.ScheduleEntry> getEntries();

    /**
     * Returns the first entry in the schedule
     *
     * @return a {@link ScheduleClass.ScheduleEntry} initial departure station and time
     */
    ScheduleClass.ScheduleEntry getDepartureEntry();

    /**
     * Returns the departure station
     *
     * @return the {@link Station} departure station of the schedule
     */
    Station getDepartureStation();

    /**
     * Returns the scheduled arrival time at the specified destination station,
     * given a departure from a specified station
     *
     * @param departure   the {@link Station} from which the train is departing
     * @param destination the {@link Station} to which the arrival time is requested
     * @return a {@link Time} object representing the arrival time at the destination
     */
    Time getArrivalForRoute(Station departure, Station destination);

    /**
     * Checks if the given schedule overlaps with this schedule
     *
     * @param schedule the schedule that should be checked for overlapping
     * @return <code>true</code> is the schedule are overlapping
     * and <code>false</code> otherwise.
     */
    boolean isOverlapping(Schedule schedule);

    /**
     * Returns a direction of the schedule
     *
     * @return a direction of the schedule
     */
    Direction getDirection();
}
