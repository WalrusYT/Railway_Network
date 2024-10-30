package Railway;

import Railway.exceptions.ImpossibleRouteException;
import Railway.exceptions.InvalidScheduleException;
import Railway.exceptions.ScheduleNotExistsException;
import Railway.exceptions.StationNotExistsException;
import dataStructures.Entry;
import dataStructures.Iterator;
import java.io.Serializable;

/**
 * This interface represents a line in the Railway Network.
 */
public interface Line extends Serializable {
    /**
     * Returns a line name
     * @return a {@link String} name of the line
     */
    String getName();

    /**
     * Returns an iterator of stations of the line
     * @return a {@link Iterator<Station>}  to iterate through the stations
     */
    Iterator<Station> getStations();

    /**
     * Returns a station by its {@link String} name
     * @param name name of the station
     * @return {@link Station} station with the given name, or null it doesn't exist
     */
    Station getStationByName(String name);

    /**
     * Adds a {@link Schedule} schedule to the line
     * @param schedule schedule that should be inserted
     */
    void addSchedule(Schedule schedule) throws InvalidScheduleException;
    /**
     * Returns the {@link Iterator<Entry<ScheduleClass.ScheduleEntry, Schedule>>} to iterate
     * through the schedules in a line
     * @return an {@link Iterator<Entry<ScheduleClass.ScheduleEntry, Schedule>>} to iterate
     */
    Iterator<Entry<ScheduleClass.ScheduleEntry, Schedule>> getSchedules();

    /**
     * Checks if the given station is a terminal one or not
     * @param station that should be checked if it's terminal
     * @return <code>true</code> is the station is terminal station 
     * and <code>false</code> otherwise
     */
    boolean isStationTerminal(Station station);

    /**
     * Finds out the best timetable of the line with the given departure, destination and time
     * @param departure station of the departure
     * @param destination station of the arrival
     * @param prefferedTime at what time it's better to arrive
     * @return a {@link Schedule} that better suits for the requested preferences
     */
    Schedule bestRoute(Station departure, Station destination, Time prefferedTime);

    /**
     * Removes a schedule with the given terminal of {@link ScheduleClass.ScheduleEntry}
     * @param entry entry that points to the terminal station of the schedule
     * @throws ScheduleNotExistsException if the {@link Schedule} schedule does not exist.
     */
    void removeSchedule(ScheduleClass.ScheduleEntry entry) throws ScheduleNotExistsException;

    /**
     * Returns an iterator of the schedules that contains given {@link Station}
     * @param station that should be in the schedule
     * @return an {@link Iterator<Schedule>} to iterate through the schedules
     */
    Iterator<Schedule> getSchedulesByStation(Station station);
}
