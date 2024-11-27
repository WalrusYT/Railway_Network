package pt.walrus.railway;

/**
 * This interface represents a station in the Railway Network.
 */
public interface Station extends ProtectedStation {
    /**
     * Adds a line to the station
     *
     * @param line the line that should be added
     */
    void addLine(Line line);

    /**
     * Removes a line from the station
     *
     * @param line line that should be removed
     */
    void removeLine(Line line);

    /**
     * Adds a train that passes the station with the given time
     *
     * @param time  the time of passing the station
     * @param train the train that passes the station
     */
    void addPassingTrain(Time time, Train train);

    /**
     * Removes a train that passes the station with the given time
     *
     * @param time the time of passing the station
     */
    void removePassingTrain(Train train, Time time);
}
