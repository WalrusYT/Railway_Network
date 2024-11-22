package pt.walrus.dataStructures;

import java.io.Serializable;

/**
 * Comparator for comparing objects by the different criteria
 * @param <T> type of the object to compare
 */
public interface Comparator<T> extends Serializable {
    /**
     * Compares two objects of type T. Returns 1 if o1 > o2, -1 otherwise,
     * and 0 if they are equal.
     *
     * @param o1 The first object to compare.
     * @param o2 The second object to compare.
     * @return 1 if o1 > o2, -1 if o1 < o2, and 0 if they are equal.
     */
    int compare(T o1, T o2);
}
