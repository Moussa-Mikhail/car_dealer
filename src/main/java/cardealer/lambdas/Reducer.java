package cardealer.lambdas;

/**
 * @author Moussa
 */
@FunctionalInterface
public interface Reducer<T> {
    /**
     * Reduces two items into one.
     *
     * @param t1 the first item
     * @param t2 the second item
     * @return the result of the reduction.
     */
    T reduce(T t1, T t2);
}
