package Railway;

import Railway.exceptions.ImpossibleRouteException;
import Railway.exceptions.InvalidScheduleException;
import Railway.exceptions.ScheduleNotExistsException;
import Railway.exceptions.StationNotExistsException;
import dataStructures.*;

/**
 * The Line class represents a line with a name, list of stations and schedules
 */
public class LineClass implements Line {
    /**
     * Serializable class a version number
     */
    private static final long serialVersionUID = 0L;
    /**
     * List of the {@link Station} stations of the line
     */
    private final List<Station> stations;
    /**
     * Dictionary of schedules of the line
     */
    private final Dictionary<ScheduleClass.ScheduleEntry, Schedule> schedules;
    /**
     * Name of the line
     */
    private final String name;

    /**
     * Constructs an object {@link LineClass} with the given name and list of stations
     * @param name name of the line
     * @param stations list of stations of the line
     */
    public LineClass (String name, List<Station> stations) {
        this.name = name;
        this.stations = stations;
        schedules = new OrderedDoubleList<>(new ScheduleEntryComparatorByTime());
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
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
        Iterator<Entry<ScheduleClass.ScheduleEntry, Schedule>> it = getSchedules();
        Schedule bestRoute = null;
        Time bestTime = null;
        while (it.hasNext()) {
            Schedule route = it.next().getValue();
            Time arrivalTime = route.getArrivalForRoute(departure, destination);
            // if (arrivalTime == null) continue; вместо exception, поскольку нужная станция прибытия может оказаться
            // в следующем расписании
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
    public void removeSchedule(ScheduleClass.ScheduleEntry entry) throws ScheduleNotExistsException {
        if (schedules.remove(entry) == null)
            throw new ScheduleNotExistsException();
    }

    @Override
    public void addSchedule(Schedule schedule) throws InvalidScheduleException {
        Iterator<Schedule> it = getSchedulesByStation(schedule.getDepartureStation());
        while (it.hasNext()) {
            if (it.next().equals(schedule)) throw new InvalidScheduleException();
        }
        schedules.insert(schedule.getDepartureEntry(), schedule);
    }

    @Override
    public Iterator<Entry<ScheduleClass.ScheduleEntry, Schedule>> getSchedules() {
        return schedules.iterator();
    }

    @Override
    public boolean isStationTerminal(Station station) {
        return station.equals(stations.getFirst()) || station.equals(stations.getLast());
    }

    @Override
    public Iterator<Schedule> getSchedulesByStation(Station station) {
        List<Schedule> schedulesByStation = new MyArrayList<>();
        Iterator<Entry<ScheduleClass.ScheduleEntry, Schedule>> iterator = schedules.iterator();
        while (iterator.hasNext()) {
            Schedule schedule = iterator.next().getValue();
            if (schedule.getDepartureStation().equals(station)) {
                schedulesByStation.addLast(schedule);
            }
        }
        return schedulesByStation.iterator();
    }

}
