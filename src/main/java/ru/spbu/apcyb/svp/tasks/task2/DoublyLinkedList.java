package ru.spbu.apcyb.svp.tasks.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Double linked list
 */
public class DoublyLinkedList implements List<Object> {
    private static class Node {
        Object data;
        Node next;
        Node prev;

        Node(Object data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public boolean add(Object obj) {
        Node newNode = new Node(obj);
        if (this.head != null) {
            Node curr = this.head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
            newNode.prev = curr;
        } else {
            this.head = newNode;
        }

        this.size += 1;

        return true;
    }

    @Override
    public void add(int index, Object obj) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(obj);
        if (this.size == 0 || this.size == index) {
            add(obj);
        } else {
            Node curr = this.head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            newNode.prev = curr.prev;
            newNode.next = curr;
            curr.prev = newNode;
            if (index != 0) {
                newNode.prev.next = newNode;
            } else {
                this.head = newNode;
            }

            this.size += 1;
        }
    }

    @Override
    public Object remove(int index) {
        Node curr = this.head;
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("beyond list indexes");
        }
        if (index == 0) {
            this.head = this.head.next;
            this.head.prev = null;
        } else {
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            if (index != this.size - 1) {
                curr.next.prev = curr.prev;
            }
            curr.prev.next = curr.next;
        }

        this.size -= 1;

        return curr.data;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Метод remove не переопределен для списка");
    }


    @Override
    public boolean contains(Object data) {
        for (Node curr = head; curr != null; curr = curr.next) {
            if (curr.data.equals(data)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("beyond list indexes");
        }

        Node curr = this.head;
        for (int i = 0; curr.next != null && i < index; i++) {
            curr = curr.next;
        }

        return curr.data;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Метод containsAll не переопределен для списка");
    }

    @Override
    public boolean addAll(Collection<?> c) {
        throw new UnsupportedOperationException("Метод addAll не переопределен для списка");
    }

    @Override
    public boolean addAll(int index, Collection<?> c) {
        throw new UnsupportedOperationException("Метод addAll не переопределен для списка");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Метод removeAll не переопределен для списка");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Метод retainAll не переопределен для списка");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Метод clear не переопределен для списка");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Метод indexOf не переопределен для списка");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Метод lastIndexOf не переопределен для списка");
    }

    @Override
    public ListIterator<Object> listIterator() {
        throw new UnsupportedOperationException("Метод listIterator не переопределен для списка");
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        throw new UnsupportedOperationException("Метод listIterator не переопределен для списка");
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Метод subList не переопределен для списка");
    }

    @Override
    public Iterator<Object> iterator() {
        throw new UnsupportedOperationException("Метод iterator не переопределен для списка");
    }

    @Override
    public Object[] toArray() {
        ArrayList<Object> arr = new ArrayList<>();

        Node curr = this.head;
        while (curr != null) {
            arr.add(curr.data);
            curr = curr.next;
        }

        return arr.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Метод toArray не переопределен для списка");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object set(int index, Object element) {
        throw new UnsupportedOperationException("Метод set не переопределен для списка");
    }

}
