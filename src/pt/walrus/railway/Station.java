package pt.walrus.railway;

import pt.walrus.dataStructures.Entry;
import pt.walrus.dataStructures.Iterator;

/**
 * This interface represents a station in the pt.walrus.Railway Network.
 */
public interface Station extends ProtectedStation {
    /**
     * Returns a name of the station
     *
     * @param line the line
     */
    void addLine(Line line);

    /**
     * Removes a line from the station
     *
     * @param line line that should be removed
     */
    void removeLine(Line line);

    /**
     * Add passing train.
     *
     * @param time  the time
     * @param train the train
     */
    void addPassingTrain(Time time, Train train);

    /**
     * Remove passing train.
     *
     * @param time the time
     */
    void removePassingTrain(Train train, Time time);
}
