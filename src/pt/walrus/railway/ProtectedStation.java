package pt.walrus.railway;

import java.io.Serializable;

import pt.walrus.dataStructures.Entry;
import pt.walrus.dataStructures.Iterator;

/**
 * The interface Protected station.
 */
public interface ProtectedStation extends Serializable {
    /**
     * Returns a name of the station
     *
     * @return a {@link int} name of the station
     */
    String getName();

    /**
     * Checks if the station has lines containing it
     *
     * @return <code>true</code> if the station has lines and <code>false</code> otherwise.
     */
    boolean hasLines();

    /**
     * Returns the iterator of {@link ProtectedLine} lines of the station
     * The object is protected from changing from the Main class
     *
     * @return the protected lines collection iterator
     */
    Iterator<ProtectedLine> getProtectedLines();

    /**
     * Returns an iterator of passing the station trains
     *
     * @return the passing the station trains
     */
    Iterator<Entry<StationClass.ArrivalEntry, Train>> getPassingTrains();

    /**
     * Checks if the train with the given number and time arrives
     *
     * @param time      the time
     * @param trainNumber the number of the train
     * @return the boolean
     */
    boolean isTrainArrive(Time time, int trainNumber);
}
