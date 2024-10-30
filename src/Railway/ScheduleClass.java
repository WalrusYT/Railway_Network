package Railway;

import Railway.exceptions.ImpossibleRouteException;
import Railway.exceptions.StationNotExistsException;
import dataStructures.Iterator;
import dataStructures.List;
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
    

    /**
     * Constructs an object {@link ScheduleClass} with the given train number and list of entries
     * @param trainNumber number of the train
     * @param entries list of entries of the schedule
     */
    public ScheduleClass (int trainNumber, List<ScheduleEntry> entries) {
        this.trainNumber = trainNumber;
        this.entries = entries;
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
    public Time getArrivalForRoute(Station departure, Station destination) 
    throws ImpossibleRouteException, StationNotExistsException {
        Iterator<ScheduleEntry> iterator = entries.iterator();
        Time departureTime = null, arrivalTime = null;
        while (iterator.hasNext()) {
            ScheduleEntry entry = iterator.next();
            if (entry.getStation().equals(departure))
                departureTime = entry.getTime();
            if (entry.getStation().equals(destination))
                arrivalTime = entry.getTime();
        }
        if (departureTime == null || arrivalTime == null) throw new StationNotExistsException();
        // if (departureTime == null || arrivalTime == null) return null;
        if (departureTime.compareTo(arrivalTime) > 0) throw new ImpossibleRouteException();
        return arrivalTime;
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
    public static class ScheduleEntry implements Serializable {
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
    }
}
