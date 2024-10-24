package Railway;

import dataStructures.MyArrayList;
import dataStructures.Iterator;
import dataStructures.List;
import Railway.exceptions.*;

public class RailwayClass implements Railway {
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
        return getLine(name) != null;
    }

    private Line getLine(String name) {
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            if (line.getName().equals(name)) {
                return line;
            }
        }
        return null;
    }

    @Override
    public void removeLine(String name) throws LineNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        lines.remove(line);
        // удалить линию из всех станций......
    }

    @Override
    public Iterator<Station> listStations (String name) throws LineNotExistsException {
        // Iterator<String> ??
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        return line.getStations();
    }

    @Override
    public Schedule bestTimetable(String name, String departureStation, String destinationStation, String arrivalTime)
            throws LineNotExistsException, ImpossibleRouteException, StationNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        Station departure = line.getStationByName(departureStation);
        Station destination = line.getStationByName(destinationStation);
        if (departure == null || destination == null) throw new StationNotExistsException();
        try {
            return line.bestRoute(departure, destination, Time.parse(arrivalTime));
        } catch (TimeFormatException e) {
            throw new ImpossibleRouteException();
        }
    }
    @Override
    public void insertSchedule(String name, int number, List<String> entriesRaw)
            throws InvalidScheduleException, LineNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        List<ScheduleClass.ScheduleEntry> entries = new MyArrayList<>();
        // parse first schedule entry, check if its terminal
        ScheduleClass.ScheduleEntry firstEntry = parseScheduleEntry(line, entriesRaw.getFirst());
        if (!line.isStationTerminal(firstEntry.getStation())) throw new InvalidScheduleException();
        Time prevTime = firstEntry.getTime();
        entries.addLast(firstEntry);
        for (int i = 1; i < entriesRaw.size() ; i++) {
            ScheduleClass.ScheduleEntry entry = parseScheduleEntry(line, entriesRaw.get(i));
            // if next station time is less than or equals prevTime - bad schedule
            if (entry.getTime().compareTo(prevTime) <= 0)
                throw new InvalidScheduleException();
            entries.addLast(entry);
        }
        line.addSchedule(new ScheduleClass(number, entries));
    }

    @Override
    public Iterator<Schedule> listSchedules(String name, String departureStation) throws LineNotExistsException,
            StationNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        Station station = line.getStationByName(departureStation);
        if (station == null || !line.isStationTerminal(station)) throw new StationNotExistsException();
        return line.getSchedulesByStation(station);
    }

    // main
    private ScheduleClass.ScheduleEntry parseScheduleEntry(Line line, String input) throws InvalidScheduleException {
        String[] stationAndTimeSplit = input.split(" ");
        if (stationAndTimeSplit.length < 2) throw new InvalidScheduleException();
        String stationName = stationAndTimeSplit[0], timeStr  = stationAndTimeSplit[1];
        Station station = line.getStationByName(stationName);
        if (station == null) throw new InvalidScheduleException();
        Time time;
        try {
            time = Time.parse(timeStr);
        } catch (TimeFormatException e) {
            throw new InvalidScheduleException();
        }
        return new ScheduleClass.ScheduleEntry(station, time);
    }
}
