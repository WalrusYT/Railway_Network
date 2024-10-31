package Railway;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;
import Railway.exceptions.*;

import java.io.Serializable;

/**
 * This interface represents a top interface of the Railway
 */
public interface Railway extends Serializable {
    /**
     * Inserts a line to the railway network with the given {@link String} name
     * and {@link List<String>} stations
     * @param name name of the line
     * @param stations list of the stations of the line
     * @throws LineAlreadyExistsException if there is a line with the same name
     * in the system already.
     */
    void insertLine(String name, List<String> stations) throws LineAlreadyExistsException;

    /**
     * Removes a line from the railway network by the given {@link String} name
     * @param name name of the line that should be removed
     * @throws LineNotExistsException if there is no line with the given name
     */
    void removeLine(String name) throws LineNotExistsException;

    /**
     * Returns an iterator of stations of a given {@link Line} line by its name
     * @param name name of the {@link Line} line
     * @return an {@link Iterator<Station>} iterator to iterate though the stations
     * of the line
     * @throws LineNotExistsException if there is no line with the given name
     */
    Iterator<Station> listStations (String name) throws LineNotExistsException;

    /**
     * Inserts a schedule to the given {@link Line} line with the given
     * train number and list of stations with times
     * @param name name of the {@link Line} line
     * @param number number of the train
     * @param entries pairs of (Station name , time )
     * @throws LineNotExistsException if there is no line with the given name
     * @throws InvalidScheduleException if the first station indicated is not terminal, or if
     * there is an overlap with another schedule or the times of arriving are not strictly
     * increasing
     * @throws StationNotExistsException if there is no {@link Station} station with the given name
     * @throws ScheduleNotExistsException if there is no such {@link Schedule} schedule with in the
     * given line
     */
    void insertSchedule(String name, int number, List<Entry<String, Time>> entries)
            throws LineNotExistsException, InvalidScheduleException,
            StationNotExistsException, ScheduleNotExistsException;

    /**
     * Removes a schedule from the given {@link Line} line with the given
     * train number, departure station and time of departure
     * @param name name of the {@link Line} line
     * @param station name of the departure {@link Station} station
     * @param time time of the departure
     * @throws ScheduleNotExistsException if there is no schedule
     * with the given parameters
     * @throws LineNotExistsException if there is no {@link Line} with the given name
     */
    void removeSchedule(String name, String station, Time time) throws ScheduleNotExistsException,
            LineNotExistsException;

    /**
     * Returns an {@link Iterator<Schedule>} of the given {@link Line} line
     * @param name name of the {@link Line} line
     * @param departureStation station of the departure of the {@link Schedule} schedule
     * @return an {@link Iterator<Schedule>} iterator to iterate
     * through the {@link Schedule} schedules
     * @throws LineNotExistsException if there is no {@link Line} line with the given name
     * @throws StationNotExistsException if there is no {@link Station} station
     * with the given name or if it's not a departure station
     */
    Iterator<Schedule> listSchedules (String name, String departureStation)
            throws LineNotExistsException, StationNotExistsException;

    /**
     * Finds out the best timetable of the given {@link Line} line by its name,
     * its station of the departure and destination, and arrival time
     * @param name name of the {@link Line} line
     * @param departureStation name o the {@link Station} departure station
     * @param destinationStation name of the {@link Station} destination station
     * @param arrivalTime {@link Time} time of the preferable arrival
     * @return the best timetable that suits to the preferences
     * @throws LineNotExistsException if there is no {@link Line} line with the given name
     * @throws ImpossibleRouteException if the given route is impossible
     * (e.g. the departure time is less that an arriving one)
     * @throws StationNotExistsException if there is no {@link Station} with the given name
     */
    Schedule bestTimetable(String name, String departureStation,
                           String destinationStation, Time arrivalTime)
            throws LineNotExistsException, ImpossibleRouteException, StationNotExistsException;

}
