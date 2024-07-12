/**
* Jesse Levisohn
* jlevisohn@brandeis.edu
* May 1, 2022
* PA3
* This class creates a HashMap which hashes entries into an array using a hash fucntion and linear probing.
*/

public class HashMap {
	
	private Entry[] hashArray; 
	private int tableSize;
	private int numEntries;
	public static final double LOAD_FACTOR = 0.75;
	public static final int DEFAULT_SIZE = 20;
	
	
	/**
	 * This is the constructor for the HashMap.
	 * It calls the overloaded constructor with the parameter DEFAULT_SIZE
	 * This sets the size of the map and creates an array of that size;
	 */
	public HashMap() {
		this(DEFAULT_SIZE);
	}
	
	/**
	 * This is the overloaded constructor for the HashMap.
	 * It calls the overloaded constructor with the parameter DEFAULT_SIZE
	 * This sets the size of the map and creates an array of that size;
	 * @parameter tableSize, an integer which is the initial size of the HashMap.
	 */
	public HashMap(int tableSize) {
		if (tableSize < 1) { //HashMaps cannot have negative size and cannot be created as empty
			throw new IllegalArgumentException();
		}
		this.tableSize = tableSize;
		this.hashArray = new Entry[tableSize];
	}
	
	/**
	 * This methods takes in a key and a value.
	 * If the key is already in the map, the key's value is updated to equal the parameter.
	 * If they key is not in the map, an Entry object is created with the key and the value.
	 * This Entry is then added to the map at the correct location determined by the hash function.
	 * @param key, a GraphNode which is the key of the Entry that is being searched for.
	 * @param value, an integer which is the new value of the Entry with the key.
	 */
	public void set(GraphNode key, int value) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		if (hasKey(key)) { //if the key is in the map
			int index = findKey(key); //finds the location of the key
			hashArray[index].value = value; //updates the value
		} else { //if the key is not in the map
			Entry e = new Entry(key, value); //creates an entry
			this.numEntries ++; 
			if (numEntries > (tableSize * LOAD_FACTOR)) { //if the entry causes the number of entries to exceed the load factor
				reHash(); //the HashMap gets rehashed into a bigger table
			}
			int index = insertIndex(key); //finds the index to insert the the key
			hashArray[index] = e;
		}
	}
	
	/**
	 * The hasKey method checks whether the map has the entry corresponding to the key, g.
	 * @param g, a GraphNode which is checked whether it is corresponds to an entry in the list.
	 * @return a boolean, true if the key is in the map, false if it isn't.
	 */
	public boolean hasKey(GraphNode g) { 
		if (g == null) {
			return false;
		}
		int index = findKey(g); //returns the index of the key or -1 if the key isn't in the list.
		if (index == -1) {
			return false;
		}
		return true;
	}
		
	/**
	 * The method findKey searches the HashMap for the key, g.
	 * If the key is in the map, the method returns the index of the key.
	 * If the key is not in the map, it returns -1. 
	 * @param g, a GraphNode that is searched for.
	 * @return an integer, either the index of g if it is in the list or -1.
	 */
	public int findKey(GraphNode g) {
		int location = hashFunction(g.getId()); //runs the hashFunction on g
		while (hashArray[location] != null) { //While the location in the array isn't empty
			if (hashArray[location].key.equals(g)) { //check is the entry at the index has the right key
				return location;
			}
			location = (location + 1) % this.tableSize; //uses linear probing to check if the key is in a different index
		}
		return -1; 
	}
	
	/**
	 * The method hashFunction determines where the key should be hashed.
	 * It truncates the id of the GraphNode to four digits.
	 * The id is then translated into numbers by using ascii values.
	 * Each of the four characters is subtracted by 40 so that it is between 0 and 100 and then multiplied by 100.
	 * This makes sure that the order of the characters in the key is preserved.
	 * The final index is the number that is the resulting of these operations modulo the size of the table.
	 * @param id, the id of a GraphNode which is used to hash the GraphNode.
	 * @return index, am integer which is the result of calling the hash function.
	 */
	public int hashFunction(String id) {
		int trunc = 0;
		for (int i = 0; i < 4; i++) {
			trunc *= 100;
			trunc += (id.charAt(i) - 40);
		}
		int index = trunc % tableSize;
		return index;
	}
	
	
	/**
	 * The method insertIndex finds the index where a GraphNode which isn't in the HashMap should be inserted.
	 * It calls the hash function and if that index isn't open, it uses linear probing to find an index to insert
	 * the element.
	 * @param key, a GraphNode to be inserted into the HashMap
	 * @return index, an integer, the index where the key should be inserted.
	 */
	public int insertIndex(GraphNode key) {
		String id = key.getId();
		int index = hashFunction(key.getId());
		while (hashArray[index] != null) {
			index = (index + 1) % this.tableSize;
		}
		return index;
	}
	
	/**
	 * This method reHash's the HashMap into a HashMap which is twice as big as the previous map.
	 */
	public void reHash() {
		Entry[] original = this.hashArray;
		this.hashArray = new Entry[tableSize * 2]; //creates a new array twice the size of the old one
		this.tableSize = tableSize * 2;
		for (Entry e : original) {
			if (e != null) {
				this.hashArray[insertIndex(e.key)] = e; //rehashes every key into the new map
			}
		}
	}
	
	/**
	 * This method gets the value of an entry given its key.
	 * @param g, a GraphNode, whose value will retrieve
	 * @return an integer which is the value associated with the given key.
	 */
	public int getValue(GraphNode g) {
		if (g == null || !hasKey(g)) {
			throw new IllegalArgumentException();
		}
		return hashArray[findKey(g)].value;
	}
	
	/**
	 * This method is a getter method for the hash table.
	 * @return, hashArray, an array which holds all of the entries in the HashMap.
	 */
	public Entry[] getHashArray() {
		return this.hashArray;
	}
	
	/**
	 * This method gets the number of entries in the list.
	 * @return, numEntries, the number of entries in the list.
	 */
	public int getEntries() {
		return this.numEntries;
	}
	
	/**
	 * This is a getter method for the field tableSize.
	 * @return the field tableSize which is the size of the hash table.
	 */
	public int getTableSize() {
		return this.tableSize;
	}
}
