package Railway;

import Railway.dataStructures.MyArrayList;
import Railway.dataStructures.Iterator;
import Railway.dataStructures.List;
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
            Station station = createStation(stations.get(i));
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

    public void removeLine(String name) throws LineNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        lines.remove(line);
        // удалить линию из всех станций......
    }

    public Iterator<Station> listStations (String name) throws LineNotExistsException {
        // Iterator<String> ??
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        return line.getStations();
    }

    @Override
    public void insertSchedule(String name, int number, List<String> entries)
            throws InvalidScheduleException, LineNotExistsException {
        Line line = getLine(name);
        if (line == null) throw new LineNotExistsException();
        List<ScheduleClass.StationTime> entriesLst = new MyArrayList<>();
        Time prevTime = null;
        for (int i = 0; i < entries.size() ; i++) {
            String[] stationAndTimeSplit = entries.get(i).split(" ");
            String stationName = stationAndTimeSplit[0], timeStr  = stationAndTimeSplit[1];
            if (i == 0 && (!stationName.equals(lines.getFirst().getName()) || !stationName.equals(lines.getLast().getName()))) {
                throw new InvalidScheduleException();
            }
            Time time = Time.parse(timeStr);
            if (time.compareTo(prevTime) <= 0) throw new InvalidScheduleException();
            prevTime = time;
            entriesLst.addLast(new ScheduleClass.StationTime());
        }
        Schedule schedule = new ScheduleClass(number, entries);
    }

    private Station createStation(String station) {
        return new StationClass(station);
    }
}
