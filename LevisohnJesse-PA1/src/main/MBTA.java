/**
* This class reads in the files and runs the simulation of the red line.
* None
*
* @author Jesse Levisohn
* jlevisohn@brandeis.edu
* March 4, 2022
* COSI 21A PA1
*/
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MBTA {

	public static final int SOUTHBOUND = 1;
	public static final int NORTHBOUND = 0;
	
	static final int TIMES = 6;
	static Railway r;
	
	/**
	 * This method initializes the stations, trains, and riders, and runs the simulation.
	 * Runtime: O(1)
	 * @param args
	 * @throws FileNotFoundException, throws an exception if an invalid file is inputted
	 */
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("INITATED RED LINE\n");
		r = new Railway();
		initStations("redLine.txt");
		initTrains("trains.txt");
		initRiders("riders.txt");
		System.out.println(r);
		System.out.println("BEGINNING RED LINE SIMULATION\n");
		runSimulation();
		
	}
	
	/**
	 * This method runs the simulation TIMES times.
	 * Runtime: O(n) where n is TIMES
	 */
	public static void runSimulation() {
		for (int i = 0; i < TIMES; i++) {
			System.out.println(" ------ " + (i + 1) + " ------ \n");
			System.out.println(r.simulate() + "\n");
		}
	}
	
	/**
	 * The method initializes the trains from the file.
	 * Runtime: O(n) where n is the number of lines in the file
	 * @param trainsFile, a file containing all the information needed to create the trains.
	 * @throws FileNotFoundException, throws an exception if the file cannot be found.
	 */
	public static void initTrains(String trainsFile) throws FileNotFoundException {
		Scanner in = new Scanner(new File(trainsFile));
		String station = "";
		int direction = 0;
		while (in.hasNextLine()){
			station = in.nextLine();
			direction = Integer.parseInt(in.nextLine());
			Train t = new Train(station, direction);
			r.addTrain(t);
		}
	}
	
	/**
	 * The method initializes the riders from the file.
	 * Runtime: O(n) where n is the number of lines in the file
	 * @param ridersFile, file containing all the information needed to create the riders.
	 * @throws FileNotFoundException throws an exception if the file cannot be found.
	 */
	public static void initRiders(String ridersFile) throws FileNotFoundException {
		Scanner in = new Scanner(new File(ridersFile));
		String id = "";
		String start = "";
		String destination = "";
		while (in.hasNextLine()){
			id = in.nextLine();
			start = in.nextLine();
			destination = in.nextLine();
			Rider rider = new Rider(id, start, destination);
			r.addRider(rider);
		}
	}
	
	/**
	 * The method initializes the stations from the file.
	 *  Runtime: O(n) where n is the number of lines in the file
	 * @param ridersFile, file containing all the information needed to create the stations.
	 * @throws FileNotFoundException throws an exception if the file cannot be found.
	 */
	public static void initStations(String stationsFile) throws FileNotFoundException {
		Scanner in = new Scanner(new File(stationsFile));
		String s = ""; 
		while (in.hasNextLine()) {
			s = in.nextLine();
			Station st = new Station(s);
			r.addStation(st);
		}
	}
}
