package cardealer.collections.sortedarray;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Moussa
 * Array whose elements are kept sorted.
 */
public class SortedArray<T> implements Collection<T> {
    private final List<T> list = new ArrayList<>();
    private final Comparator<T> comparator;

    public SortedArray(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
        return true;
    }

    @Override
    public boolean add(T t) {
        int index = find(t);
        if (index == -1) {
            insert(t);
        } else {
            list.add(index, t);
        }
        return true;
    }

    private void insert(T t) {
        if (list.isEmpty()) {
            list.add(t);
            return;
        }

        if (comparator.compare(t, list.get(0)) < 0) {
            list.add(0, t);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (comparator.compare(t, list.get(i)) < 0) {
                list.add(i, t);
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public int find(Object o) {
        int left = 0;
        int right = size() - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            int compare = comparator.compare((T) o, list.get(middle));
            if (compare == 0) {
                return middle;
            } else if (compare < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
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
    public @NotNull Iterator<T> iterator() {
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
    public boolean containsAll(@NotNull Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return find(o) != -1;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            changed = changed || remove(o);
        }
        return changed;
    }

    @Override
    public boolean remove(Object o) {
        list.remove(o);
        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        boolean changed = false;
        for (T elem : list) {
            if (!c.contains(elem)) {
                changed = changed || remove(elem);
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        list.clear();
    }
}
