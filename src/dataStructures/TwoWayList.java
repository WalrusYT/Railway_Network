package dataStructures;

public interface TwoWayList<E> extends List<E>{
    TwoWayIterator<E> twoWayIterator();
}
