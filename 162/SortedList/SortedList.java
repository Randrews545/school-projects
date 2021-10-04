/**************************************************************************
* SortedList creates a linked list of nodes that are sorted by their data values.
*
* @author Ryan Andrews 
*
* @version
*   March 2018
***************************************************************************/

public class SortedList {

	public NodeDouble listHead;
	
	/**
	* Initializes a linked list with a head set to null.
	* @param - none
	* @postcondition
	*   The head of this list begins at null.
	**/
	public SortedList() {
		listHead = null;
	}

	/**
	* Method that inserts a new node into the linked list at the appropriate spot for the nodes value.
	* @param num
	*   the data value of the new node
	* @postcondition
	*   A new node is created at the appropriate point
	**/
	
	public void insert(double num) {
		NodeDouble preceding = null;
		NodeDouble nextLink = null;
		if(listHead == null) {
			listHead = new NodeDouble(num, listHead);
		}
		else if(getPrecedingNode(num) == null) {
			listHead = new NodeDouble(num, listHead);
		}
		else if(getPrecedingNode(num) != null){
			preceding = getPrecedingNode(num);
			nextLink = preceding;
			nextLink.setLink(new NodeDouble(num, nextLink.getLink()));
		}
	}
	/**
	* Method that determines the appropriate place for the new node in the linked list
	* @param value
	*   the data value of the new node
	* @postcondition
	*   A new node is created at the appropriate point
	* @return
	*   Null if value is smaller than all of the data in the linked list or precursor if it is larger than at least one.
	**/
	
	private NodeDouble getPrecedingNode(double value) {
		NodeDouble cursor = null;
		NodeDouble precursor;
			
			for (precursor = listHead; precursor != null; precursor = precursor.getLink( )) {
				cursor = precursor.getLink();
				if(value >= precursor.getData() && cursor != null && value <= cursor.getData()){
					return(precursor);
				}
				else if(value >= precursor.getData() && cursor == null) {
					return(precursor);
				}	
			}			
		return(null);
	}
	/**
	* Creates a string of all the data in the linked list
	* @param - none
	* @return
	* 	The string of all of the data values in the linked list
	**/   
	 public String toString() {
	      String printList = "";
	      NodeDouble cursor;
	      double data;
	      for (cursor = listHead; cursor != null; cursor = cursor.getLink( )) {
	    	  data = cursor.getData();
	    	  printList += data;
	    	  printList += " ";
	      }
	      return(printList);
	   }
}
