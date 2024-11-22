package pt.walrus.railway;

import pt.walrus.dataStructures.Iterator;
import pt.walrus.dataStructures.List;
import java.io.Serializable;

/**
 * The Schedule class represents a schedule with a train number, list of entries and schedules
 */
public class ScheduleClass implements Schedule {
     /**
     * Serializable class a version number
     */
    private static final long serialVersionUID = 0L;
    /**
     * Number of the train
     */
    private final int trainNumber;
     /**
     * List of the {@link ScheduleEntry} enties
     */
    private final List<ScheduleEntry> entries;

    private final Direction direction;

    /**
     * Constructs an object {@link ScheduleClass} with the given train number and list of entries
     * @param trainNumber number of the train
     * @param entries list of entries of the schedule
     */
    public ScheduleClass (int trainNumber, List<ScheduleEntry> entries, Direction direction) {
        this.trainNumber = trainNumber;
        this.entries = entries;
        this.direction = direction;
    }


    @Override
    public Iterator<ScheduleEntry> getEntries() {
        return entries.iterator();
    }

    @Override
    public int getTrainNumber() {
        return trainNumber;
    }

    @Override
    public ScheduleEntry getDepartureEntry() {
        return entries.getFirst();
    }

    @Override
    public Time getArrivalForRoute(Station departure, Station destination) {
        Iterator<ScheduleEntry> iterator = entries.iterator();
        Time departureTime = null, arrivalTime = null;
        while (iterator.hasNext()) {
            ScheduleEntry entry = iterator.next();
            if (entry.getStation().equals(departure))
                departureTime = entry.getTime();
            if (entry.getStation().equals(destination))
                arrivalTime = entry.getTime();
        }
        if (departureTime == null || arrivalTime == null) return null;
        if (departureTime.compareTo(arrivalTime) >= 0) return null;
        return arrivalTime;
    }

    @Override
    public boolean isOverlapping(Schedule other) {
        if (this.direction != other.getDirection()) return false;
        Iterator<ScheduleEntry> otherEntries = other.getEntries();
        ScheduleEntry thisEntry = this.entries.getFirst(), otherEntry = otherEntries.next();
        int initialTimeState = thisEntry.time.compareTo(otherEntry.getTime());
        if (initialTimeState == 0) return true;
        // It's not O(n^2) !!!
        // keeping schedules' stations in sync with the second while loop
        int lastStationIndex = 1;
        otherEntriesLoop: while (otherEntries.hasNext()) {
            otherEntry = otherEntries.next();
            for (int i = lastStationIndex; i < this.entries.size(); i++) {
                thisEntry = this.entries.get(i);
                if (otherEntry.station.equals(thisEntry.getStation())) {
                    lastStationIndex = i + 1;
                    int currentTimeState = thisEntry.time.compareTo(otherEntry.getTime());
                    if (currentTimeState == 0 || currentTimeState != initialTimeState) return true;
                    continue otherEntriesLoop;
                }
            }
        }
        return false;
    }
    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleClass that = (ScheduleClass) o;
        Iterator<ScheduleEntry> it = that.getEntries();
        int i = 0;
        while (it.hasNext() && i < entries.size()) {
            if (it.next().equals(entries.get(i)))
                return true;
        }
        return false;
    }

    @Override
    public Station getDepartureStation() {
        return entries.getFirst().getStation();
    }

    /**
     * Represents an individual entry within a train schedule, which includes a specific station 
     * and the scheduled time for that station. 
     */
    public static class ScheduleEntry implements Comparable<ScheduleEntry>, Serializable {
        /**
        * Serializable class a version number
        */
        private static final long serialVersionUID = 0L;
        /**
         * Name of the station
         */
        private final Station station;
        /**
         * Departure time
         */
        private final Time time;
        /**
        * Constacts an object {@link ScheduleEntry} with the given station name and time
        * @param station name of the station
        * @param time time of departure
        */
        public ScheduleEntry(Station station, Time time) {
            this.station = station;
            this.time = time;
        }
        /**
         * Returns the station name
         * @return a {@link Station} station name
         */
        public Station getStation() {
            return station;
        }
        /**
         * Returns time of departure
         * @return a {@link Time} time
         */
        public Time getTime() {
            return time;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ScheduleEntry that = (ScheduleEntry) o;
            return time.equals(that.time) && station.equals(that.station);
        }

        @Override
        public int compareTo(ScheduleEntry o) {
            int timeCompare = this.getTime().compareTo(o.getTime());
            if (timeCompare == 0)
                return this.getStation().getName().compareTo(o.getStation().getName());
            return timeCompare;
        }
    }
}
