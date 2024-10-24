package Railway;

import dataStructures.Iterator;
import dataStructures.List;
import Railway.exceptions.*;

public interface Railway {
    void insertLine(String name, List<String> stations) throws LineAlreadyExistsException;
    void removeLine(String name) throws LineNotExistsException;
    Iterator<Station> listStations (String name) throws LineNotExistsException;
    void insertSchedule(String name, int number, List<String> entries) throws InvalidScheduleException, LineNotExistsException, InvalidScheduleException, StationNotExistsException;
    Iterator<Schedule> listSchedules (String name, String departureStation) throws LineNotExistsException,
            StationNotExistsException, InvalidScheduleException;
    Schedule bestTimetable(String name, String departureStation, String destinationStation, String arrivalTime)
            throws LineNotExistsException, ImpossibleRouteException, StationNotExistsException;
}
