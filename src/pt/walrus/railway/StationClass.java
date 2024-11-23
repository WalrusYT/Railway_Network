package pt.walrus.railway;

import pt.walrus.dataStructures.*;
import pt.walrus.railway.ScheduleClass.ScheduleEntry;

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

    private final Set<Line> lines;

    private final Dictionary<Time, Train> passingTrains;

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
        Iterator<Entry<ScheduleEntry, Schedule>> it = line.getSchedules();
        while (it.hasNext()) {
            Schedule s = it.next().getValue();
            Iterator<ScheduleEntry> it2 = s.getEntries();
            while (it2.hasNext()) {
                ScheduleEntry se = it2.next();
                if (se.getStation().equals(this)) {
                    passingTrains.remove(se.getTime());
                }
            }
        }
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
        this.passingTrains.insert(time, train);
    }

    @Override
    public void removePassingTrain(Time time) {
        this.passingTrains.remove(time);
    }

    @Override
    public Iterator<Entry<Time, Train>> getPassingTrains() {
        return this.passingTrains.iterator();
    }

    @Override
    public boolean isTrainArrive(Time time, Direction direction) {
        Train train = passingTrains.find(time);
        if (train == null) return false;
        return train.getDirection() == direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationClass that = (StationClass) o;
        return name.equalsIgnoreCase(that.name);
    }

    public static class ArrivalEntry implements Comparable<ArrivalEntry>, Serializable {
        Time time;
        Direction direction;
        ArrivalEntry (Time time, Direction direction) {
            this.time = time;
            this.direction = direction;
        }
        @Override
        public int compareTo(ArrivalEntry o) {
            return this.getTime().compareTo(o.getTime());
        }

        public Time getTime() {
            return time;
        }
        public Direction getDirection() {
            return direction;
        }
    }
}
