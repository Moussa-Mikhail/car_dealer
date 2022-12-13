package cardealer.collections.chainlist;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Moussa
 */
public class ChainedIterator<T> implements Iterator<T> {
    private final @NotNull Iterator<Iterator<T>> nestedIterator;
    private Iterator<T> currentIterator;

    public ChainedIterator(@NotNull List<Iterable<T>> list) {
        List<Iterator<T>> iterators = list.stream().map(Iterable::iterator).collect(Collectors.toList());
        nestedIterator = iterators.iterator();
        currentIterator = nestedIterator.next();
    }

    @Override
    public boolean hasNext() {
        return currentIterator.hasNext() || nestedIterator.hasNext();
    }

    @Override
    public T next() {
        if (!currentIterator.hasNext()) {
            currentIterator = nestedIterator.next();
        }
        return currentIterator.next();
    }
}
