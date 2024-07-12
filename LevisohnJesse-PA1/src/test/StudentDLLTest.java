package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.DoubleLinkedList;
import main.Queue;
import main.Station;

class StudentDLLTest {

	DoubleLinkedList<Station> d;
	
	@Test
	void testConstructor() {
		d = new DoubleLinkedList<Station>();
		assertEquals(0, d.size());
		assertEquals(null, d.getFirst());
		assertEquals("", d.toString());
	}
	
	@Test
	void testInsert() {
		d = new DoubleLinkedList<Station>();
		
		//check insert of one element
		Station s1 = new Station("Alewife");
		Station s2 = new Station("Davis");
		d.insert(s1);
		d.insert(s2);
		assertEquals(2, d.size());
		assertEquals(s1, d.getFirst().getData());
		assertEquals("Station: Alewife\n" + 0 + " north-bound trains waiting\n" + 0 + " south-bound trains waiting\n" + 0 + " north-bound passengers waiting\n"+ 0 + " south-bound passengers waiting\n" + "Station: Davis\n" + 0 + " north-bound trains waiting\n" + 0 + " south-bound trains waiting\n" + 0 + " north-bound passengers waiting\n"+ 0 + " south-bound passengers waiting\n", d.toString());
		
		// check insert of more than one element
		Station s3 = new Station("Porter");
		d.insert(s3);
		assertEquals(3, d.size());
		assertEquals("Station: Alewife\n" + 0 + " north-bound trains waiting\n" + 0 + " south-bound trains waiting\n" + 0 + " north-bound passengers waiting\n"+ 0 + " south-bound passengers waiting\n" + "Station: Davis\n" + 0 + " north-bound trains waiting\n" + 0 + " south-bound trains waiting\n" + 0 + " north-bound passengers waiting\n"+ 0 + " south-bound passengers waiting\n" + "Station: Porter\n" + 0 + " north-bound trains waiting\n" + 0 + " south-bound trains waiting\n" + 0 + " north-bound passengers waiting\n"+ 0 + " south-bound passengers waiting\n", d.toString());
	}
	
	@Test
	void testDelete() {
		d = new DoubleLinkedList<Station>();
		
		// check delete from end
		Station s1 = new Station("Alewife");
		Station s2 = new Station("Davis");
		d.insert(s1);
		d.insert(s2);
		Station deletedStation = d.delete(s2);
		assertEquals(1, d.size());
		assertEquals(s2, deletedStation);
		assertEquals(d.getFirst().getNext(), null);
		
		
		// check delete from middle
		d.insert(s2);
		Station s3 = new Station("Porter");
		d.insert(s3);;
		deletedStation = d.delete(s2);
		assertEquals(2, d.size());
		assertEquals(s2, deletedStation);
		assertEquals((d.getFirst().getNext()).getData(), s3);
		assertEquals(((d.getFirst().getNext()).getPrev()).getData(), s1);
		
		//check delete from front
		d.delete(s1);
		assertEquals(d.getFirst().getData(), s3);
		assertEquals(1, d.size());
		assertEquals("Station: Porter\n" + 0 + " north-bound trains waiting\n" + 0 + " south-bound trains waiting\n" + 0 + " north-bound passengers waiting\n"+ 0 + " south-bound passengers waiting\n", d.toString());
		
	}
	
	@Test
	void testGet() {
		d = new DoubleLinkedList<Station>();
		Station s1 = new Station("Alewife");
		Station s2 = new Station("Davis");
		Station s3 = new Station("Porter");
		
		//test empty list
		assertEquals(d.get(s1), null);
		
		
		d.insert(s1);
		d.insert(s2);
		d.insert(s3);
		
		//test get front
		assertEquals(d.get(s1).stationName(), "Alewife");
		
		//test get middle
		assertEquals(d.get(s2).stationName(), "Davis");
		
		//test get last
		assertEquals(d.get(s3).stationName(), "Porter");
		
		//test get with stations which are equal but not the same
		Station A = new Station("Alewife");
		assertEquals(d.get(A).stationName(), "Alewife");
		
		//tests element not in DLL
		Station s4 = new Station("Harvard");
		assertEquals(d.get(s4), null);
	}
	
	
	
	
	
	
	

}
