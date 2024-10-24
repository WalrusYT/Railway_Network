package Railway;

import Railway.exceptions.TimeFormatException;

public class Time implements Comparable<Time> {
    private int h, m;

    public Time(int h, int m) {
        this.h = h;
        this.m = m;
    }

    @Override
    public int compareTo(Time o) {
        if (h > o.getHours()) {
            return 1;
        } else if (h < o.getHours()) {
            return -1;
        } else return Integer.compare(m, o.getMinutes());
    }

    public int getHours() {
        return this.h;
    }

    public int getMinutes() {
        return this.m;
    }

    public Time difference(Time other) {
        return new Time (Math.abs(h - other.getHours()), Math.abs(m - other.getMinutes()));
    }

    // in main
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

    @Override
    public String toString() {
        return String.format("%02d:%02d", this.h, this.m);
    }
    // in main
}
