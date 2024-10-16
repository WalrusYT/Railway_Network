package Railway;

import Railway.dataStructures.Iterator;
import Railway.dataStructures.List;
import Railway.exceptions.*;

public interface Railway {
    void insertLine(String name, List<String> stations) throws LineAlreadyExistsException;
    void removeLine(String name) throws LineNotExistsException;
    Iterator<Station> listStations (String name) throws LineNotExistsException;
}
