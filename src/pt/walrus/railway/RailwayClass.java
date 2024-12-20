package pt.walrus.railway;

import pt.walrus.railway.exceptions.*;
import pt.walrus.dataStructures.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.lang.reflect.Field;

/**
 * The Railway class represents a railway system with a collection of lines and stations
 */
public class RailwayClass implements Railway {
    /**
     * Serializable class a version number
     */
    private static final long serialVersionUID = 0L;
    /**
     * Collection of stations of the network
     */
    private final Dictionary<String, Station> stations;
    /**
     * Collection of lines of the network
     */
    private final Dictionary<String, Line> lines;


    /**
     * Constructs an object Railway Network
     */
    public RailwayClass() {
        lines = new SepChainHashTable<>();
        stations = new SepChainHashTable<>();
    }

    @Override
    public void insertLine(String name, List<String> stations) throws LineAlreadyExistsException {
        if (this.getLine(name) != null) throw new LineAlreadyExistsException();
        TwoWayList<Station> stationList = new DoubleList<>();
        for (int i = 0; i < stations.size(); i++) {
            String stationName = stations.get(i);
            Station station = this.getStation(stationName);
            if (station == null) {
                station = new StationClass(stationName);
                this.stations.insert(stationName.toLowerCase(), station);
            }
            stationList.addLast(station);
        }
        Line line = new LineClass(name, stationList);
        lines.insert(name.toLowerCase(), line);
    }

    /**
     * Auxiliary method to get a station by its name
     * @param name name of the {@link Station} station
     * @return the {@link Station} line with the given name
     */
    private Station getStation(String name) {
        return stations.find(name.toLowerCase());
    }

    /**
     * Auxiliary method to get a line by its name
     * @param name name of the {@link Line} line
     * @return the {@link Line} line with the given name
     */
    private Line getLine(String name) {
        return lines.find(name.toLowerCase());
    }

    @Override
    public void removeLine(String name) throws LineNotExistsException, ScheduleNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        Iterator<Station> stations = line.getStations(Direction.FORWARD);
        // removes all the station of the line
        while (stations.hasNext()) {
            Station station = stations.next();
            station.removeLine(line);
            if (!station.hasLines()) this.stations.remove(station.getName().toLowerCase());
        }
        // removes all the schedules of the line
        Iterator<Entry<ScheduleClass.ScheduleEntry, Schedule>> schedules = line.getSchedules();
        while (schedules.hasNext()) {
            ScheduleClass.ScheduleEntry s = schedules.next().getKey();
            line.removeSchedule(s);
        }
        lines.remove(line.getName().toLowerCase());
    }

    @Override
    public Iterator<ProtectedStation> listStations(String name) {
        Line line = getLine(name);
        return line == null ? null : line.getProtectedStations();
    }

    @Override
    public Iterator<ProtectedLine> listLines(String name) {
        Station station = this.getStation(name);
        return station == null ? null : station.getProtectedLines();
    }

    @Override
    public Schedule bestTimetable(String name, String departureStation,
                                  String destinationStation, Time arrivalTime)
            throws LineNotExistsException, ImpossibleRouteException, DepartureNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        Station departure = line.getStationByName(departureStation);
        if (departure == null) throw new DepartureNotExistsException();
        Station destination = line.getStationByName(destinationStation);
        if (destination == null) throw new ImpossibleRouteException();
        Schedule best = line.bestRoute(departure, destination, arrivalTime);
        if (best == null) throw new ImpossibleRouteException();
        return best;
    }

    @Override
    public void insertSchedule(String name, int number, List<Entry<String, Time>> entriesRaw)
            throws InvalidScheduleException, ScheduleNotExistsException, LineNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        List<ScheduleClass.ScheduleEntry> entries = new MyArrayList<>();
        for (int i = 0; i < entriesRaw.size(); i++)
            entries.addLast(createScheduleEntry(line, entriesRaw.get(i)));
        Direction direction = line.getDirectionByDeparture(entries.getFirst().getStation());
        if (direction == null) throw new InvalidScheduleException();
        Schedule schedule = new ScheduleClass(number, entries, direction);
        if (!line.isScheduleValid(schedule)) throw new InvalidScheduleException();
        line.addSchedule(schedule);
    }
    
    @Override
    public void removeSchedule(String name, String station, Time time)
            throws ScheduleNotExistsException, LineNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        ScheduleClass.ScheduleEntry entry =
                createScheduleEntry(line, new EntryClass<>(station, time));
        line.removeSchedule(entry);
    }

    @Override
    public Iterator<Entry<StationClass.ArrivalEntry, Train>> passingTrainsOfStation(String name) {
        Station station = this.getStation(name);
        return station == null ? null : station.getPassingTrains();
    }

    @Override
    public Iterator<Schedule> listSchedules(String name, String departureStation)
            throws LineNotExistsException, DepartureNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        Station station = line.getStationByName(departureStation);
        if (station == null || !line.isStationTerminal(station))
            throw new DepartureNotExistsException();
        return line.getSchedulesByStation(station);
    }

    /**
     * Creates a schedule entry by the given {@link Line} and pairs of (station , time)
     * @param line {@link Line} of the schedule entry
     * @param entry the pair of (station , time)
     * @return an instance of {@link ScheduleClass.ScheduleEntry}
     * @throws ScheduleNotExistsException if there is no station in the given line
     */
    private ScheduleClass.ScheduleEntry createScheduleEntry(Line line, Entry<String, Time> entry)
            throws ScheduleNotExistsException {
        Station station = line.getStationByName(entry.getKey());
        if (station == null) throw new ScheduleNotExistsException();
        return new ScheduleClass.ScheduleEntry(station, entry.getValue());
    }

    /*
    Custom serialization for Railway class.
    Using java's default serialization mechanism fails with NullPointerException
    when inserting Lines into the Set<Line> lines field of a StationClass during deserialization.
    The Set in question is marked as transient in order to exclude it from default
    serialization mechanism - this will ensure that the Set is not serialized.
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    /*
    Iterate through Stations' of each Line and modify each Stations Set<Line> field.
    Using reflection here to initialize the Set in question and prevent NullPointerException

    This solution is approved by professor Bernardo Toninho
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Iterator<Entry<String, Line>> linesIter = lines.iterator();
        while (linesIter.hasNext()) {
            Line line = linesIter.next().getValue();
            Iterator<Station> stationsIter = line.getStations(Direction.FORWARD);
            while (stationsIter.hasNext()) {
                Station station = stationsIter.next();
                try {
                    Field f = StationClass.class.getDeclaredField("lines");
                    f.setAccessible(true);
                    if (f.get(station) == null)
                        f.set(station, new TreeSet<>());
                } catch (Exception e) {
                    System.out.println("Deserialization failed :(");
                }
                station.addLine(line);
            }
        }
    }
}
