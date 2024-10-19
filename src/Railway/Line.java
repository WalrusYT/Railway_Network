package Railway;

import Railway.dataStructures.Iterator;

public interface Line {
    String getName();
    Iterator<Station> getStations();
    Station getStationByName(String name);
    void addSchedule(Schedule schedule);
    Iterator<Schedule> getSchedules();
    boolean isStationTerminal(Station station);
}
