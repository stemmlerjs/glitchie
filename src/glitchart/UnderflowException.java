package glitchart;

/**BST Program
*
* @author Khalil Stemmler
* October 2nd, 2014
* This class' purpose is used to instantiate an Exception Object used in the LinkStack topAndPop() method.
* The class extends RuntimeException so that a try/catch block is not required.
*/

public class UnderflowException extends RuntimeException {

	public UnderflowException(){
		System.out.println("Can't Undo");
	}
}
