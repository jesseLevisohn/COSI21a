/**
* This class provides the implementation of a Queue
* None
*
* @author Jesse Levisohn
* jlevisohn@brandeis.edu
* March 4, 2022
* COSI 21A PA1
*/
package main;

import java.util.NoSuchElementException;

public class Queue<T> {

	public T[] q;
	public int head;
	public int tail;
	public int numEntries;
	private int capacity;
	
	/**
	 * Queue constructor
	 * Runtime; O(1)
	 * @param capacity, size of queue
	 */
	@SuppressWarnings("unchecked")
	public Queue(int capacity) {
		this.capacity = capacity;
		if (this.capacity < 1) {
			throw new IllegalArgumentException("Queue must have a positive number of elements");
		}
		this.q = (T[]) new Object[this.capacity];
		this.head = -1;
		this.tail = 0;
		this.numEntries = 0;
	}
	
	/**
	 * Adds an element to the queue.
	 * Runtime: O(1)
	 * @param element, element to be added
	 */
	@SuppressWarnings("unchecked")
	public void enqueue(T element) {
		if (this.head == -1) {
			this.head = 0;
		}
		if (q[tail % capacity] != null) {
			T[] temp = (T[]) new Object[capacity * 2];
			for (int i = head; i < tail; i++) {
				temp[i - head] = q[i % capacity];
			}
			this.capacity = capacity * 2;
			this.tail = numEntries;
			this.q = temp;
			this.head = 0;
		}
		this.q[tail % capacity] = element;
		this.tail ++;
		this.numEntries ++;
	}
	
	/**
	 * Removes the first element
	 * Runtime: O(1)
	 */
	public void dequeue() { 
		this.q[head] = null;
		this.head ++;
		this.numEntries --;
		if (q[head] == null) {
			this.head = -1;
		}
		
	}
	
	/**
	 * Returns the first element
	 * Runtime: O(1)
	 * @return the first element
	 */
	public T front() {
		if (head == -1) {
			throw new NoSuchElementException();
		} else {
			return q[head];
		}
	}
	
	/**
	 * Returns the size of the queue
	 * Runtime: O(1)
	 * @return, the size of the queue
	 */
	public int size() {
		return numEntries;
	}
	
	/**
	 * toString method
	 * O(1)
	 * @return a string representation of the queue
	 */
	@Override
	public String toString() {
		String s = "";
		if (numEntries == 0) {
			s = "[]";
		} else {
			s = "[" + q[head];
			for (int i = head + 1; i < tail; i++) {
				s += ", " + q[i % capacity];
			}
			s += "]";
		}
		return s;
	}
	
	/**
	 * returns the location of the head of the queue
	 * O(1)
	 * @return head
	 */
	public int getHead() {
		return this.head;
	}
}
