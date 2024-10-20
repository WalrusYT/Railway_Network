package Railway;

import Railway.dataStructures.DoubleList;
import Railway.dataStructures.Iterator;
import Railway.dataStructures.List;
import Railway.dataStructures.MyArrayList;

public class LineClass implements Line {
    private final List<Station> stations;
    private final List<Schedule> schedules;
    String name;
    public LineClass (String name, List<Station> stations) {
        this.name = name;
        this.stations = stations;
        schedules = new DoubleList<>();
    }

    public String getName() {
        return name;
    }

    public Iterator<Station> getStations() {
        return stations.iterator();
    }

    @Override
    public Station getStationByName(String name) {
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            if (station.getName().equals(name))
                return station;
        }
        return null;
    }

    @Override
    public void addSchedule(Schedule schedule) {
        for (int i = 0; i < schedules.size(); i++) {
            if (schedule.getDepartureTime().compareTo(schedules.get(i).getDepartureTime()) <= 0) {
                schedules.add(i, schedule);
                return;
            }
        }
        schedules.addLast(schedule);
    }

    @Override
    public Iterator<Schedule> getSchedules() {
        return schedules.iterator();
    }

    @Override
    public boolean isStationTerminal(Station station) {
        return station.equals(stations.getFirst()) || station.equals(stations.getLast());
    }

    @Override
    public Iterator<Schedule> getSchedulesByStation(Station station) {
        List<Schedule> schedulesByStation = new MyArrayList<>();
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            if (schedule.getDepartureStation().equals(station)) {
                schedulesByStation.addLast(schedule);
            }
        }
        return schedulesByStation.iterator();
    }

}
