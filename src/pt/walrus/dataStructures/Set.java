package pt.walrus.dataStructures;

import java.io.Serializable;

public interface Set<T> extends Serializable {
    boolean add(T element);
    boolean remove(T element);
    boolean contains(T element);
    int size();
    boolean isEmpty();
    Iterator<T> iterator();
}
