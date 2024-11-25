package pt.walrus.railway;

import pt.walrus.dataStructures.*;

import java.io.Serializable;

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
     * Lines of the station
     */
    private final Set<Line> lines;
    /**
     * Trains of the station
     */
    private final Dictionary<ArrivalEntry, Train> passingTrains;


    /**
     * Constructs an object {@link StationClass} with the given station name
     *
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
    public void addPassingTrain(Time time, Train train) {
        ArrivalEntry entry = new ArrivalEntry(time, train.getNumber());
        this.passingTrains.insert(entry, train);
    }

    @Override
    public void removePassingTrain(Train train, Time time) {
        this.passingTrains.remove(new ArrivalEntry(time, train.getNumber()));
    }

    @Override
    public Iterator<Entry<ArrivalEntry, Train>> getPassingTrains() {
        return this.passingTrains.iterator();
    }

    @Override
    public boolean isTrainArrive(Time time, int trainNumber) {
        Train train = passingTrains.find(new ArrivalEntry(time, trainNumber));
        if (train == null) return false;
        return train.getNumber() == trainNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station that = (Station) o;
        return name.equalsIgnoreCase(that.getName());
    }

    /**
     * The type Arrival entry.
     */
    public static class ArrivalEntry implements Comparable<ArrivalEntry>, Serializable {
        /**
         * The time of the arrival of the train.
         */
        Time time;
        /**
         * The number of the train
         */
        int trainNumber;

        /**
         * Constructs an instance of an Arrival Entry
         * @param time time of the arrival
         * @param trainNumber number of the arrival train
         */
        ArrivalEntry (Time time, int trainNumber) {
            this.time = time;
            this.trainNumber = trainNumber;
        }
        @Override
        public int compareTo(ArrivalEntry o) {
            int timeCompare = this.getTime().compareTo(o.getTime());
            if (timeCompare == 0)
                return trainNumber - o.getTrainNumber();
            return timeCompare;
        }

        /**
         * Returns the time of the arrival
         *
         * @return the time
         */
        public Time getTime() {
            return time;
        }

        /**
         * Returns the train number of the arrival train
         *
         * @return the number of the train
         */
        public int getTrainNumber() {
            return trainNumber;
        }
    }
}
