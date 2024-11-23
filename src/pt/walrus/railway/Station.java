package pt.walrus.railway;

import pt.walrus.dataStructures.Entry;
import pt.walrus.dataStructures.Iterator;

import java.io.Serializable;

/**
 * This interface represents a station in the pt.walrus.Railway Network.
 */
public interface Station extends ProtectedStation {
    /**
     * Returns a name of the station
     *
     * @param line the line
     * @return a {@link int} name of the station
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
    void removePassingTrain(Time time);

    /**
     * Gets passing trains.
     *
     * @return the passing trains
     */
    Iterator<Entry<Time, Train>> getPassingTrains();

    /**
     * Is train arrive boolean.
     *
     * @param time      the time
     * @param direction the direction
     * @return the boolean
     */
    boolean isTrainArrive(Time time, Direction direction);
}
