package Railway;

import Railway.exceptions.TimeFormatException;

import java.io.Serializable;

public class Time implements Comparable<Time>, Serializable {
    private static final long serialVersionUID = 0L;

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
}
