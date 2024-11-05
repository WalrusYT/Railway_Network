package Railway;

import java.io.Serializable;

public interface ProtectedStation extends Serializable {
    /**
     * Returns a name of the station
     * @return a {@link int} name of the station
     */
    String getName();
    boolean hasLines();
}
