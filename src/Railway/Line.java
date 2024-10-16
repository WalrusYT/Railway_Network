package Railway;

import Railway.dataStructures.Iterator;

public interface Line {
    String getName();
    Iterator<Station> getStations();
}
