package Railway;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;
import Railway.exceptions.*;

import java.io.Serializable;

public interface Railway extends Serializable {
    void insertLine(String name, List<String> stations) throws LineAlreadyExistsException;
    void removeLine(String name) throws LineNotExistsException;
    Iterator<Station> listStations (String name) throws LineNotExistsException;
    void insertSchedule(String name, int number, List<Entry<String, Time>> entries) throws InvalidScheduleException, LineNotExistsException, InvalidScheduleException, StationNotExistsException;
    void removeSchedule(String name, String station, Time time) throws InvalidScheduleException, LineNotExistsException;
    Iterator<Schedule> listSchedules (String name, String departureStation) throws LineNotExistsException,
            StationNotExistsException, InvalidScheduleException;
    Schedule bestTimetable(String name, String departureStation, String destinationStation, Time arrivalTime)
            throws LineNotExistsException, ImpossibleRouteException, StationNotExistsException;
    Line getLine(String name) throws LineNotExistsException;
}
