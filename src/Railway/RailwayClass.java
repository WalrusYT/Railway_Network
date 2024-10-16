package Railway;

import Railway.dataStructures.DoubleList;
import Railway.dataStructures.Iterator;
import Railway.dataStructures.List;
import Railway.exceptions.*;

public class RailwayClass implements Railway{
    List<Line> lines = new DoubleList<>();
    @Override
    public void insertLine(String name, List<String> stations) throws LineAlreadyExistsException {
        if (lineExists(name)) throw new LineAlreadyExistsException();
        List<Station> stationList = new DoubleList<>();
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
    private Station createStation(String station) {
        return new StationClass(station);
    }
}
