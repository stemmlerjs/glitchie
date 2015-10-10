package glitchart;

/**BST Program
*
* @author Khalil Stemmler
* October 2nd, 2014
* This Node class is used within the BST class and as elements for the LinkStack class. By default, the leftTag and rightTag are false.
* This allows us to create a normal BST and a Threaded BST where the default is for a normal BST.
*/

public class Node {
	
	//INSTANCE VARIABLES
	Node stNext; //used for Stack implementations
	byte[] array;
	
	//CONSTRUCTOR
	Node(byte[] array, Node stNext){
		this.stNext = stNext;   
		this.array = array;
	}
	
	/** This method sets the Node object that call it's stNext pointer to stNext: the parameter of this method.
     ** @param Node     a Node where we point "this" to. 
     **/
	
	public void setNext(Node stNext){
		this.stNext = stNext;
	}
	
	/** This method returns the Node object that calls it's stNext item.
     ** @return Node	this' stNext pointer  
     **/
	
	public Node getNext(){
		return stNext;
	}
}
