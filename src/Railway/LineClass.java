package Railway;

import Railway.exceptions.ScheduleNotExistsException;
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
     * Inserts itself to its stations
     * @param name name of the line
     * @param stations list of stations of the line
     */
    public LineClass (String name, List<Station> stations) {
        this.name = name;
        this.stations = stations;
        for (int i = 0; i < stations.size(); i++) {
            stations.get(i).addLine(this);
        }
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
    public Iterator<ProtectedStation> getProtectedStations() {
        return new Iterator<ProtectedStation>() {
            private final Iterator<Station> iterator = stations.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ProtectedStation next() {
                return iterator.next();
            }

            @Override
            public void rewind() {
                iterator.rewind();
            }
        };
    }

    @Override
    public Station getStationByName(String name) {
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            if (station.getName().equalsIgnoreCase(name))
                return station;
        }
        return null;
    }

    @Override
    public Schedule bestRoute(Station departure, Station destination, Time prefferedTime) {
        Iterator<Entry<ScheduleClass.ScheduleEntry, Schedule>> it = getSchedules();
        Schedule bestRoute = null;
        Time bestTime = null;
        while (it.hasNext()) {
            Schedule route = it.next().getValue();
            Time arrivalTime = route.getArrivalForRoute(departure, destination);
            if (arrivalTime == null) continue;
            int arrivalDiffMin = prefferedTime.differenceMin(arrivalTime);
            if (arrivalTime.compareTo(prefferedTime) <= 0 &&
                    (bestTime == null || arrivalDiffMin < prefferedTime.differenceMin(bestTime))) {
                bestTime = arrivalTime;
                bestRoute = route;
            }
        }
        return bestRoute;
    }

    @Override
    public void removeSchedule(ScheduleClass.ScheduleEntry entry)
            throws ScheduleNotExistsException {
        if (schedules.remove(entry) == null)
            throw new ScheduleNotExistsException();
    }

    @Override
    public void addSchedule(Schedule schedule) {
        Schedule s = schedules.insert(schedule.getDepartureEntry(), schedule);
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
    public int getStationIndex(Station station) {
        return stations.find(station);
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
