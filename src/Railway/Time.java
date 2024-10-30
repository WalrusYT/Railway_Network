package Railway;

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
     * Hours and minutes
     */
    private int h, m;
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
    /**
     * Returns hours
     * @return hour
     */
    public int getHours() {
        return this.h;
    }
    /**
     * Returns minutes
     * @return minutes
     */
    public int getMinutes() {
        return this.m;
    }
    /**
     * Calculates absolute difference between this {@code Time} and another {@code Time}
     * @param other {@code Time} instance to calculate the difference from
     * @return a new {@code Time} object representing the absolute difference in hours and minutes
     */
    public Time difference(Time other) {
        return new Time (Math.abs(h - other.getHours()), Math.abs(m - other.getMinutes()));
    }
}
