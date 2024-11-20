package Railway;

import java.io.Serializable;
import dataStructures.Iterator;

public interface ProtectedStation extends Serializable {
    /**
     * Returns a name of the station
     * @return a {@link int} name of the station
     */
    String getName();

    /**
     * Checks if the station has lines containing it
     * @return <code>true</code> if the station has lines and <code>false</code> otherwise.
     */
    boolean hasLines();

    Iterator<ProtectedLine> getProtectedLines();
}
