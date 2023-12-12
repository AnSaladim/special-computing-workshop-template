package ru.spbu.apcyb.svp.tasks.task2;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    @Test
    void testQAdd() {
        Queue1 queue = new Queue1();
        queue.add(1);
        queue.add(2);
        assertArrayEquals(new Object[]{1,2}, queue.toArray());
    }

    @Test
    void testQPeek() {
        Queue1 queue = new Queue1();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertEquals(1, queue.peek());
    }

    @Test
    void testQEmpty() {
        Queue1 queue = new Queue1();
        queue.add(1);
        queue.add(2);
        assertFalse(queue.isEmpty());
    }

    @Test
    void clearExceptionQTest() {
        Queue1 list = new Queue1();
        assertThrows(UnsupportedOperationException.class, list::clear);
    }

    @Test
    void iteratorExceptionQTest() {
        Queue1 list = new Queue1();
        assertThrows(UnsupportedOperationException.class, list::iterator);
    }

    @Test
    void removeExceptionQTest() {
        Queue1 list = new Queue1();
        assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
    }

    @Test
    void removeExceptionQTest2() {
        Queue1 list = new Queue1();
        assertThrows(UnsupportedOperationException.class, list::remove);
    }

    @Test
    void interatorExceptionQTest() {
        Queue1 list = new Queue1();
        assertThrows(UnsupportedOperationException.class, list::iterator);
    }

    @Test
    void elementExceptionQTest() {
        Queue1 list = new Queue1();
        assertThrows(UnsupportedOperationException.class, () -> list.element());
    }

}
