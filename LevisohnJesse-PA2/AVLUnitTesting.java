import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
* Test class for AVLPlayerNodes.
*
* @author Jesse Levisohn
* jlevisohn@brandeis.edu
* May 17, 2022
* COSI 21A PA2
*/
class AVLUnitTesting {

	private Player Mike;
	private Player Sandra;
	private Player Eric;
	private AVLPlayerNode idTree;
	private AVLPlayerNode eloTree;
	
	
	public void setup() {
		this.Mike = new Player("Mike", 1, 2400);
		this.Sandra = new Player("Sandra", 2, 2300);
		this.Eric = new Player("Eric", 3, 2200);
		this.idTree = new AVLPlayerNode();
		this.eloTree = new AVLPlayerNode();
	}
	
	public void insertSetUp() {
		idTree.insert(Mike, 1);
		idTree.insert(Sandra, 2);
		idTree.insert(Eric, 3);
	}
	
	@Test
	void testConstructor() {
		setup();
		//test ID node
		AVLPlayerNode MikeID = new AVLPlayerNode(Mike, 1);
	
		assertEquals(MikeID.getValue(), 1);
		assertEquals(MikeID.getParent(), null);
		assertEquals(MikeID.getLeftChild(), null);
		assertEquals(MikeID.getRightChild(), null);
		assertEquals(MikeID.getRightWeight(), 0);
		assertEquals(MikeID.getBalanceFactor(), 0);
		assertEquals(MikeID.getRoot(), null);
		
		//test Elo node
		AVLPlayerNode MikeElo = new AVLPlayerNode(Mike, 2400);
		
		assertEquals(MikeElo.getValue(), 2400);
		assertEquals(MikeElo.getParent(), null);
		assertEquals(MikeElo.getLeftChild(), null);
		assertEquals(MikeElo.getRightChild(), null);
		assertEquals(MikeElo.getRightWeight(), 0);
		assertEquals(MikeElo.getBalanceFactor(), 0);
		assertEquals(MikeElo.getRoot(), null);
		
		//test null node
		boolean thrown = false;
		try {
			AVLPlayerNode test = new AVLPlayerNode(null, 0);
		} catch(Exception e) {
			if (e instanceof IllegalArgumentException) {
				thrown = true;
			}
		}
		assertTrue(thrown);
	}
	
	@Test
	void testInsert() {
		setup();
		
		//insert into empty tree
		idTree.insert(Mike, 1);
		AVLPlayerNode root =  idTree.getRoot();
		
		assertEquals(1.0, root.getValue());
		assertEquals(0, root.getBalanceFactor());
		assertEquals(0, root.getRightWeight());
		assertEquals(1, root.getRank(1));
		assertEquals(Mike, idTree.getPlayer(1));
		
		//insert into a not empty tree
		idTree.insert(Sandra, 2);
		AVLPlayerNode SandraID = idTree.getRoot().getRightChild();
		
		assertEquals(-1, root.getBalanceFactor());
		assertEquals(1, root.getRightWeight());
		assertEquals(2, idTree.getRank(1));
		assertEquals(1, idTree.getRank(2));
		assertEquals(0, SandraID.getRightWeight());
		assertEquals(0, SandraID.getBalanceFactor());
		assertTrue(Mike == idTree.getPlayer(1));
		assertTrue(Sandra == idTree.getPlayer(2));
	}
	
	@Test
	void testInsertWithRebalance1() {
		setup();
		
		
		idTree.insert(Mike, 1);
		idTree.insert(Sandra, 2);
		idTree.insert(Eric, 3);
		
		AVLPlayerNode Mike =  idTree.getRoot().getLeftChild();
		AVLPlayerNode Sandra =  idTree.getRoot();
		AVLPlayerNode Eric = idTree.getRoot().getRightChild();
		
		assertEquals(Sandra, idTree.getRoot());
		assertEquals(0, Mike.getBalanceFactor());
		assertEquals(0, Mike.getRightWeight());
		assertEquals(3, idTree.getRank(1));
		assertEquals(2, idTree.getRank(2));
		assertEquals(1, Sandra.getRightWeight());
		assertEquals(0, Sandra.getBalanceFactor());
		assertEquals(1, idTree.getRank(3));
		assertEquals(0, Eric.getRightWeight());
		assertEquals(0, Eric.getBalanceFactor());
		assertEquals("((Mike)Sandra(Eric))", idTree.treeString());
	}
	
	@Test
	void testDeleteLeaf() {
		setup();
		insertSetUp();
		
		idTree.delete(1);
		AVLPlayerNode Sandra =  idTree.getRoot();
		AVLPlayerNode Eric = idTree.getRoot().getRightChild();
		
		assertEquals(-1, Sandra.getBalanceFactor());
		assertEquals(idTree.getRoot().getLeftChild(), null);
		
		idTree.delete(3);
		assertEquals(0, Sandra.getBalanceFactor());
		assertEquals(idTree.getRoot().getRightChild(), null);
	}
	
	@Test
	void testDeleteRoot() {
		setup();
		insertSetUp();
		
		idTree.delete(2);
		assertEquals(3, idTree.getRoot().getValue());
		assertEquals(1, idTree.getRoot().getBalanceFactor());
	}

}
