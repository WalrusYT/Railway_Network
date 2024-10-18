package Railway;

import Railway.dataStructures.Iterator;
import Railway.dataStructures.List;

public class ScheduleClass implements Schedule {
    private final int trainNumber;
    private final List<ScheduleEntry> entries;

    public ScheduleClass (int trainNumber, List<ScheduleEntry> entries) {
        this.trainNumber = trainNumber;
        this.entries = entries;
    }

    public Iterator<ScheduleEntry> getEntries() {
        return entries.iterator();
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public static class ScheduleEntry {
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
