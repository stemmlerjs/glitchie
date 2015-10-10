package glitchart;

/**Glitch-Art
*
* @author Khalil Stemmler
* October 2nd, 2014
* The LinkStack class is used in the Glitch-Pond project as a means to keep changes made to the picture in a stack data structure. 
* This is done so that we can revert our changes to the glitched picture by seeking the previous picture that is represented
* by the byte[]. The LinkStack class implements the Stacks interface.
* 
* @see LinkStack
* @see stackTraversal()
* @see Node
*/

public class LinkStack {
	
	//INSTANCE VARIABLES
	private Node top;
	int size = 0;
	
	//CONSTRUCTOR
	public LinkStack(){
		size = 0;
		top = null;
	}

	/** This method sets the currentNode object within the parameter to being the top of the stack, increments the size of the stack, and sets the next
	 ** value in the stack to be the old top object.
     ** @param Node     the Node object that we set as the new top item and push to the top of the stack
     **/
	
	public void push(Node currentNode) {
		currentNode.setNext(top);
		top = currentNode;
		size++;
	}

	/** This method removes the item on the top of the LinkStack and returns it. Throws an UnderflowException if the LinkStack is null.
     ** @return Node 	the popped Node
     **/
	
	public Node topAndPop() {
		Node i;
		if(top == null){
			throw new UnderflowException();
		} else {
			i = top;
			top = top.getNext();
			size--;
			return i;
		}
	}
	
	/** This method returns the truth value of the top of the LinkStack being null.
     ** @param Boolean		the truth value of top being null
     **/

	public boolean isEmpty() {
		return top == null;
	}


}
