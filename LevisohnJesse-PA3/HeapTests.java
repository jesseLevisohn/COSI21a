import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HeapTests {

	private Heap h;
	@Test
	void test() {
		this.h = new Heap(10);
		
		//test constructor
		assertThrows(IllegalArgumentException.class, () -> {
			Heap h = new Heap(-1);
		});
		
		GraphNode g1 = new GraphNode("1234", true);
		GraphNode g2 = new GraphNode("2345", true);
		GraphNode g3 = new GraphNode("1234", true);
		GraphNode g4 = new GraphNode("3456", true);
		GraphNode g5 = new GraphNode("4567", true);
		GraphNode g6 = new GraphNode("5678", true);
		GraphNode g7 = new GraphNode("1235", true);
		GraphNode g8 = new GraphNode("1235", true);
		GraphNode g9 = new GraphNode("1235", true);
		GraphNode g10 = new GraphNode("1235", true);
		GraphNode g11 = new GraphNode("1236", false);
		
		
		g1.priority = 4;
		g2.priority = 1;
		g3.priority = 3;
		g4.priority = 2;
		g5.priority = 16;
		g6.priority = 9;
		g7.priority = 10;
		g8.priority = 14;
		g9.priority = 8;
		g10.priority = 7;
		g11.priority = 6;
		
		h.insert(g1);
		h.insert(g2);
		h.insert(g3);
		h.insert(g4);
		h.insert(g5);
		h.insert(g6);
		h.insert(g7);
		h.insert(g8);
		h.insert(g9);
		h.insert(g10);
		
		//insert tests
		assertEquals(h.toString(), "[1, 2, 3, 4, 7, 9, 10, 14, 8, 16]");
		assertEquals(h.size(), 10);
		assertEquals(h.length(), 10);
		
		assertThrows(IllegalArgumentException.class, () -> {
			Heap h = new Heap(3);
			GraphNode g = new GraphNode("1234", false);
			g.priority = -1;
			h.insert(g);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			Heap h = new Heap(3);
			GraphNode g = null;
			h.insert(g);
		});
		
		//delete test
		assertEquals(h.delete(), g2);
		assertEquals(h.toString(), "[2, 4, 3, 8, 7, 9, 10, 14, 16]");
		assertEquals(h.size(), 9);
		assertEquals(h.length(), 10);
		
		
		//test resize
		h.insert(g2);
		h.insert(g11);
		assertEquals(h.toString(), "[1, 2, 3, 8, 4, 9, 10, 14, 16, 7, 6]");
		assertEquals(h.size(), 11);
		assertEquals(h.length(), 20);
		
		//delete after resizing
		assertEquals(h.delete(), g2);
		assertEquals(h.toString(),  "[2, 4, 3, 8, 6, 9, 10, 14, 16, 7]");
		assertEquals(h.size(), 10);
		assertEquals(h.length(), 20);
		
		
		
	}

}
