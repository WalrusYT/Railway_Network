package Railway;

import Railway.dataStructures.DoubleList;
import Railway.dataStructures.Iterator;
import Railway.dataStructures.List;

public class LineClass implements Line {
    List<Station> stationList;
    String name;
    public LineClass (String name, List<Station> stations) {
        this.name = name;
        stationList = stations;
    }

    public String getName() {
        return name;
    }

    public Iterator<Station> getStations() {
        return stationList.iterator();
    }
}
