package Railway;

import dataStructures.Comparator;

/**
 * Comparator for comparing two {@link ScheduleClass.ScheduleEntry} objects based on their time
 * If two entries have the same time, it compares the entries based on the station name
 */
public class ScheduleEntryComparatorByTime implements Comparator<ScheduleClass.ScheduleEntry> {
    @Override
    public int compare(ScheduleClass.ScheduleEntry o1, ScheduleClass.ScheduleEntry o2) {
        int timeCompare = o1.getTime().compareTo(o2.getTime());
        if (timeCompare == 0 && o1.getStation().getName().compareTo(o2.getStation().getName()) == 0)
            return 0;
        return timeCompare;
    }
}
