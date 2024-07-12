/**
* This class creates the station object
* None
*
* @author Jesse Levisohn
* jlevisohn@brandeis.edu
* March 4, 2022
* COSI 21A PA1
*/
package main;

public class Station {

	public Queue<Rider> northBoundRiders;
	public Queue<Rider> southBoundRiders;
	public Queue<Train> northBoundTrains;
	public Queue<Train> southBoundTrains;
	private String name;
	
	/**
	 * constructor
	 * O(1)
	 * @param name
	 */
	public Station(String name) {
		this.name = name;
		this.northBoundRiders = new Queue<Rider>(20);
		this.southBoundRiders = new Queue<Rider>(20);
		this.northBoundTrains = new Queue<Train>(20);
		this.southBoundTrains = new Queue<Train>(20);
	}
	
	/**
	 * adds a rider to the right queue.
	 * O(1)
	 * @param r, the rider
	 * @return, boolean if the rider can be added to this station
	 */
	public boolean addRider(Rider r) { 
		if (r.getStarting().equals(this.name)) {
			if (r.goingNorth()) {
				northBoundRiders.enqueue(r);
			} else {
				southBoundRiders.enqueue(r);
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * adds a train to the station
	 * O(1)
	 * @param t the train
	 * @return a string representing the passengers that are getting off at that stop
	 */
	public String addTrain(Train t) {
		t.updateStation(name);
		if (t.goingNorth()) {
			northBoundTrains.enqueue(t);
		} else {
			southBoundTrains.enqueue(t);
		}
		return t.disembarkPassengers();
	}
	
	/**
	 * boards a train heading south with riders
	 * O(n) where n is the number of riders
	 * @return the train or null if there is no train heading that direction
	 */
	public Train southBoardTrain() {
		if (southBoundTrains.size() > 0) {
			Train t = southBoundTrains.front();
			southBoundTrains.dequeue();
			int i = 0; 
			while (i < southBoundRiders.size() && t.addPassenger(southBoundRiders.front())) {
				southBoundRiders.dequeue();
				i++;
			}
			return t;
		} else {
			return null;
		}	
	}
	
	/**
	 * boards a train heading north with riders
	 * O(n) where n is the number of riders
	 * @return the train or null if there is no train heading that direction
	 */
	public Train northBoardTrain() {
		if (northBoundTrains.size() > 0) {
			Train t = northBoundTrains.front();
			northBoundTrains.dequeue();
			int i = 0; 
			while (i < northBoundRiders.size() && t.hasSpaceForPassengers()) {
				t.addPassenger(northBoundRiders.front());
				northBoundRiders.dequeue();
				i++;
			}
			return t;

		} else {
			return null;
		}
	}
	
	/**
	 * moves a train from the north-bound queue to the south-bound queue
	 * O(1)
	 */
	public void moveTrainNorthToSouth() {
		if (northBoundTrains.size() != 0) {
			Train t = northBoundTrains.front();
			northBoundTrains.dequeue();
			t.swapDirection();
			southBoundTrains.enqueue(t);
			
		} 
	}
	/**
	 * moves a train from the south-bound queue to the north-bound queue
	 * O(1)
	 */
	public void moveTrainSouthToNorth() {
		if (southBoundTrains.size() != 0) {
			Train t = southBoundTrains.front();
			southBoundTrains.dequeue();
			t.swapDirection();
			northBoundTrains.enqueue(t);
		} 
	}
	
	/**
	 * tostring method
	 * O(1)
	 * @return a string representation of the station
	 */
	@Override
	public String toString() {
		return "Station: " + name + "\n" + northBoundTrains.size() + " north-bound trains waiting\n" + southBoundTrains.size() + " south-bound trains waiting\n" + northBoundRiders.size() + " north-bound passengers waiting\n"+ southBoundRiders.size() + " south-bound passengers waiting\n\n";
	}
	
	/**
	 * getter for the name
	 * O(1)
	 * @return the station name
	 */
	public String stationName() {
		return name;
	}
	
	/**
	 * equals method
	 * O(1)
	 * @return true or false
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Station) {
			Station other = (Station) o;
			return other.stationName().equals(this.name);
		} else {
			return false;
		}
		
	}
}
