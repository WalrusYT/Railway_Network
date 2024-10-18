package Railway;

import Railway.exceptions.TimeFormatException;

import java.util.Comparator;

public class Time implements Comparable<Time> {
    private int h, m;

    public Time(int h, int m) {
        this.h = h;
        this.m = m;
    }

    @Override
    public int compareTo(Time o) {
        return Comparator.comparingInt(Time::getHours)
                .thenComparingInt(Time::getMinutes)
                .compare(this, o);
    }

    public int getHours() {
        return this.h;
    }

    public int getMinutes() {
        return this.m;
    }

    public static Time parse(String input) throws TimeFormatException {
        String[] hoursAndMinutes = input.split(":");
        if (hoursAndMinutes.length != 2) throw new TimeFormatException();
        int h, m;
        try {
            h = Integer.parseInt(hoursAndMinutes[0]);
            m = Integer.parseInt(hoursAndMinutes[1]);
        } catch (NumberFormatException e) {
            throw new TimeFormatException();
        }
        return new Time(h, m);
    }
}
