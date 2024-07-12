/**
* Jesse Levisohn
* jlevisohn@brandeis.edu
* May 1, 2022
* PA3
* This class creates a minimum priority queue which implements a heap.
*/
public class MinPriorityQueue {
	
	private Heap h;
	private int length;
	private int numEntries;
	public static final int DEFAULT_SIZE = 20;
	
	
	/**
	 * This is the constructor for the MinPriorityQueue.
	 * It calls the overloaded constructor with default size.
	 */
	public MinPriorityQueue() {
		this(DEFAULT_SIZE);
		
	}
	
	/**
	 * This is the overloaded constructor with a parameter length.
	 * It creates a heap of size, length and sets the field length to length.
	 * @param length, an integer which is the size of the heap which the MinPriorityQueue implements.
	 */
	public MinPriorityQueue(int length) {
		if (length < 0) {
			throw new IllegalArgumentException("MinPriorityQueue must have positive length");
		}
		this.length = length;
		this.h = new Heap(length);
	}
	
	/**
	 * The insert method inserts a GraphNode into the MinPriorityQueue.
	 * It call the insert method of the heap on the heap field.
	 * Then it updates the size and length of the MinPriorityQueue.
	 * @param g, a GraphNode to be inserted into the MinPriorityQueue.
	 */
	public void insert(GraphNode g) {
		if (g == null || g.priority < 0) {
			throw new IllegalArgumentException();
		}
		h.insert(g);
		this.numEntries = h.size();
		this.length = h.length();
	}
	
	
	/**
	 * This method returns the highest priority element in the queue.
	 * It calls the delete method of the heap on the heap field, h.
	 * @return
	 */
	public GraphNode pullHighestPriorityElement() {
		if (isEmpty()) {
			throw new UnsupportedOperationException("Delete is not supported when the queue is empty");
		}
		this.numEntries --;
		return h.delete();
	}
	
	/**
	 * This method calls the heapifyDown method of the heap class on the heap field, h.
	 * @param g, a GraphNode which needs to be rebalanced.
	 */
	public void rebalanceDown(GraphNode g) {
		if (g == null || g.priority < 0) {
			throw new IllegalArgumentException();
		}
		h.heapifyDown(g);
	}
	
	/**
	 * This method calls the heapifyUp method of the heap class on the heap field, h.
	 * @param g, a GraphNode which needs to be rebalanced.
	 */
	public void rebalance(GraphNode g) {
		if (g == null || g.priority < 0) {
			throw new IllegalArgumentException();
		}
		h.heapifyUp(g);
	}
	
	/**
	 * This method checks whether the MinPriorityQueue is empty.
	 * @return true if the number of entries is zero, otherwise false
	 */
	public boolean isEmpty() {
		if (this.numEntries == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * This is a getter method for the heap field.
	 * @return, a heap, h.
	 */
	public Heap getHeap() {
		return this.h;
	}
	
	/**
	 * This method returns the number of elements in the MinPriorityQueue.
	 * @return, an integer, the number of entries in the MinPriorityQueue.
	 */
	public int size() {
		return this.numEntries;
	}
	
	/**
	 * This is a getter method for the length field.
	 * @return, an integer, the field length, the total capacity of the MinPriorityQueue.
	 */
	public int getLength() {
		return this.length;
	}
}
