package pt.walrus.dataStructures;

public interface TwoWayList<E> extends List<E> {
    TwoWayIterator<E> twoWayIterator();
}
