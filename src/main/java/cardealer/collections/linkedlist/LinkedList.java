package cardealer.collections.linkedlist;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author Moussa
 */
@SuppressWarnings("PMD.GodClass")
public class LinkedList<T> implements List<T> {
    private @Nullable Node<T> first = null;
    private @Nullable Node<T> last = null;
    private int size = 0;

    public LinkedList() {
        // empty constructor
    }

    private LinkedList(@Nullable Node<T> first, @Nullable Node<T> last, int size) {
        this.first = first;
        this.last = last;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }

    @Override
    public Object @NotNull [] toArray() {
        return this.toArray(new Object[0]);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1 @NotNull [] toArray(T1 @NotNull [] a) {

        T1[] arr = a;

        if (a.length < size) {
            arr = (T1[]) new Object[size];
        }

        for (int i = 0; i < size; i++) {
            arr[i] = (T1) get(i);
        }

        if (a.length > size) {
            arr[size] = null;
        }
        return arr;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    /*default*/ Node<T> getNode(int index) {
        checkIndex(index);

        if (first == null) {
            throw new NoSuchElementException("List is empty.");
        }

        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            assert node != null;
            node = node.next;
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
    }

    @SuppressWarnings("PMD.CompareObjectsWithEquals")
        /*default*/ void unlink(@NotNull Node<T> node) {
        if (node == first) {
            first = null;
        } else if (node == last) {
            last = node.prev;
        } else {
            assert node.next != null;
            node.next.prev = node.prev;
        }
        size--;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        for (Object elem : c) {
            if (!contains(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (T elem : this) {
            if (elem.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        for (T elem : c) {
            add(elem);
        }
        return true;
    }

    @SuppressWarnings("java:S3516")
    @Override
    public boolean add(T t) {
        Node<T> node = new Node<>(t);

        if (first == null) {
            first = node;
            last = node;
            size++;
            return true;
        }

        assert last != null;
        last.next = node;
        node.prev = last;
        last = node;
        size++;
        return true;
    }

    @Override
    public boolean addAll(int start, @NotNull Collection<? extends T> c) {
        int index = start;
        for (T elem : c) {
            add(index, elem);
            index++;
        }
        return true;
    }

    @Override
    public void add(int index, T element) {
        Node<T> node = new Node<>(element);

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        if (first == null) {
            first = node;
            last = node;
            size++;
            return;
        }

        if (index == 0) {
            node.next = first;
            first = node;
            size++;
            return;
        }

        Node<T> prev = getNode(index - 1);

        Node<T> next = prev.next;

        prev.next = node;
        node.next = next;
        size++;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        boolean changed = false;
        for (Object elem : c) {
            changed = changed || remove(elem);
        }
        return changed;
    }

    @Override
    public boolean remove(Object o) {
        if (first == null) {
            return false;
        }

        if (first.value.equals(o)) {
            first = first.next;
            size--;
            return true;
        }

        Node<T> prev;
        Node<T> current = first;
        while (current.next != null) {
            prev = current;
            current = current.next;
            if (current.value.equals(o)) {
                prev.next = current.next;
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        boolean changed = false;
        Node<T> current = first;
        while (current != null) {
            T value = current.value;
            current = current.next;
            if (!c.contains(value)) {
                changed = changed || remove(value);
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public T set(int index, T element) {
        Node<T> node = getNode(index);
        T oldValue = node.value;
        node.value = element;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Node<T> prev = getNode(index - 1);
        Node<T> node = prev.next;
        assert node != null;
        prev.next = node.next;
        size--;
        return node.value;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (get(i).equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<T> current = first;
        int index = 0;
        while (current != null) {
            if (current.value.equals(o)) {
                Node<T> next = current.next;
                if (next == null || !next.value.equals(o)) {
                    return index;
                }
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public @NotNull ListIterator<T> listIterator() {
        return new LinkedListIterator<>(this);
    }

    @Override
    public @NotNull ListIterator<T> listIterator(int index) {
        return new LinkedListIterator<>(this, index);
    }

    @Override
    public @NotNull List<T> subList(int fromIndex, int toIndex) {
        Node<T> subFirst = getNode(fromIndex);
        Node<T> subLast = getNode(toIndex);
        int subSize = toIndex - fromIndex;
        return new LinkedList<>(subFirst, subLast, subSize);
    }

    @Override
    public @NotNull String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (T elem : this) {
            sb.append(elem).append(", ");
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
}
