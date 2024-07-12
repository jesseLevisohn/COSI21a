/**
* This class creates the nodes for the double linked list.
* None
*
* @author Jesse Levisohn
* jlevisohn@brandeis.edu
* March 4, 2022
* COSI 21A PA1
*/
package main;

public class Node<T> {
	
	private T data;
	private Node<T> next;
	private Node<T> prev;
	
	/**
	 * The constructor for the node.
	 * Runtime: O(1)
	 * @param data, the data of the node.
	 */
	public Node(T data) {
		setData(data);
		this.next = null;
		this.prev = null;
	}
	
	/**
	 * Sets the data to the parameter.
	 * Runtime: O(1)
	 * @param data, data to set the data field of a node to.
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	
	/**
	 * Sets the next pointer of the node.
	 * Runtime: O(1)
	 * @param next, a node to point to.
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	/**
	 * Sets the previous pointer of the node.
	 * Runtime: O(1)
	 * @param prev, a node to point to.
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	
	/**
	 * gets the next node
	 * Runtime: O(1)
	 * @return, next, the next node in the list.
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * gets the previous node
	 * Runtime: O(1)
	 * @return, prev, the previous node in the list.
	 */
	public Node<T> getPrev() {
		return prev;
	}
	
	/**
	 * gets the data of a node
	 * Runtime: O(1)
	 * @return data, the data of a node
	 */
	public T getData() {
		return data;
	}
	/**
	 * creates a string representation of the node
	 * Runtime: O(1)
	 * @return data.toString(), a string representation of the data
	 */
	@Override
	public String toString() {
		return data.toString();
	}
}
