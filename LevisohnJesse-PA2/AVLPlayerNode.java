/**
 * Your code goes in this file
 * fill in the empty methods to allow for the required
 * operations. You can add any fields or methods you want
 * to help in your implementations.
 */

/**
* Provides implementation of an AVL tree and AVL tree nodes with insertion and deletion with rebalancing.
*
* @author Jesse Levisohn
* jlevisohn@brandeis.edu
* May 17, 2022
* COSI 21A PA2
*/


public class AVLPlayerNode{
    private Player data;
    private double value;
    private AVLPlayerNode parent;
    private AVLPlayerNode leftChild;
    private AVLPlayerNode rightChild;
    private int rightWeight;
    private int balanceFactor;
    private AVLPlayerNode root;
    private int height;
    
    /**
     * This is the overloaded constructor method with no parameters.
     * Used to construct the node which holds the tree.
     */
    public AVLPlayerNode() {
    	this.data = null;
        this.value = -1;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
        this.rightWeight = 0;
        this.balanceFactor = 0;
        this.root = null;
        this.height = 0;
    }
    
    
    /**
     * This is the constructor for an AVLPlayerNode with constructors.
     * This method is used to construct nodes to add to the tree when players are inserted.
     * @param data, a player
     * @param value, a value
     */
    public AVLPlayerNode(Player data, double value){
        if (data == null) {
        	throw new IllegalArgumentException();
        }
    	this.data = data;
        this.value = value;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
        this.rightWeight = 0;
        this.balanceFactor = 0;
        this.root = null;
        this.height = 0;
    }
    
    //This should return the new root of the tree
    //make sure to update the balance factor and right weight
    //and use rotations to maintain AVL condition
    /**
     * The insert method inserts a node into a AVL tree.
     * The insert method creates a tree at the node that it is called on. 
     * Therefore every insert call must be made on the same node.
     * First, the tree is traversed to find the spot to insert the node. 
     * Then, the root is updated if the insertion is into an empty tree.
     * FInally, a path is traced from the node back to the root and rebalancing is performed as needed.
     * O(log n)
     * @param newGuy, a player to insert into the tree
     * @param value, the value to order the node in the binary search tree, either an elo rating or id 
     * @return, the new root of the tree after insertion
     */
    public AVLPlayerNode insert(Player newGuy, double value){
    	AVLPlayerNode z = new AVLPlayerNode(newGuy, value);
    	AVLPlayerNode v = null;
    	AVLPlayerNode w = this.root;
    	
    	// finds the location to insert the new node
    	while (w != null) {
    		v = w;
    		w.setHeight(1);
    		if (value < w.getValue()) {
    			w.setBalanceFactor(1);
    			w = w.getLeftChild();
    		}
    		else {
    			w.setRightWeight(1);
    			w.setBalanceFactor(-1);
    			w = w.getRightChild();
    		}
     	}
    	z.setParent(v);
    	
    	// attaches the new node to the tree
    	if (v == null) {
    		this.root = z;
    		z.setRoot(z);
    	} else {
    		if (value < v.getValue()) {
    			v.leftChild = z;
    		} else {
    			v.rightChild = z;
    		}
    		z.setRoot(v.getRoot());
    	}
    	
    	//trace the path back to the root doing rotations as needed
    	while (z.getParent() != null) {
    		z = z.getParent();
    		if (z.balanceFactor > 1) {	
    			if (z.getRightChild() != null && z.getRightChild().getBalanceFactor() < 0) {
    				z.rotateLeft();
    			}
    			z.rotateRight();
    			this.root = z.getRoot();
    		} else if (z.balanceFactor < -1) {
    			if (z.getLeftChild() != null && z.getLeftChild().getBalanceFactor() > 0) {
    				z.rotateRight();
    			}
    			z.rotateLeft();
    			this.root = z.getRoot();
    		}
    	}
    	return this.root;
    }
    
    
    /**
     * This method sets the height of a node. 
     * Adds the parameter i to the previous height.
     * @param i, an integer
     */
    private void setHeight(int i) {
		this.height += i;
		
	}
    
	
    //This should return the new root of the tree
    //remember to update the right weight
    
    /**
     * This is the delete method for AVL trees with rebalancing. 
     * First, the node that needs to be deleted is found.
     * Then the method checks the number of children that the node has and finds the nodes successor if it has two children.
     * The parent and child fields of the parent and child of y are updated to skip over y.
     * If y was the successor of z instead of the node itself, the data fields of z are switched to y's.
     * Then the rebalancing occurs. 
     * A path is traced back to the root. 
     * At each step, the right weight and balance factor of the parent are updated to reflect the deleted node.
     * If the parent node is then unbalanced, the necessary rebalancing operations are performed.
     * O(log n)
     * @param value, the value of the node that is to be deleted.
     * @return, the new root of the tree.
     */
    public AVLPlayerNode delete(double value){
    	//deletes node
    	AVLPlayerNode z = this.root;
    	if (value == this.getValue()) {
    		z = this;
    	}
        while (z.getValue() != value) { //finds node to delete
        	if (z.getValue() > value) {
        		z = z.getLeftChild();
        	} else {
        		z = z.getRightChild();
        	}
        }
    	AVLPlayerNode y = null; 
    	AVLPlayerNode x = null;
    	if (z.getLeftChild() == null || z.getRightChild() == null) { // if the node has zero or 1 children - case 1 or 2
    		y = z;
    	} else {
    		y = treeSuccesor(z); //if the node has two children find its successor
    	}
    	if (y.getLeftChild() != null) { // y will only have at most one child
    		x = y.getLeftChild();
    	} else {
    		x = y.getRightChild();
    	}
    	if (x != null) {
    		x.setParent(y.getParent());
    	}
    	if (y.getParent() == null) { //if y is the root of the tree
    		this.root = x;
    	} else {
    		if (y.equals(y.getParent().getLeftChild())) { //if y is a left child
    			if (y.parent.equals(this.root)) {
        			this.root.setBalanceFactor(-1);
            		y.setHeight(-1);
        			this.root.setNewHeight();
        			y.setHeight(1);
        		}
    			y.getParent().setLeftChild(x);
    		} else {
    			if (y.parent.equals(this.root)) {
        			this.root.setBalanceFactor(1);
            		y.setHeight(-1);
        			this.root.setNewHeight();
        			y.setHeight(1);
        		}
    			y.getParent().setRightChild(x);
    		}
    	}
    	if (!y.equals(z)) {
    		z.setValue(y.getValue());
    		z.setData(y.getData());
    	}
    	// re-balancing
    	x = y.getParent();
    	while (x.getParent() != null) { //trace path back to root.
    		AVLPlayerNode parent = x.getParent();
    		if (x.equals(parent.getRightChild())){ //if the deleted node was in the right subtree
    			parent.setRightWeight(-1); //subtract 1 from the right weight
    			parent.setBalanceFactor(1); // balance is left minus right so a double negative makes a positive
    			parent.setNewHeight();
    			if (parent.getBalanceFactor() > 1 || parent.getBalanceFactor() < -1) {
    				z = parent.getLeftChild(); // z is the root of v left subtree
        			if (z.getBalanceFactor() == -1) { //if z is exactly -1 do a left-right rotation
        				parent.rotateLeft();
        			} 
        			parent.rotateRight(); //otherwise just rotate right
    			}
    		} else { //if the deleted node is in the left subtree
    			parent.setBalanceFactor(-1); // subtract one from the balance factor
    			parent.setNewHeight();
    			if (parent.getBalanceFactor() > 1 || parent.getBalanceFactor() < -1) { //same but opposite as for the previous case
    				z = parent.getRightChild();
        			if (z.getBalanceFactor() == 1) {
        				parent.rotateRight();
        			}
        			parent.rotateLeft();
    			}
    		}
    		x = x.getParent();
    	}
    	return this.root;
    }
    
    
    /**
     * This method sets the data field of a node.
     * Used in delete to swap the data of a node and its successor.
     * @param data, a Player object.
     */
    private void setData(Player data) {
		this.data = data;
	}
    
    /**
     * This a getter method for the data field
     * @return
     */
	private Player getData() {
		return this.data;
	}
	
	/**
	 * This is a setter method for the value field.
	 * @param value, a double which is the new value 
	 */
	private void setValue(double value) {
		this.value = value;
		
	}
	
	/**
	 * This method finds the successor node in the tree.
	 * That is the node that is the least amount bigger than parameter.
	 * O(log n)
	 * @param v, a node whose successor is to be found
	 * @return, the successor node
	 */
	private AVLPlayerNode treeSuccesor(AVLPlayerNode v) {
    	if (v.getRightChild() != null) {
    		return minimum(v.getRightChild());
    	} else {
    		AVLPlayerNode w = v.getParent();
    		while (w != null && v.equals(w.getRightChild())) {
    			v = w;
    			w = w.parent;
    		}
    		return w;
    	}
    	
    }
	
	/**
	 * Finds the smallest node in the subtree of v.
	 * @param v, a node of the tree, a root of the subtree that the method will find the minimum of.
	 * @return the minimum node of the subtree.
	 */
    
    private AVLPlayerNode minimum(AVLPlayerNode v) {
    	while (v.getLeftChild() != null) {
    		v = v.getLeftChild();
    	}
    	return v;
    }
    
    //remember to maintain rightWeight
    
    /**
     * This method rotates an unbalanced tree right around a node.
     * There are two key nodes involved in the rotation, the node and its left child.
     * The balance factor, right weight, and heights of the nodes are updated in the method.
     * O(1)
     */
    private void rotateRight(){
    	AVLPlayerNode y = this.leftChild;
    	this.leftChild = y.getRightChild();
    	if (y.getRightChild() != null) {
    		y.getRightChild().setParent(this);
    	} 
    	y.setParent(this.parent);
    	if (this.parent == null) {
    		this.root = y;
    	} else if (this == this.parent.getRightChild()) {
    		this.parent.setRightChild(y);
    	} else {
    		this.parent.setLeftChild(y);
    	}
    	y.setRightChild(this);
    	this.parent = y;
    	y.setRightWeight(1 + this.rightWeight);
    	setNewHeight();
    	y.setNewHeight();
    	bf();
    	y.bf();
    }

    //remember to maintain rightWeight
    
    /**
     * This method rotates an unbalanced tree left around a node.
     * It is symetric to rotateRight.
     * O(1)
     */
    private void rotateLeft(){
    	AVLPlayerNode y = this.rightChild;
    	this.rightChild = y.getLeftChild();
    	if (y.getLeftChild() != null) {
    		y.getLeftChild().setParent(this);
    		this.rightWeight = 1 + this.rightChild.getRightWeight();
    	} else {
    		this.rightWeight = 0;
    	}
    	y.setParent(this.parent);
    	if (this.parent == null) {
    		this.root = y;
    	} else if (this == this.parent.getLeftChild()) {
    		this.parent.setLeftChild(y);
    	} else {
    		this.parent.setRightChild(y);
    	}
    	y.setLeftChild(this);
    	this.parent = y;
    	setNewHeight();
    	y.setNewHeight();
    	bf();
    	y.bf();
    }
    
    
    /**
     * Getter method for the height field.
     * @return
     */
    public int getHeight() {
    	return this.height;
    }
    
    /**
     * Sets the height of a node as the max of the height of the left child and the height of the right child.
     */
    public void setNewHeight() {
    	int l = 0;
    	int r = 0;
    	if (this.leftChild != null) {
    		l = this.leftChild.getHeight();
    	} else {
    		l = -1;
    	}
    	if (this.rightChild != null) {
    		r = this.rightChild.getHeight();
    	} else {
    		r = -1;
    	} 
    	if (l > r) {
    		this.height = l;
    	} else {
    		this.height = r;
    	}
    	if (this.height == -1) {
    		this.height = 0;
    	}
    }
    
    
    /**
     * Sets the balance factor of a node after rotation.
     * Calculated as the height of the left child minus the height of the right child.
     */
    public void bf() {
    	int l = 0;
    	int r = 0;
    	if (this.leftChild != null) {
    		l = this.leftChild.getHeight();
    	} else {
    		l = -1;
    	}
    	if (this.rightChild != null) {
    		r = this.rightChild.getHeight();
    	} else {
    		r = -1;
    	}
    	this.balanceFactor = l - r;
    }
    
	
    /**
     * Finds the player node with the given value.
     * O (log n)
     * @param value, the value of the player being searched for.
     * @return, the player at the node that corresponds to that value.
     */
    //this should return the Player object stored in the node with this.value == value
    public Player getPlayer(double value){
    	if (root == null) {
    		return null;
    	}
    	AVLPlayerNode curr = root;
    	while (curr != null && value != curr.value) {
    		if (value > curr.value) {
    			curr = curr.getRightChild();
    		} else {
    			curr = curr.getLeftChild();
    		}
    	}	
    	if (value == curr.value) {
    		return curr.data;
    	}
    	return null;
    }
    
    //this should return the rank of the node with this.value == value
    
    
    /**
     * Calculates the rank of the node with the given value.
     * The rank of the root is one more than the number of node in its right subtree.
     * If the value is greater than the root, the rank is equal to the rank in the right subtree computed 
     * recursively. If the value is less than the root, the rank is the rank in the left subtree plus the number 
     * of node in the right subtree plus one for the root.
     * This method calls the recursive search method if the value is not the root.
     * O (log n)
     * @param value, the value corresponding to the node whose rank should computed.
     * @return, the rank of the node
     */
    public int getRank(double value){
    	if (this.root == null) {
    		throw new IllegalArgumentException();
    	} 
    	if (this.root.getValue() == value) {
    		return 1 + this.root.rightWeight;
    	}
    	else {
    		return this.root.recursiveSearch(value);
    	}
    }
    
    
    /**
     * Recursively computes the rank of a value.
     * @param value, the value whose rank should be computed.
     * @return the rank of that value
     */
    public int recursiveSearch(double value) {
    	if (this.value == value) {
    		return 1 + this.rightWeight;
    	} else {
    		if (value > this.value) {
    			return this.rightChild.recursiveSearch(value);
    		} else {
    			return 1 + this.rightWeight + this.leftChild.recursiveSearch(value);
    		}
    	}
    }

    
    //this should return the tree of names with parentheses separating subtrees
    //eg "((bob)alice(bill))"
    /**
     * This method returns a string representation of the entire tree.
     * The string contains the name of the player at each node with parentheses separating subtrees.
     * O(n)
     * @return, a string representation of the tree.
     */
    public String treeString(){
    	return this.root.inOrder();
    }
    
    /**
     * This method traverses the tree in order.
     * It returns strings as it goes to create the tree string.
     * @return, a string with the name of a node or a parentheses.
     */
    public String inOrder() {
    	if (this.leftChild == null && this.rightChild == null) {
    		return "(" + this.data.getName() + ")";
    	} else {
    		if (this.leftChild != null && this.rightChild != null) {
        		return "(" + this.leftChild.inOrder() + this.data.getName() + this.rightChild.inOrder() + ")";
        	} 
    		if (this.leftChild == null) {
    			return "(" + this.data.getName() + this.rightChild.inOrder() + ")";
    		} else {
    			return "(" + this.leftChild.inOrder() + this.data.getName() + ")";
    		}
    	}
    }

    /**
     * Traverses the array in reverse order and prints out a score board.
     * O(n)
     * @return a string with each players name, id, and score.
     */
    //this should return a formatted scoreboard in descending order of value
    //see example printout in the pdf for the command L
    public String scoreboard(){
    	//TOD
    	return "NAME	 	ID   SCORE" + "\n" + this.root.reverseInOrder();
    }
    
    /**
     * traverses the array in reverse order to create the score board.
     * returns a string for each node as it goes.
     * @return, a string score board.
     */
    
    public String reverseInOrder() {
    	if (this.leftChild == null && this.rightChild == null) {
    		return this.data.getName() + "		" + this.data.getID() + " " + this.data.getELO() + "\n";
    	} else {
    		if (this.leftChild != null && this.rightChild != null) {
        		return this.rightChild.reverseInOrder() + this.data.getName() + "		" + this.data.getID() + " " + this.data.getELO() + "\n" + this.leftChild.reverseInOrder() + ")";
        	} 
    		if (this.rightChild == null) {
    			return this.data.getName() + "		" + this.data.getID() + " " + this.data.getELO() + "\n" + this.leftChild.reverseInOrder(); 
    		} else {
    			return this.rightChild.reverseInOrder() + this.data.getName() + "		" + this.data.getID() + " " + this.data.getELO() + "\n"; 
    		}
    	}
    }
    
    
    /**
     * Getter method for the value field
     * @return, a double, value
     */
    public double getValue() {
    	return this.value;
    }
    
    /**
     * Gets the root of the tree.
     * @return, a node, the root of the tree.
     */
    public AVLPlayerNode getRoot() {
    	return this.root;
    }
    
    
    /**
     * Setter for the root field.
     * @param root, a node which is to be the new root of the tree.
     */
    public void setRoot(AVLPlayerNode root) {
    	this.root = root;
    }
    
    /**
     * Setter for Balance Factor.
     * Adds the parameter to the balance factor.
     * @param num, an integer.
     */
    public void setBalanceFactor(int num) {
    	this.balanceFactor += num;
    }
    
    /**
     * Getter method for the field balance factor.
     * @return
     */
    public int getBalanceFactor() {
    	return this.balanceFactor;
    }
    
    /**
     * Getter method for the parent field.
     * @return, a node, parent
     */
    public AVLPlayerNode getParent() {
    	return this.parent;
    }
    
    /**
     * adds the parameter, num, to the right weight field.
     * @param num, an integer.
     */
    public void setRightWeight(int num) {
    	this.rightWeight += num;
    }
    
    /**
     * Getter method for the right child field.
     * @return, the right child of the node.
     */
    public AVLPlayerNode getRightChild() {
    	return this.rightChild;
    }
    
    /**
     * Getter method for the left child.
     * @return, the left child of the node.
     */
    public AVLPlayerNode getLeftChild() {
    	return this.leftChild;
    }
	
    /**
     * Setter method for the parent field.
     * @param newParent, a node
     */
    public void setParent(AVLPlayerNode newParent) {
    	this.parent = newParent;
    }
    
    /**
     * setter method for right child.
     * @param right, a node.
     */
    public void setRightChild(AVLPlayerNode right) {
    	this.rightChild = right;
    }
    
    /**
     * setter method for the left child.
     * @param left, a node.
     */
    public void setLeftChild(AVLPlayerNode left) {
    	this.leftChild = left;
    }
    
    /**
     * Getter method for right weight.
     * @return, the field right weight.
     */
    public int getRightWeight() {
    	return this.rightWeight;
    }
}
    
	
