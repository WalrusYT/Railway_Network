package Railway;

import dataStructures.Entry;
import dataStructures.Iterator;

import java.io.Serializable;
/**
 * This interface represents a station in the Railway Network.
 */
public interface Station extends Serializable, ProtectedStation {
    /**
     * Returns a name of the station
     * @return a {@link int} name of the station
     */
    void addLine(Line line);

    /**
     * Removes a line from the station
     * @param line line that should be removed
     */
    void removeLine(Line line);

    void addPassingTrain(Time time, int train);

    Iterator<Entry<Time, Integer>> getPassingTrains();
}
