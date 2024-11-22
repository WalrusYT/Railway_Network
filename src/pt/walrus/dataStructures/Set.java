package pt.walrus.dataStructures;

public interface Set<T> {
    boolean add(T element);
    boolean remove(T element);
    boolean contains(T element);
    int size();
    boolean isEmpty();
    Iterator<T> iterator();
}
