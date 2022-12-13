package cardealer.collections.linkedlist;

import org.jetbrains.annotations.Nullable;

/**
 * @author Moussa
 */
@SuppressWarnings("java:S1104")
public class Node<T> {
    public T value;
    public @Nullable Node<T> next;
    public @Nullable Node<T> prev;

    public Node(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}
