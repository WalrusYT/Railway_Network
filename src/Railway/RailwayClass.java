package Railway;

import Railway.exceptions.*;
import dataStructures.*;

public class RailwayClass implements Railway {
    private static final long serialVersionUID = 0L;

    List<Line> lines;
    public RailwayClass() {
        lines = new MyArrayList<>();
    }

    @Override
    public void insertLine(String name, List<String> stations) throws LineAlreadyExistsException {
        if (lineExists(name)) throw new LineAlreadyExistsException();
        List<Station> stationList = new MyArrayList<>();
        for (int i = 0; i < stations.size(); i++) {
            Station station = new StationClass(stations.get(i));
            stationList.addLast(station);
        }
        Line line = new LineClass(name, stationList);
        lines.addLast(line);
    }

    private boolean lineExists (String name) {
        try {
            getLine(name);
            return true;
        } catch (LineNotExistsException e) {
            return false;
        }
    }

    /**
     * Auxiliary method to get a line by its name
     * @param name name of the {@link Line} line
     * @return the {@link Line} line with the given name
     * @throws LineNotExistsException if there is no line with the given name
     */
    private Line getLine(String name) throws LineNotExistsException {
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            if (line.getName().equals(name)) {
                return line;
            }
        }
        throw new LineNotExistsException();
    }

    @Override
    public void removeLine(String name) throws LineNotExistsException {
        Line line = getLine(name);
        lines.remove(line);
        // delete the schedules of this line?
    }

    @Override
    public Iterator<Station> listStations (String name) throws LineNotExistsException {
        Line line = getLine(name);
        return line.getStations();
    }

    @Override
    public Schedule bestTimetable(String name, String departureStation, String destinationStation, Time arrivalTime)
            throws LineNotExistsException, ImpossibleRouteException, StationNotExistsException {
        Line line = getLine(name);
        Station departure = line.getStationByName(departureStation);
        Station destination = line.getStationByName(destinationStation);
        if (departure == null || destination == null) throw new StationNotExistsException();
        return line.bestRoute(departure, destination, arrivalTime);
    }

    @Override
    public void insertSchedule(String name, int number, List<Entry<String, Time>> entriesRaw)
            throws InvalidScheduleException, LineNotExistsException, ScheduleNotExistsException {
        Line line = getLine(name);
        List<ScheduleClass.ScheduleEntry> entries = new MyArrayList<>();
        ScheduleClass.ScheduleEntry firstEntry = createScheduleEntry(line, entriesRaw.getFirst());
        if (!line.isStationTerminal(firstEntry.getStation())) throw new InvalidScheduleException();
        Time prevTime = firstEntry.getTime();
        entries.addLast(firstEntry);
        for (int i = 1; i < entriesRaw.size() ; i++) {
            ScheduleClass.ScheduleEntry entry = createScheduleEntry(line, entriesRaw.get(i));
            if (entry.getTime().compareTo(prevTime) <= 0)
                throw new InvalidScheduleException();
            entries.addLast(entry);
        }
        line.addSchedule(new ScheduleClass(number, entries));
    }
    
    @Override
    public void removeSchedule(String name, String station, Time time)
            throws ScheduleNotExistsException, LineNotExistsException {
        Line line = getLine(name);
        ScheduleClass.ScheduleEntry entry = createScheduleEntry(line, new EntryClass<>(station, time));
        line.removeSchedule(entry);
    }

    @Override
    public Iterator<Schedule> listSchedules(String name, String departureStation) throws LineNotExistsException,
            StationNotExistsException {
        Line line = getLine(name);
        Station station = line.getStationByName(departureStation);
        if (station == null || !line.isStationTerminal(station)) throw new StationNotExistsException();
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
}
