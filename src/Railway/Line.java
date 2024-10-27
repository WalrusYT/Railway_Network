package Railway;

import Railway.exceptions.ImpossibleRouteException;
import Railway.exceptions.InvalidScheduleException;
import Railway.exceptions.StationNotExistsException;
import dataStructures.Entry;
import dataStructures.Iterator;

import java.io.Serializable;

public interface Line extends Serializable {
    String getName();
    Iterator<Station> getStations();
    Station getStationByName(String name);
    void addSchedule(Schedule schedule);
    Iterator<Entry<ScheduleClass.ScheduleEntry, Schedule>> getSchedules();
    boolean isStationTerminal(Station station);
    Schedule bestRoute(Station departure, Station destination, Time prefferedTime)
            throws ImpossibleRouteException, StationNotExistsException;
    void removeSchedule(ScheduleClass.ScheduleEntry entry) throws InvalidScheduleException;
    Iterator<Schedule> getSchedulesByStation(Station station);
}
