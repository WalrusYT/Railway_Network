package Railway;

import Railway.exceptions.ImpossibleRouteException;
import Railway.exceptions.StationNotExistsException;
import dataStructures.Entry;
import dataStructures.Iterator;

public interface Line {
    String getName();
    Iterator<Station> getStations();
    Station getStationByName(String name);
    void addSchedule(Schedule schedule);
    Iterator<Entry<Time, Schedule>> getSchedules();
    boolean isStationTerminal(Station station);
    Schedule bestRoute(Station departure, Station destination, Time prefferedTime)
            throws ImpossibleRouteException, StationNotExistsException;
    Iterator<Schedule> getSchedulesByStation(Station station);
}
