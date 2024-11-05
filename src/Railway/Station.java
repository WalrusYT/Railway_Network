package Railway;

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
    void removeLine(Line line);
}
