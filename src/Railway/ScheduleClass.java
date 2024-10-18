package Railway;

import Railway.dataStructures.List;
import Railway.dataStructures.MyArrayList;

public class ScheduleClass {
    private int trainNumber;
    private List<StationTime> entries;

    public ScheduleClass (int trainNumber) {
        this.trainNumber = trainNumber;
        entries = new MyArrayList<>();
    }

    public class StationTime {
        Station station;
        Time time;

        public StationTime (Station station, Time time) {
            this.station = station;
            this.time = time;
        }
    }
}
