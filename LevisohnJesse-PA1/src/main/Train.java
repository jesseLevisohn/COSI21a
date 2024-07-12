/**
* This class creates the train object
* known bugs: disembark passengers doesn't remove passengers from the passengers array
*
* @author Jesse Levisohn
* jlevisohn@brandeis.edu
* March 4, 2022
* COSI 21A PA1
*/
package main;

public class Train {

	public static final int TOTAL_PASSENGERS = 10;
	public Rider[] passengers;
	public int passengerIndex;
	private String currentStation;
	private Boolean direction;
	
	/**
	 * constructor
	 * O(1)
	 * @param currentStation, the station the train is at
	 * @param direction
	 */
	public Train(String currentStation, int direction) {
		updateStation(currentStation);
		this.direction = (0 == direction);
		this.passengers = new Rider[TOTAL_PASSENGERS];
		this.passengerIndex = 0;
	}
	
	/**
	 * Returns if the train is going north or not
	 * O(1)
	 * @return true if going north, false otherwise
	 */
	public boolean goingNorth() {
		return this.direction;
	}
	
	/**
	 * switches the trains direction
	 * O(1)
	 */
	public void swapDirection() {
		this.direction = !direction; 
	}
	
	/**
	 * returns a string of all the passengers
	 * O(n) where n is the number of passengers
	 * @return a string
	 */
	public String currentPassengers() {
		String s = "";
		for (int i = 0; i < passengerIndex; i++) {
			s += passengers[i].toString() + "\n";
		}
		return s;
	}
	
	/**
	 * adds a passenger
	 * O(1)
	 * @param r the rider to be added
	 * @return true if the rider was added, false otherwise
	 */
	public boolean addPassenger(Rider r) {
		if (r.getStarting().equals(this.currentStation) && r.goingNorth() == this.direction && hasSpaceForPassengers()) {
			passengers[passengerIndex] = r;
			this.passengerIndex ++;
			return true;
		}
		return false;
	}
	
	/**
	 * check is there is space to add a a passenger
	 * O(1)
	 * @return boolean
	 */
	public boolean hasSpaceForPassengers() {
		return passengerIndex != TOTAL_PASSENGERS;
	}
	
	/**
	 * Disembarks the passengers from the train and returns a string.
	 * This method contains a bug. It only returns the string but doesn't remove the passengers from the array.
	 * O(n) where n is the number of passengers.
	 * @return a string of the passengers who got off at that stop.
	 */
	public String disembarkPassengers() {
		String s = "";
		for (int i = 0; i < passengerIndex; i++) {
			if (passengers[i].getDestination().equals(currentStation)) {
				s += passengers[i].toString() + "\n";

			}
		}
		return s;
	}
	
	/**
	 * Changes the station the train is at.
	 * O(1)
	 * @param s the name of the next station
	 */
	public void updateStation(String s) {
		this.currentStation = s;
	}
	
	/**
	 * getter
	 * O(1)
	 * @return the station where the train is.
	 */
	public String getStation() {
		return currentStation;
	}
	
	/**
	 * to string method
	 * O(1)
	 * @return string representation of the train
	 */
	@Override
	public String toString() {
		String northOrSouth = "";
		if (this.direction == false) {
			northOrSouth = "Southbound";
		} else {
			northOrSouth = "Northbound";
		}
		return "Direction: " + northOrSouth + "\nPassengers: " + currentPassengers() + "\nCurrent Station: "+ currentStation + "\n";
	}
}
