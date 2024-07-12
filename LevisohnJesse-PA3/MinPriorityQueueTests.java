import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MinPriorityQueueTests {
	
	@Test
	void testConstructor() {
		MinPriorityQueue Q = new MinPriorityQueue();
		MinPriorityQueue Q2 = new MinPriorityQueue(5);
		
		assertEquals(20, Q.getLength());
		assertEquals(0, Q.size());
		assertEquals(5, Q2.getLength());
		assertEquals(0, Q2.size());
		assertTrue(Q.isEmpty());
		assertTrue(Q2.isEmpty());
		
		assertThrows(IllegalArgumentException.class, () -> {
			MinPriorityQueue Q3 = new MinPriorityQueue(-1);;
		});
	}
	
	@Test
	void testInsert() {
		MinPriorityQueue Q = new MinPriorityQueue();
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
		
		Q.insert(g1);
		
		assertFalse(Q.isEmpty());
		assertEquals(g1, Q.pullHighestPriorityElement());
		assertTrue(Q.isEmpty());
		
		Q.insert(g1);
		Q.insert(g2);
		Q.insert(g3);
		Q.insert(g4);
		Q.insert(g5);
		Q.insert(g6);
		Q.insert(g7);
		Q.insert(g8);
		Q.insert(g9);
		Q.insert(g10);
		
		assertEquals(Q.getHeap().toString(), "[1, 2, 3, 4, 7, 9, 10, 14, 8, 16]");
		assertEquals(Q.size(), 10);
		assertEquals(Q.getLength(), 20);
		
		assertEquals(g2, Q.pullHighestPriorityElement());
		assertEquals(Q.size(), 9);
		assertEquals(Q.getLength(), 20);
		
		assertEquals(g4.priority, Q.pullHighestPriorityElement().priority);
		assertEquals(Q.size(), 8);
		
		assertEquals(g3, Q.pullHighestPriorityElement());
		assertEquals(Q.size(), 7);
	}
	
	@Test
	void testReblance() {
		MinPriorityQueue Q = new MinPriorityQueue();
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
		
		Q.insert(g1);
		Q.insert(g2);
		Q.insert(g3);
		Q.insert(g4);
		Q.insert(g5);
		Q.insert(g6);
		Q.insert(g7);
		Q.insert(g8);
		Q.insert(g9);
		Q.insert(g10);
		
		g6.priority = 5;
		Q.rebalance(g6);
		assertEquals(Q.getHeap().toString(), "[1, 2, 3, 4, 7, 5, 10, 14, 8, 16]");
		
		g5.priority = 6;
		Q.rebalance(g5);
		assertEquals(Q.getHeap().toString(), "[1, 2, 3, 4, 6, 5, 10, 14, 8, 7]");
		
		
		g2.priority = 20;
		Q.rebalanceDown(g2);
		assertEquals(Q.getHeap().toString(), "[2, 4, 3, 8, 6, 5, 10, 14, 20, 7]");
		
		g2.priority = 1;
		Q.rebalance(g2);
		assertEquals(Q.getHeap().toString(), "[1, 2, 3, 4, 6, 5, 10, 14, 8, 7]");
		
		
	}

}
