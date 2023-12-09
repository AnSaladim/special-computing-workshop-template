package ru.spbu.apcyb.svp.tasks.task2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * Queue
 */
public class Queue1 implements Queue<Object> {
    private final DoublyLinkedList dll;

    public Queue1() {
        this.dll = new DoublyLinkedList();
    }

    @Override
    public int size() {
        return this.dll.size();
    }

    @Override
    public boolean isEmpty() {
        return this.dll.isEmpty();
    }

    @Override
    public Object peek() {
        Object result = null;
        if (!this.isEmpty()) {
            result = this.dll.get(0);
        }
        return result;
    }

    @Override
    public boolean add(Object o) {
        return this.dll.add(o);
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Object> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return this.dll.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offer(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object poll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object element() {
        throw new UnsupportedOperationException();
    }
}
