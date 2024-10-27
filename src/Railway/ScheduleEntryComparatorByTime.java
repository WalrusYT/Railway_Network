package Railway;

import dataStructures.Comparator;

public class ScheduleEntryComparatorByTime implements Comparator<ScheduleClass.ScheduleEntry> {
    @Override
    public int compare(ScheduleClass.ScheduleEntry o1, ScheduleClass.ScheduleEntry o2) {
        int timeCompare = o1.getTime().compareTo(o2.getTime());
        if (timeCompare == 0 && o1.getStation().getName().compareTo(o2.getStation().getName()) == 0) {
            return 0;
        }
        return timeCompare;
    }
}
