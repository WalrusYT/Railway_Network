package Railway;

import Railway.exceptions.ImpossibleRouteException;
import Railway.exceptions.StationNotExistsException;
import dataStructures.*;

public class LineClass implements Line {
    private final List<Station> stations;
    private final Dictionary<Time, Schedule> schedules;
    String name;
    public LineClass (String name, List<Station> stations) {
        this.name = name;
        this.stations = stations;
        schedules = new OrderedDoubleList<>();
    }

    public String getName() {
        return name;
    }

    public Iterator<Station> getStations() {
        return stations.iterator();
    }

    @Override
    public Station getStationByName(String name) {
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            if (station.getName().equals(name))
                return station;
        }
        return null;
    }

    @Override
    public Schedule bestRoute(Station departure, Station destination, Time prefferedTime)
            throws ImpossibleRouteException, StationNotExistsException {
        Iterator<Entry<Time, Schedule>> it = getSchedules();
        Schedule bestRoute = null;
        Time bestTime = null;
        while (it.hasNext()) {
            Schedule route = it.next().getValue();
            Time arrivalTime = route.getArrivalForRoute(departure, destination);
            Time diff = prefferedTime.difference(arrivalTime);
            if (arrivalTime.compareTo(prefferedTime) <= 0 &&
                    (bestTime == null || prefferedTime.difference(bestTime).compareTo(diff) > 0)) {
                bestTime = arrivalTime;
                bestRoute = route;
            }
        }
        return bestRoute;
    }

    @Override
    public void addSchedule(Schedule schedule) {
        schedules.insert(schedule.getDepartureTime(), schedule);
    }

    @Override
    public Iterator<Entry<Time, Schedule>> getSchedules() {
        return schedules.iterator();
    }

    @Override
    public boolean isStationTerminal(Station station) {
        return station.equals(stations.getFirst()) || station.equals(stations.getLast());
    }

    @Override
    public Iterator<Schedule> getSchedulesByStation(Station station) {
        List<Schedule> schedulesByStation = new MyArrayList<>();
        Iterator<Entry<Time, Schedule>> iterator = schedules.iterator();
        while (iterator.hasNext()) {
            Schedule schedule = iterator.next().getValue();
            if (schedule.getDepartureStation().equals(station)) {
                schedulesByStation.addLast(schedule);
            }
        }
        return schedulesByStation.iterator();
    }

}
