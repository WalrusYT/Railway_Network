package Railway;

import dataStructures.DoubleList;
import dataStructures.List;

/**
 * The Station Class represents a station with a station name and list of lines
 */
public class StationClass implements Station {
     /**
     * Serializable class a version number
     */
    private static final long serialVersionUID = 0L;
    /**
     * Name of the station
     */
    private final String name;

    /**
     * Constructs an object {@link StationClass} with the given station name
     * @param name name of the station
     */
    public StationClass (String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationClass that = (StationClass) o;
        return name.equalsIgnoreCase(that.name);
    }
}
