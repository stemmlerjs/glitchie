package glitchart;

/**Glitch-Pond
*
* @author Khalil Stemmler
* October 2nd, 2014
* This Node class is used as an object in the LinkStack. In the Glitch-Pond project, each Node keeps a byte[] array that holds the
* binary data for an image.
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
