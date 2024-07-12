/**
* This class provides the implementation of a generic non circular double linked list
* None
*
* @author Jesse Levisohn
* jlevisohn@brandeis.edu
* March 4, 2022
* COSI 21A PA1
*/
package main;

public class DoubleLinkedList<T> {
	
	private Node<T> first;
	private Node<T> last;
	private int size;
	
	
	/**
	 * This is the class constructor.
	 * Constructs an empty double linked list.
	 * Runtime: constant time, O(1)
	 */
	public DoubleLinkedList() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	/**
	 * Returns the first node in the list.
	 * Runtime: constant time, O(1)
	 * @return a node, first, the first node in the list
	 */
	public Node<T> getFirst() {
		return first;
	}
	
	/**
	 * Inserts an element at the end of the list.
	 * Runtime: constant time, O(1)
	 * @param element, an element to insert
	 */
	public void insert(T element) {
		Node<T> n = new Node<T>(element);
		if (first == null) { //If the list is empty
			this.first = n;
		} else {
			last.setNext(n);
			n.setPrev(last);
		}
		this.size ++;
		this.last = n;
	}
	
	/**
	 * Deletes a node whose data corresponds to a given element
	 * Runtime: O(n)
	 * @param key, the data to be deleted
	 * @return an object T which is the data of the node which was deleted or null if it doesn't exist
	 */
	public T delete(T key) {
		Node<T> n = null;
		Node<T> curr = first;
		while (curr != n) {
			if (curr.getData().equals(key)) {
				n = curr; 
				if (curr.getPrev() != null) {
					curr.getPrev().setNext(curr.getNext());
				} else {
					this.first = curr.getNext();
				}
				if (curr.getNext() != null) {
					curr.getNext().setPrev(curr.getPrev());
				} else {
					this.last = curr.getPrev();
				}
			} else {
				curr = curr.getNext();
			}
		}
		if (n == null) {
			return null;
		} else {
			this.size --;
			return n.getData();
		}
		
	}
	
	/**
	 * Returns the data of a node which matches a given object.
	 * Runtime: O(n)
	 * @param key, an object to be searched for
	 * @return, the key, or null if it is not in the list
	 */
	public T get(T key) {
		Node<T> n = null;
		Node<T> curr = first;
		while (curr != n) {
			if (curr.getData().equals(key)) {
				n = curr;
			} else {
				curr = curr.getNext();
			}
		}
		if (n == null) {
			return null;
		} else {
			return n.getData();
		}
	}
	
	/**
	 * getter method for size
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	
	
	/**
	 * Provides a string representation of the list
	 * @return a string
	 */
	@Override
	public String toString() {
		Node<T> curr = first;
		String s = "";
		while (curr != null) {
			s += curr.toString() + "\n";
			curr = curr.getNext();
		}
		return s;
	}
}
