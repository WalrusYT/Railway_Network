package Railway;

import Railway.exceptions.ImpossibleRouteException;
import Railway.exceptions.InvalidScheduleException;
import Railway.exceptions.StationNotExistsException;
import Railway.exceptions.TimeFormatException;
import dataStructures.Iterator;
import dataStructures.List;

import java.io.Serializable;

public class ScheduleClass implements Schedule {
    private static final long serialVersionUID = 0L;

    private final int trainNumber;
    private final List<ScheduleEntry> entries;

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
    public Time getArrivalForRoute(Station departure, Station destination) throws ImpossibleRouteException, StationNotExistsException {
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
        if (departureTime.compareTo(arrivalTime) > 0) throw new ImpossibleRouteException();
        return arrivalTime;
    }

    @Override
    public Station getDepartureStation() {
        return entries.getFirst().getStation();
    }

    public static class ScheduleEntry implements Serializable {
        private static final long serialVersionUID = 0L;

        private final Station station;
        private final Time time;

        public ScheduleEntry(Station station, Time time) {
            this.station = station;
            this.time = time;
        }

        public Station getStation() {
            return station;
        }

        public Time getTime() {
            return time;
        }
    }
}
