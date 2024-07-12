package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import main.Queue;

class StudentQueueTest {
	
	Queue q;
	
	@Test
	void testConstructor() {
		q = new Queue(8);
		
		assertEquals(0, q.size());
		assertThrows(NoSuchElementException.class, () -> {
			Queue q2 = new Queue(8);
			q2.front();
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Queue q3 = new Queue(0);
		});
		assertEquals("[]", q.toString());
	}

	
	@Test
	void testEnqueue() {
		q = new Queue(8);
		q.enqueue(2);
		assertEquals(2, q.front());
		assertEquals(1, q.size());
		q.enqueue(3);
		q.enqueue(1);
		assertEquals(2, q.front());
		assertEquals(3, q.size());
		assertEquals("[2, 3, 1]", q.toString());
		for (int i = 0; i < 5; i++) {
			q.enqueue(1);
		}
		q.dequeue();
		q.enqueue(2);
		assertEquals(3, q.front());
		assertEquals("[3, 1, 1, 1, 1, 1, 1, 2]", q.toString());
		assertEquals(8, q.size());
		assertEquals(1, q.getHead());
		q.enqueue(7);
		assertEquals(3, q.front());
		assertEquals("[3, 1, 1, 1, 1, 1, 1, 2, 7]", q.toString());
		assertEquals(9, q.size());
	}
	
	@Test
	void testDequeue() {
		q = new Queue(8);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(1);
		q.dequeue();
		assertEquals(3, q.front());
		assertEquals(2, q.size());
		assertEquals(1, q.getHead());
		assertEquals("[3, 1]", q.toString());
	}

}
