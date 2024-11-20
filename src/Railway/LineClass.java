package Railway;

import Railway.exceptions.ScheduleNotExistsException;
import dataStructures.*;
import Railway.ScheduleClass.ScheduleEntry;

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
    private final TwoWayList<Station> stations;
    /**
     * Dictionary of schedules of the line
     */
    private final Dictionary<ScheduleEntry, Schedule> schedules;
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
    public LineClass (String name, TwoWayList<Station> stations) {
        this.name = name;
        this.stations = stations;
        this.schedules = new BinarySearchTree<>();
        for (int i = 0; i < stations.size(); i++) {
            stations.get(i).addLine(this);
        }
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Iterator<Station> getStations(Direction direction) {
        if (direction == Direction.FORWARD) return stations.iterator();
        return new Iterator<>() {
            private final TwoWayIterator<Station> iterator = stations.twoWayIterator();
            // reverse iterator before doing stuff
            { this.rewind(); }

            @Override
            public boolean hasNext() {
                return iterator.hasPrevious();
            }

            @Override
            public Station next() throws NoSuchElementException {
                return iterator.previous();
            }

            @Override
            public void rewind() {
                iterator.fullForward();
            }
        };
    }

    @Override
    public Iterator<ProtectedStation> getProtectedStations() {
        return new Iterator<>() {
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
        Iterator<Entry<ScheduleEntry, Schedule>> it = getSchedules();
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
    public void removeSchedule(ScheduleEntry entry)
            throws ScheduleNotExistsException {
        if (schedules.remove(entry) == null)
            throw new ScheduleNotExistsException();
    }

    @Override
    public void addSchedule(Schedule schedule) {
        schedules.insert(schedule.getDepartureEntry(), schedule);
        Iterator<ScheduleEntry> entries = schedule.getEntries();
        while (entries.hasNext()) {
            ScheduleEntry entry = entries.next();
            entry.getStation().addPassingTrain(entry.getTime(), schedule.getTrainNumber());
        }
     }

    @Override
    public Iterator<Entry<ScheduleEntry, Schedule>> getSchedules() {
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

    @Override
    public Direction getDirectionByDeparture(Station station) {
        if (station.equals(stations.getFirst())) return Direction.FORWARD;
        if (station.equals(stations.getLast())) return Direction.BACKWARDS;
        return null;
    }

    @Override
    public int compareTo(Line o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean isScheduleValid(Schedule schedule) {
        // checking if there is an overlap on the same line (train overtakes)
        Iterator<Entry<ScheduleEntry, Schedule>> schedules = this.schedules.iterator();
        while (schedules.hasNext()) {
            boolean overlap = schedules.next().getValue().isOverlapping(schedule);
            if (overlap) return false;
        }
        // checking if there is an arrival at the same time on different lines (train collisions)
        Iterator<ScheduleEntry> entries = schedule.getEntries();
        while (entries.hasNext()) {
            ScheduleEntry entry = entries.next();
            boolean arrive = entry.getStation().isTrainArrive(entry.getTime());
            if (arrive) return false;
        }
        return true;
    }
}
