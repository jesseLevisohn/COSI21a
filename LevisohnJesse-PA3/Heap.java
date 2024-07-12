/**
* Jesse Levisohn
* jlevisohn@brandeis.edu
* May 1, 2022
* PA3
* This class creates a minimum heap which can be used to implement a minimum priority heap.
*/
public class Heap {
	
	private int heapSize;
	private int length;
	private GraphNode[] heap;
	private HashMap h;
	public static final int DEFAULT_SIZE = 20;
	
	/**
	 * This is the constructor for the Heap class.
	 * It calls the overloaded constructor on the default size.
	 */
	public Heap() {
		this(DEFAULT_SIZE);
	}
	
	/**
	 * This is the overloaded constructor for the heap class.
	 * It takes in a parameter length and sets the field length to the parameter.
	 * It then creates an base array to build the heap of size length.
	 * Then it creates a HashMap to store the indices of the elements.
	 * And initializes the size of the heap.
	 * @param length, an integer which is the starting size of the underlying array.
	 */
	public Heap(int length) {
		if (length < 0) {
			throw new IllegalArgumentException();
		}
		this.length = length;
		this.heap = new GraphNode[length];
		this.h = new HashMap((length * 4)/3);
		this.heapSize = -1;
	}
	
	/**
	 * Finds the parent of the element at index i.
	 * @param i, an integer, the index to find the parent of.
	 * @return an integer which is the index of the parent of i. 
	 */
	public int parent(int i) {
		return i/2 - 1;
	}
	
	/**
	 * Finds the left child of the element at index i.
	 * @param i, an integer, the index to find the left child of.
	 * @return an integer which is the index of the left child of i. 
	 */
	public int left(int i) {
		return (2 * i) - 1;
	}
	
	/**
	 * Finds the right child of the element at index i.
	 * @param i, an integer, the index to find the right child of.
	 * @return an integer which is the index of the right child of i. 
	 */
	public int right(int i) {
		return (2 * i);
	}
	
	/**
	 * This method builds a min heap from an unordered array of elements.
	 * This element should never need to be utilized since elements are sorted as they are inserted.
	 */
	public void buildMinHeap() {
		for (int i = heapSize/2; i >= 0; i--) {
			heapifyDown(heap[i]);
		}
	}
	
	/**
	 * This is the heapifyDown method.
	 * It moves an element down the heap until it is in the correct position.
	 * It does this by swapping a node with its child if the child is smaller than it.
	 * @param g, a GraphNode to heapifyDown.
	 */
	public void heapifyDown(GraphNode g) {
		if (g == null || g.priority < 0) { //The priority cannot be negative to implement dijkstra's algorithm
			throw new IllegalArgumentException();
		}
		int i = h.getValue(g);
		int l = left(i + 1);
		int r = right(i + 1);
		int smallest = i;
		if (l <= this.heapSize && this.heap[l].priority < this.heap[i].priority) { //if the left child exists and is smaller than the parent
			smallest = l;
		} 
		if (r <= this.heapSize && this.heap[r].priority < this.heap[smallest].priority) { //if the right child exists and is smaller than the minimum of the parent and the left child
			smallest = r;
		}
		if (smallest != i) { //swap the smallest child and the parent 
			GraphNode temp = heap[i];
			heap[i] = heap[smallest];
			h.set(heap[smallest], i);
			heap[smallest] = temp;
			h.set(temp, smallest);
			heapifyDown(heap[smallest]); //recursively call heapifyDown
		}
	}
	
	/**
	 * This is the heapifyUp method.
	 * It moves an element up the heap until it is in the correct position.
	 * It does this by swapping a node with its parent if the parent is smaller than it.
	 * @param g, a GraphNode to heapifyDown.
	 */
	public void heapifyUp(GraphNode g) {
		if (g == null || g.priority < 0) { //The priority cannot be negative to implement dijkstra's algorithm
			throw new IllegalArgumentException();
		}
		int i = h.getValue(g);
		while(i > 0 && heap[i].priority < heap[parent(i + 1)].priority) { //while the parent exists and is larger than the child
			GraphNode temp = heap[i]; //swap the parent and the child.
			heap[i] = heap[parent(i + 1)];
			h.set(heap[parent(i + 1)], i);
			heap[parent(i + 1)] = temp;
			h.set(temp, parent(i + 1));
			i = parent(i + 1); // i gets the parent
		}
	}
	
	/**
	 * The insert method inserts an element into the heap.
	 * Elements are always inserted at the bottom and then heapifyuUp is called to move them to the correct position.
	 * @param g, a GraphNode to insert into the heap
	 */
	public void insert(GraphNode g) {
		if (g == null || g.priority < 0) { //The priority cannot be negative to implement dijkstra's algorithm
			throw new IllegalArgumentException();
		}
		this.heapSize ++;
		if (heapSize == length) { //if the heap is full reSize the heap
			reSize();
		}
		heap[heapSize] = g; //add the element to the end of the list
		h.set(g, heapSize); //add the element to the hash map
		heapifyUp(g);  //heapifyUp the element
	}
	
	/**
	 * The delete method deletes the first element from the list.
	 * It swaps the first and last elements and then removes the last element.
	 * Then heapifyDown is called on the new first node.
	 * @return the first GraphNode in the heap which was just deleted.
	 */
	public GraphNode delete() {
		if (isEmpty()) { //if the heap is empty there is nothing to delete
			throw new UnsupportedOperationException("Delete is not supported when the heap is empty");
		}
		GraphNode curr = heap[0]; 
		h.set(heap[0], -1); //the GraphNode's value get set to -1 to show that it is no longer in the array.
		heap[0] = heap[heapSize]; //the last element becomes the first element.
		h.set(heap[heapSize], 0); 
		heap[heapSize] = null;
		this.heapSize --;
		if (!isEmpty()) { //if the deleted element wasn't the last element in the list
			heapifyDown(heap[0]); // heapifyDown the new first element
		}
		return curr; //return the deleted element
	}
	
	/**
	 * This method resizes the heap.
	 * If the heap has been filled, the hash map is rehashed and the heap is replaced with a 
	 * larger underlying array. All of the element are then transferred to the new array.
	 */
	public void reSize() {
		h.reHash();
		GraphNode[] biggerHeap = new GraphNode[length * 2];
		this.length = length * 2;
		for (int i = 0; i < heapSize; i++) {
			biggerHeap[i] = heap[i];
		}
		this.heap = biggerHeap;
	}
	
	/**
	 * This method returns the length of the underlying array for the heap.
	 * @return an integer length.
	 */
	public int length() {
		return this.length;
	}
	
	/**
	 * This method returns the number of elements in the heap.
	 * @return an integer field, heapSize + 1
	 */
	public int size() {
		return this.heapSize + 1;
	}
	
	/**
	 * Getter method for the hash map.
	 * @return the field h, a hash map.
	 */
	public HashMap getHashMap() {
		return this.h;
	}
	
	/**
	 * Checks if the heap is empty
	 * @return a boolean, true if it has no elements, false otherwise
	 */
	public boolean isEmpty() {
		if (this.heapSize == -1) {
			return true;
		}
		return false;
	}
	/**
	 * This is a toString method which prints out the elements of the heap.
	 * @return a String which represents the heap.
	 */
	@Override
	public String toString() {
		String s = "[";
		for (int i = 0; i < heapSize; i++) {
			if (heap[i] == null) {
				s += "null, ";
			} else {
				s += heap[i].priority + ", ";
			}
		}
		if (heapSize != -1) {
			s += heap[heapSize].priority; 
		}
		s += "]";
		return s;
	}
	
	/**
	 * This method gets the heap array.
	 * @return an array of GraohNodes
	 */
	public GraphNode[] getArray() {
		return this.heap;
	}
}
