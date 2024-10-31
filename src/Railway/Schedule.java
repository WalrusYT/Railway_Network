package Railway;

import Railway.exceptions.ImpossibleRouteException;
import Railway.exceptions.StationNotExistsException;
import dataStructures.Iterator;
import java.io.Serializable;

/**
 * This interface represents a schedule in the Railway Network.
 */
public interface Schedule extends Serializable {
    /**
     * Returns a train number
     * @return a {@link int} number of the train
     */
    int getTrainNumber();

    /**
     * Returns an iterator of entries of the schedule
     * @return a {@link Iterator<ScheduleClass.ScheduleEntry>}  to iterate through the schedule
     */
    Iterator<ScheduleClass.ScheduleEntry> getEntries();

     /**
     * Returns the first entry in the schedule
     * @return a {@link ScheduleClass.ScheduleEntry} initial departure station and time
     */
    ScheduleClass.ScheduleEntry getDepartureEntry();

    /**
     * Returns the departure station
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
}
