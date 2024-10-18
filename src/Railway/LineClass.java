package Railway;

import Railway.dataStructures.Iterator;
import Railway.dataStructures.List;
import Railway.dataStructures.MyArrayList;

public class LineClass implements Line {
    List<Station> stationList;
    List<Schedule> schedules;
    String name;
    public LineClass (String name, List<Station> stations) {
        this.name = name;
        stationList = stations;
        schedules = new MyArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Iterator<Station> getStations() {
        return stationList.iterator();
    }
}
