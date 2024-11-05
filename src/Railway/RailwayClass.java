package Railway;

import Railway.exceptions.*;
import dataStructures.*;

public class RailwayClass implements Railway {
    private static final long serialVersionUID = 0L;

    List<Line> lines;
    List<Station> stations;
    public RailwayClass() {
        lines = new MyArrayList<>();
        stations = new MyArrayList<>();
    }

    @Override
    public void insertLine(String name, List<String> stations) throws LineAlreadyExistsException {
        if (lineExists(name)) throw new LineAlreadyExistsException();
        List<Station> stationList = new MyArrayList<>();
        for (int i = 0; i < stations.size(); i++) {
            Station station = getStation(stations.get(i)); // получаем станцию из коллекции системы
            if (station == null) { // если такой станции еще нет
                station = new StationClass(stations.get(i)); // то создаем новую
                this.stations.addLast(station); // и кладем её в коллекцию системы
            }
            stationList.addLast(station);
        }
        Line line = new LineClass(name, stationList);
        lines.addLast(line);
        // теперь нужно вставить созданную линюю во все станции
        updateStationsOfALine(line);
    }

    // добавляет линюю в каждую станцию линии
    private void updateStationsOfALine(Line line) {
        Iterator<Station> stations = line.getStations();
        while (stations.hasNext()) {
            Station station = stations.next();
            station.addLine(line);
        }
    }

    private Station getStation (String name) {
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            if (station.getName().equalsIgnoreCase(name)) return station;
        }
        return null;
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
            if (line.getName().equalsIgnoreCase(name)) {
                return line;
            }
        }
        throw new LineNotExistsException();
    }

    @Override
    public void removeLine(String name) throws LineNotExistsException {
        Line line = getLine(name);
        // удаляем станции линии из системы
        Iterator<Station> stations = line.getStations(); // пробегаем по станциям линии
        while (stations.hasNext()) {
            Station station = stations.next();
            station.removeLine(line); // удаляем линюю из станции
            if (!station.hasLines()) this.stations.remove(station); // если у станций не осталось линий
            // то удаляем станцию из системы
        }
        boolean removed = lines.remove(line);
        // delete the schedules of this line?
    }

    @Override
    public Iterator<Station> listStations (String name) throws LineNotExistsException {
        Line line = getLine(name);
        return line.getStations();
    }

    @Override
    public Schedule bestTimetable(String name, String departureStation,
                                  String destinationStation, Time arrivalTime)
            throws LineNotExistsException, ImpossibleRouteException, StationNotExistsException {
        Line line = getLine(name);
        Station departure = line.getStationByName(departureStation);
        if (departure == null) throw new StationNotExistsException();
        Station destination = line.getStationByName(destinationStation);
        if (destination == null) throw new ImpossibleRouteException();
        Schedule best = line.bestRoute(departure, destination, arrivalTime);
        if (best == null) throw new ImpossibleRouteException();
        return best;
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
        int stationIndex = line.getStationIndex(firstEntry.getStation());
        boolean directionLeft = stationIndex == 0;
        for (int i = 1; i < entriesRaw.size() ; i++) {
            ScheduleClass.ScheduleEntry entry = createScheduleEntry(line, entriesRaw.get(i));
            if (entry.getTime().compareTo(prevTime) <= 0) throw new InvalidScheduleException();
            int nextStationIndex = line.getStationIndex(entry.getStation());
            if (directionLeft && nextStationIndex <= stationIndex)
                throw new InvalidScheduleException();
            if (!directionLeft && nextStationIndex >= stationIndex)
                throw new InvalidScheduleException();
            entries.addLast(entry);
            prevTime = entry.getTime();
            stationIndex = nextStationIndex;
        }
        line.addSchedule(new ScheduleClass(number, entries));
    }
    
    @Override
    public void removeSchedule(String name, String station, Time time)
            throws ScheduleNotExistsException, LineNotExistsException {
        Line line = getLine(name);
        ScheduleClass.ScheduleEntry entry =
                createScheduleEntry(line, new EntryClass<>(station, time));
        line.removeSchedule(entry);
    }

    @Override
    public Iterator<Schedule> listSchedules(String name, String departureStation)
            throws LineNotExistsException, StationNotExistsException {
        Line line = getLine(name);
        Station station = line.getStationByName(departureStation);
        if (station == null || !line.isStationTerminal(station))
            throw new StationNotExistsException();
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
