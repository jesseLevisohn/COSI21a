/**
* Jesse Levisohn
* jlevisohn@brandeis.edu
* May 1, 2022
* PA3
* This class creates a minimum priority queue which implements a heap.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class FindMinPath {
	
	/**
	 * This is the main method which implements Dijkstra's algorithm. It initializes a MinPriorityQueue, Q, and gets the 
	 * GraphNode for home. It then runs a loop while Q isn't empty and the goal node hasn't been found yet. In the loop.
	 * curr is first updated to be the element with the highest priority in Q. If curr is the goal, it is saved as the goal.
	 * Otherwise, all of the neighbors of curr are explored using the checkNeightbors method which also updates the priorities
	 * and previous directions of the nodes as necessary. Once the loop is exited, the method printPath is run to print the path
	 * that wsa taken to get to the goal unless the goal node or the home node was null.
	 * @param args
	 * @throws FileNotFoundException, the method prints to an output file, answer.txt
	 */
	public static void main(String[] args) throws FileNotFoundException {
		MinPriorityQueue Q = new MinPriorityQueue();
		GraphWrapper gw = new GraphWrapper(true); 
		GraphNode home = gw.getHome(); //gets the home node
		home.priority = 0;
		Q.insert(home);
		GraphNode curr = home;
		int x = 0;
		GraphNode goal = null;
		int numNodesOnPath = -1;
		while (!Q.isEmpty() && !curr.isGoalNode()) {
			curr = Q.pullHighestPriorityElement();
			numNodesOnPath ++;
			if (curr.isGoalNode()) {
				goal = curr;
			} else {
				if (curr.hasNorth()) {
					x = curr.priority + curr.getNorthWeight();
					GraphNode neighbor = curr.getNorth();
					checkNeighbors("North", curr, neighbor, Q, x);
				}
				if (curr.hasEast()) {
					x = curr.priority + curr.getEastWeight();
					GraphNode neighbor = curr.getEast();
					checkNeighbors("East", curr, neighbor, Q, x);
				}
				if (curr.hasSouth()) {
					x = curr.priority + curr.getSouthWeight();
					GraphNode neighbor = curr.getSouth();
					checkNeighbors("South", curr, neighbor, Q, x);
				}
				if (curr.hasWest()) {
					x = curr.priority + curr.getWestWeight();
					GraphNode neighbor = curr.getWest();
					checkNeighbors("West", curr, neighbor, Q, x);
				}
			}
		}
		PrintStream out = new PrintStream(new File("answer.txt")); //create print stream object
		if (goal == null || home == null) { //if the home or goal is null, no path exists
			out.println("No path exists");
		} else {
			String s = printPath(goal, home, numNodesOnPath);
			out.println(s);
		}
	}
	
	/**
	 * The checkNeighbors method takes in a GraphNode, curr, and it's neighbor node and checks whether it is in Q
	 * and whether the path to the neighbor through curr is faster than any previous path. If it is, priority, previous 
	 * node, and previous distance are updated. 
	 * @param s, a string which is the direction that the neighbor is from the node.
	 * @param curr, a GraphNode, the current one whose neighbor's are being explored.
	 * @param neighbor, a GraphNode, the neighbor of curr that is being explored.
	 * @param Q, the MinPriorityQueue
	 * @param x, the priority of curr plus the weight of the path to the neighbor node.
	 */
	public static void checkNeighbors(String s, GraphNode curr, GraphNode neighbor, MinPriorityQueue Q, int x) {
		if (!Q.getHeap().getHashMap().hasKey(neighbor)) { //if neighbor is not in Q
			neighbor.priority = x;
			neighbor.previousNode = curr;
			neighbor.previousDirection = s;
			Q.insert(neighbor); //insert the node
		} else if(Q.getHeap().getHashMap().hasKey(neighbor) && x < neighbor.priority) { //if neighbor is Q but the new path is faster
			neighbor.priority = x;
			neighbor.previousNode = curr;
			neighbor.previousDirection = s;
			Q.rebalance(neighbor); //rebalance Q after the priority of the node has been changed.
		}
	}
	
	/**
	 * This method prints the path from the home node to the goal node. It creates an array that is the size of the 
	 * number of directions and fills it with the directions to the goal from back to front. It then adds the elements
	 * of the array to a string from front to back.
	 * @param curr, a GraphNode, initially the goal node, and then each node back to the home node.
	 * @param home, a GraphNode, the home GraphNode
	 * @param numNodesOnPath, an integer, the number of steps that it took to get to the goal.
	 * @return s, a string which has all of the directions to the goal from the home.
	 */
	public static String printPath(GraphNode curr, GraphNode home, int numNodesOnPath) {
		String s = "";
		int numDirections = numNodesOnPath;
		String[] a = new String[numNodesOnPath];
		while(!curr.equals(home)) {
			numDirections --;
			a[numDirections] = curr.previousDirection;
			curr = curr.previousNode;
		}
		for (int i = 0; i < numNodesOnPath; i++) {
			s += a[i] + "\n";
		}
		return s;
	}
}
