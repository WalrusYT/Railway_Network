package dataStructures;

/**
 * ConcatableQueue class that
 * @param <E>
 */
public interface ConcatenableQueue<E> extends Queue<E>{
    void append(ConcatenableQueue<E> queue);
}
