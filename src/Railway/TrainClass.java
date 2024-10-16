package Railway;

public class TrainClass implements Train{
    private int number;
    private Schedule schedule;

    public TrainClass (int number, Schedule schedule) {
        this.number = number;
        this.schedule = schedule;
    }
    
    @Override
    public int getNumder() {
        return number;
    }
    
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    // Delegating validation to the Schedule class
    public boolean isScheduleValid() {
        return schedule.isScheduleValid();
    }
}