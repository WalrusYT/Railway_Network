package Railway;

import Railway.exceptions.ImpossibleRouteException;
import Railway.exceptions.StationNotExistsException;
import dataStructures.Iterator;

import java.io.Serializable;

public interface Schedule extends Serializable {
    int getTrainNumber();
    Iterator<ScheduleClass.ScheduleEntry> getEntries();

    ScheduleClass.ScheduleEntry getDepartureEntry();

    Station getDepartureStation();

    Time getArrivalForRoute(Station departure, Station destination) throws ImpossibleRouteException, StationNotExistsException;
}
