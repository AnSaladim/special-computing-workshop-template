package ru.spbu.apcyb.svp.tasks.task2;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Тесты для задания 2.
 */
class Task2Test {

    @Test
    void testAddEl() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(1);
        list.add(2);
        assertArrayEquals(new Object[]{1, 2}, list.toArray());
        list.add(1,"string");
        assertArrayEquals(new Object[]{1, "string", 2}, list.toArray());
        assertThrows(IndexOutOfBoundsException.class, ()->list.add(-1, 1));
    }

    @Test
    void testDelIndex() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(1);
        assertArrayEquals(new Object[]{1, 3, 4}, list.toArray());
        list.remove(2);
        assertArrayEquals(new Object[]{1, 3}, list.toArray());
        list.remove(0);
        assertArrayEquals(new Object[]{3}, list.toArray());
        assertThrows(IndexOutOfBoundsException.class, ()->list.remove(-1));
        list.add("string");
        assertThrows(UnsupportedOperationException.class, ()->list.remove("string"));
    }

    @Test
    void testAddIndex() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(3);
        list.add(1, 1);
        list.add(2, 2);
        Object[] l = list.toArray();
        Object[] arr = {3,1,2};
        assertArrayEquals(arr, l);
    }

    @Test
    void testFindEl() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        Object[] arr = {1,2,3};
        assertArrayEquals(list.toArray(), arr);
        assertTrue(list.contains(3));
    }

    @Test
    void testEmpty() {
        DoublyLinkedList list = new DoublyLinkedList();
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testIndex() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(1);
        list.add(2);
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(-1));
        assertEquals(2, list.get(1));
    }

    @Test
    void sizeEmptyTest() {
        DoublyLinkedList list = new DoublyLinkedList();
        assertEquals(0, list.size());
    }

    @Test
    void clearExceptionTest() {
        DoublyLinkedList list = new DoublyLinkedList();
        assertThrows(UnsupportedOperationException.class, list::clear);
    }

    @Test
    void iteratorExceptionTest() {
        DoublyLinkedList list = new DoublyLinkedList();
        assertThrows(UnsupportedOperationException.class, list::iterator);
        list.add("string");
        assertThrows(UnsupportedOperationException.class, () -> list.listIterator(0));
    }

    @Test
    void setExceptionTest() {
        DoublyLinkedList list = new DoublyLinkedList();
        assertThrows(UnsupportedOperationException.class, () -> list.set(0, 5));
    }

    @Test
    void indexOfExceptionTest() {
        DoublyLinkedList list = new DoublyLinkedList();
        assertThrows(UnsupportedOperationException.class, () -> list.indexOf(0));
    }

    @Test
    void lastIndexOfExceptionTest() {
        DoublyLinkedList list = new DoublyLinkedList();
        assertThrows(UnsupportedOperationException.class, () -> list.lastIndexOf(0));
    }

    @Test
    void listIteratorExceptionTest() {
        DoublyLinkedList list = new DoublyLinkedList();
        assertThrows(UnsupportedOperationException.class, list::listIterator);
    }

    @Test
    void sublistExceptionTest() {
        DoublyLinkedList list = new DoublyLinkedList();
        assertThrows(UnsupportedOperationException.class, () -> list.subList(0, 2));
    }
}
