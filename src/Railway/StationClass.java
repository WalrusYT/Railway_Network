package Railway;

import dataStructures.*;

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

    private final Set<Line> lines;

    private final Dictionary<Time, Integer> passingTrains;

    /**
     * Constructs an object {@link StationClass} with the given station name
     * @param name name of the station
     */
    public StationClass (String name) {
        this.name = name;
        this.lines = new TreeSet<>();
        this.passingTrains = new BinarySearchTree<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addLine(Line line) {
        // O log n
        lines.add(line);
    }
    @Override
    public void removeLine(Line line) {
        // O log n
        lines.remove(line);
    }

    @Override
    public boolean hasLines() {
        return !lines.isEmpty();
    }

    @Override
    public Iterator<ProtectedLine> getProtectedLines() {
        return new Iterator<>() {
            private final Iterator<Line> iterator = lines.iterator();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ProtectedLine next() throws NoSuchElementException {
                return iterator.next();
            }

            @Override
            public void rewind() {
                iterator.rewind();
            }
        };
    }

    @Override
    public void addPassingTrain(Time time, int train) {
        this.passingTrains.insert(time, train);
    }

    @Override
    public Iterator<Entry<Time, Integer>> getPassingTrains() {
        return this.passingTrains.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationClass that = (StationClass) o;
        return name.equalsIgnoreCase(that.name);
    }
}
