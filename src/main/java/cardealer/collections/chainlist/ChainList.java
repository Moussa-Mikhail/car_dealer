package cardealer.collections.chainlist;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Moussa
 * List of Iterables that allows iterators to be chained.
 */
public class ChainList<T> implements List<Iterable<T>> {
    private final List<Iterable<T>> list = new ArrayList<>();

    @Override
    public boolean addAll(@NotNull Collection<? extends Iterable<T>> c) {
        return list.addAll(c);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public @NotNull Iterator<Iterable<T>> iterator() {
        return list.iterator();
    }

    @Override
    public Object @NotNull [] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1 @NotNull [] toArray(T1 @NotNull [] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(Iterable<T> objects) {
        return list.add(objects);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return new HashSet<>(list).containsAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends Iterable<T>> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterable<T> get(int index) {
        return list.get(index);
    }

    @Override
    public Iterable<T> set(int index, Iterable<T> element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, Iterable<T> element) {
        list.add(index, element);
    }

    @Override
    public Iterable<T> remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public @NotNull ListIterator<Iterable<T>> listIterator() {
        return list.listIterator();
    }

    @Override
    public @NotNull ListIterator<Iterable<T>> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public @NotNull List<Iterable<T>> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    public @NotNull ChainedIterator<T> chainedIterator() {
        return new ChainedIterator<>(this.list);
    }
}
