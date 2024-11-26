package railway;

import dataStructures.Entry;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * The interface Protected line.
 */
public interface ProtectedLine extends Serializable {
    /**
     * Returns a line name
     *
     * @return a {@link String} name of the line
     */
    String getName();

    /**
     * Returns an iterator of stations of the line
     *
     * @param direction the direction
     * @return a {@link Iterator <Station>}  to iterate through the stations
     */
    Iterator<Station> getStations(Direction direction);

    /**
     * Returns an iterator of "protected"* stations of the line
     * *"protected" means that they cannot be modified (for the safe output in Main)
     *
     * @return a {@link Iterator<Station>}  to iterate through the stations
     */
    Iterator<ProtectedStation> getProtectedStations();

    /**
     * Returns a station by its {@link String} name
     *
     * @param name name of the station
     * @return {@link Station} station with the given name, or null it doesn't exist
     */
    Station getStationByName(String name);

    /**
     * Returns the {@link Iterator<Entry<ScheduleClass.ScheduleEntry,Schedule>>} to iterate
     * through the schedules in a line
     *
     * @return an {@link Iterator<Entry<ScheduleClass.ScheduleEntry,Schedule>>} to iterate
     */
    Iterator<Entry<ScheduleClass.ScheduleEntry, Schedule>> getSchedules();

    /**
     * Checks if the given station is a terminal one or not
     *
     * @param station that should be checked if it's terminal
     * @return <code>true</code> is the station is terminal station and <code>false</code> otherwise
     */
    boolean isStationTerminal(Station station);

    /**
     * Finds out the best timetable of the line with the given departure, destination and time
     *
     * @param departure     station of the departure
     * @param destination   station of the arrival
     * @param prefferedTime at what time it's better to arrive
     * @return a {@link Schedule} that better suits for the requested preferences
     */
    Schedule bestRoute(Station departure, Station destination, Time prefferedTime);

    /**
     * Returns an iterator of the schedules that contains given {@link Station}
     *
     * @param station that should be in the schedulec
     * @return an {@link Iterator<Schedule>} to iterate through the schedules
     */
    Iterator<Schedule> getSchedulesByStation(Station station);

    /**
     * Returns direction of the schedule by the departure station
     *
     * @param station the station of depart
     * @return the direction of the train
     */
    Direction getDirectionByDeparture(Station station);

    /**
     * Checks if the given Schedule is valid for the insertion
     *
     * @param schedule the schedule that should be checked
     * @return <code>true</code> if the schedule can be safely added to the line
     * and <code>false</code> otherwise.
     */
    boolean isScheduleValid(Schedule schedule);
}
