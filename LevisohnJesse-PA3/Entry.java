/**
* Jesse Levisohn
* jlevisohn@brandeis.edu
* May 1, 2022
* PA3
* This class creates an Entry with a key and a value which fulfills the map function of the HashMap.
*/

public class Entry {
	
	public GraphNode key;
	public int value;
	
	/**
	 * This is the constructor for Entry.
	 * It creates an object of the Entry class with two fields, a key and a value.
	 * @param g, a GraphNode which will be the key of the entry.
	 * @param i, an integer which is the value of the entry.
	 */
	public Entry(GraphNode g, int i) {
		this.key = g;
		this.value = i;
	}
}
