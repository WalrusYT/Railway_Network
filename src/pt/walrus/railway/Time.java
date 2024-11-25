package pt.walrus.railway;

import java.io.Serializable;

/**
 * Represents a specific time in hours and minutes in a 24-hour format
 * Provides functionality to compare and find the difference between two {@code Time} instances
 * Implements {@link Comparable} to allow time comparisons based on hours and minutes
 */
public class Time implements Comparable<Time>, Serializable {
     /**
     * Serializable class a version number
     */
    private static final long serialVersionUID = 0L;
    /**
     * Hours and minutes of the Time
     */
    private final int h;
    private final int m;
     /**
     * Constructs an object {@link Time} with the given hours and minutes
     * @param h hours
     * @param m minutes
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time that = (Time) o;
        return h == that.h && m == that.m;
    }
    /**
     * Returns hours of the Time
     * @return hour
     */
    public int getHours() {
        return this.h;
    }
    /**
     * Returns minutes of the Time
     * @return minutes
     */
    public int getMinutes() {
        return this.m;
    }
    /**
     * Calculates absolute difference between this {@code Time} and another {@code Time} in minutes
     * @param other {@code Time} instance to calculate the difference from
     * @return Difference in time in minutes
     */
    public int differenceMin(Time other) {
        int thisMin = this.h * 60 + this.m;
        int otherMin = other.h * 60 + other.m;
        return Math.abs(thisMin - otherMin);
    }
}
