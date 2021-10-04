// a very simple example of adding to and removing from a linked list 
// uses the NodeDouble class
// Beth Katz, February 2007

public class LinkedStart {

   public static void main(String[] args) {
      NodeDouble head = null;
      NodeDouble current;

      // insert a node at the front      // list contains ...
      head = new NodeDouble(42, head);   // 42

      // insert at front three more times
      head = new NodeDouble(17, head);   // 17 42
      head = new NodeDouble(75, head);   // 75 17 42
      head = new NodeDouble(-2, head);   // -2 75 17 42

      // make current point to the front
      current = head;
      //move to next node
      current = current.getLink( ); 

      // insert after current; between nodes containing 75 and 17
      current.setLink(new NodeDouble(64, current.getLink( ))); 
                                         // -2 75 64 17 42
      
      print(head);

      // remove the front by head letting go of the front node
      head = head.getLink( );            // 75 64 17 42

      // this block will remove the third item in the list
      current = head;
      // move to next node; head hangs onto front
      current = current.getLink( );
      // remove the node after the current one; remove the one with 17
      current.setLink((current.getLink( )).getLink( ));     // 75 64 42   
   }

   // print a linked list starting at head
   public static void print(NodeDouble head) {
      NodeDouble cursor;

      for (cursor = head; cursor != null; cursor = cursor.getLink( )) {
         System.out.print(cursor.getData( ) + " ");
      }
      System.out.println( );
   }
}
