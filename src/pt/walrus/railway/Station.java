package pt.walrus.railway;

import pt.walrus.dataStructures.Entry;
import pt.walrus.dataStructures.Iterator;

import java.io.Serializable;
/**
 * This interface represents a station in the pt.walrus.Railway Network.
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

    void addPassingTrain(Time time, Train train);

    void removePassingTrain(Time time);

    Iterator<Entry<Time, Train>> getPassingTrains();

    boolean isTrainArrive(Time time, Direction direction);
}