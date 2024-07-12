/**
* This class provides creates a railway and runs a simulation of the trains going through the stations
* None
*
* @author Jesse Levisohn
* jlevisohn@brandeis.edu
* March 4, 2022
* COSI 21A PA1
*/
package main;

public class Railway {

	public DoubleLinkedList<Station> railway;
	public String[] stationNames;
	private int numStations;
	
	/**
	 * Constructor
	 * O(1)
	 */
	public Railway() {
		this.railway = new DoubleLinkedList<Station>();
		this.stationNames = new String[18];
		this.numStations = 0;
	
	}
	
	/**
	 * Adds a station to the railway.
	 * Runtime: O(1)
	 * @param s, the station to be added
	 */
	public void addStation(Station s) {
		railway.insert(s);
		stationNames[numStations] = s.stationName();
		numStations ++;
	}
	
	/**
	 * Adds a rider to a station in the railway.
	 * Runtime: O(1)
	 * @param r, the rider to be added
	 */
	public void addRider(Rider r) {
		setRiderDirection(r);
		int i = 0;
		Station temp = new Station(r.getStarting());
		railway.get(temp).addRider(r);
	}
	
	/**
	 * Adds a train to a station in the railway.
	 * Runtime: O(1)
	 * @param t, the train to be added
	 */
	public void addTrain(Train t) {
		Station temp = new Station(t.getStation());
		(railway.get(temp)).addTrain(t);
	}
	
	/**
	 * Sets the direction that the rider is going.
	 * Runtime: O(n) where n is number of stations in the railway
	 * @param r, the rider
	 */
	public void setRiderDirection(Rider r) {
		int i = 0;
		String curr = stationNames[0];
		while (!curr.equals(r.getStarting())) {
			i++;
			curr = stationNames[i];
		}
		String destination = "";
		while (i < this.numStations - 1) {
			if (curr.equals(r.getDestination())) {
				destination = curr;
			}
			i++;
			curr = stationNames[i];
		}
		r.setDirection(destination.equals(""));
	}
	
	/**
	 * this simulates the process of going through the railway station by station
	 * runtime: O(n)
	 * @return a string log of the simulation
	 */
	public String simulate() {
		Node<Station> curr = railway.getFirst();
		Station st = curr.getData();
		String s = st + "\n";
		
		Train t = st.southBoardTrain();
		Rider r = new Rider("12345", "Alewife", "Porter");	
		if (curr.getNext() != null && t != null) {
			s += curr.getNext().getData().addTrain(t) + "\n";
			s += t.getStation() + " Disembarking Passengers:\n" + t.disembarkPassengers();
			s += t + "\n";
		}
		st.moveTrainNorthToSouth();
		
		//For all stations besides first and last
		while (curr.getNext().getNext() != null) { 
			curr = curr.getNext();
			st = curr.getData();
			s += st;
			Train t1 = st.southBoardTrain();
			Train t2 = st.northBoardTrain();
			if (t1 != null) {
				s += curr.getNext().getData().addTrain(t1) + "\n";
				s += t1.getStation() + " Disembarking Passengers:\n" + t1.disembarkPassengers();
				s += t1 + "\n";
			}
			if (t2 != null) {
				s += curr.getPrev().getData().addTrain(t2) + "\n";
				s += t2.getStation() + " Disembarking Passengers:\n" + t2.disembarkPassengers();
				s += t2+ "\n";
			}
		}
		
		st = curr.getNext().getData();
		Train last = st.northBoardTrain();
		s += st;
		if (last != null) {
			s += curr.getPrev().getData().addTrain(last);
			s += last.getStation() + " Disembarking Passengers:\n" + last.disembarkPassengers();
			s += last + "\n";
		}
		st.moveTrainSouthToNorth();
		return s;
	}
	/**
	 * toString method
	 * Runtime: O(n)
	 * @return a string representation of the railway
	 */
	@Override
	public String toString() {
		String s = "";
		Node<Station> curr = railway.getFirst();
		while (curr != null) {
			s += curr.getData().toString() + "\n";
			curr = curr.getNext();
		}
		return s;
	}
}
