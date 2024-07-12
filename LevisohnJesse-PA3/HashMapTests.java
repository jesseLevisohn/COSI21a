
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HashMapTests {
	
	private HashMap h;
	
	@Test
	void test() {
		h = new HashMap(7);
		GraphNode g1 = new GraphNode("1234", true);
		GraphNode g2 = new GraphNode("2345", true);
		GraphNode g3 = new GraphNode("1234", true);
		GraphNode g4 = new GraphNode("3456", true);
		GraphNode g5 = new GraphNode("1234", true);
		GraphNode g6 = new GraphNode("1234", false);
		
		h.set(g1, 0);
		h.set(g2, 0);
		h.set(g3, 0);
		h.set(g4, 0);
		
		h.set(g4, 2);
		
		//test hashMap construction
		assertEquals(h.getHashArray().length, 7);
		assertEquals(h.getEntries(), 4);
		assertThrows(IllegalArgumentException.class, () -> {
			HashMap h3 = new HashMap(-1);
		});
		
		//test hash function and findKey
		assertEquals(h.findKey(g1), 6);
		assertEquals(h.findKey(g2), 0);
		
		//tests linear probing
		assertEquals(h.findKey(g3), 1);
		assertEquals(h.findKey(g4), 2);
		
		//tests hasKey
		assertTrue(h.hasKey(g3));
		assertTrue(h.hasKey(g4));
		assertFalse(h.hasKey(g5));
		assertFalse(h.hasKey(null));
		
		//test getValue
		assertEquals(h.getValue(g1), 0);
		assertEquals(h.getValue(g4), 2);
		assertThrows(IllegalArgumentException.class, () -> {
			HashMap h2 = new HashMap(2);
			h2.getValue(null);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			HashMap h2 = new HashMap(2);
			h2.getValue(g1);
		});
		
		//test set
		assertThrows(IllegalArgumentException.class, () -> {
			HashMap h4 = new HashMap(2);
			h4.set(null, 0);
		});
			
		//test reHash
		h.set(g5, 1);
		h.set(g6, 0);
		assertEquals(h.getTableSize(), 14);
		assertTrue(h.hasKey(g1));
		assertTrue(h.hasKey(g2));
		assertTrue(h.hasKey(g3));
		assertTrue(h.hasKey(g4));
		assertTrue(h.hasKey(g5));
		
	}

}
