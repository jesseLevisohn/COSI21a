/**
* This class creates a rider.
* None
*
* @author Jesse Levisohn
* jlevisohn@brandeis.edu
* March 4, 2022
* COSI 21A PA1
*/
package main;

public class Rider {
	
	private String ID;
	private String start;
	private String destination;
	private boolean direction;
	
	/**
	 * Constructor
	 * O(1)
	 * @param riderID
	 * @param startingStation
	 * @param destinationStation
	 */
	public Rider(String riderID, String startingStation, String destinationStation) {
		this.ID = riderID;
		this.start = startingStation;
		this.destination = destinationStation;
		this.direction = false;
	}
	
	/**
	 * get method for the starting station 
	 * O(1)
	 * @return string name of the stating station
	 */
	public String getStarting() {
		return start;
	}
	
	/**
	 * get method for the destination station 
	 * O(1)
	 * @return string name of the destination station
	 */
	public String getDestination() {
		return destination;
	}
	
	/**
	 * getter for the id
	 * O(1)
	 * @return the id
	 */
	public String getRiderID() {
		return ID;
	}
	
	/**
	 * getter for the direction
	 * O(1)
	 * @return direction
	 */
	public boolean goingNorth() {	
		return direction;
	}
	
	/**
	 * switches the direction of the rider
	 * O(1)
	 */
	public void swapDirection() {
		this.direction = !direction;
	}
	
	/**
	 * set the direction of the rider
	 * O(1)
	 * @param direction 
	 */
	public void setDirection(Boolean direction) {
		this.direction = direction;
	}
	
	/**
	 * toString method
	 * O(1)
	 * @return string representation of the rider
	 */
	@Override
	public String toString() {
		String nOrS = "south";
		if (this.direction) {
			nOrS = "north";
		}
		return ID + " going " + nOrS + " to " + destination;
	}
	
	/**
	 * equal method
	 * O(1)
	 */
	@Override
	public boolean equals(Object s) {
		if (s instanceof Rider) {
			Rider r = (Rider) s;
			return ID == r.getRiderID();
		} else {
			return false;
		}
		
		
	}
}
