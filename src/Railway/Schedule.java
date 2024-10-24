package Railway;

import Railway.exceptions.ImpossibleRouteException;
import Railway.exceptions.StationNotExistsException;
import dataStructures.Iterator;

public interface Schedule {
    int getTrainNumber();
    Iterator<ScheduleClass.ScheduleEntry> getEntries();

    Time getDepartureTime();

    Station getDepartureStation();
}
