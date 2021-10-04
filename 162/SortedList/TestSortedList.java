// Test the SortedList class
// By __________, March 2018

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestSortedList {

	@Test public void a_check42( ) {
       		SortedList list = new SortedList( );
		list.insert(42);
		assertTrue("Incorrect value for one item",
				list.toString( ).equals("42.0 "));
	}
	@Test public void b_checkNegative( ) {
   		SortedList list = new SortedList( );
	list.insert(-1);
	list.insert(5);
	list.insert(6);
	list.insert(-1);
	list.insert(-1);
	list.insert(-3);
	list.insert(100);
	list.toString();
	assertTrue("Incorrect values",
			list.toString( ).equals("-3.0 -1.0 -1.0 -1.0 5.0 6.0 100.0 "));
	} 
	@Test public void c_checkDecimal( ) {
   		SortedList list = new SortedList( );
	list.insert(1.3);
	list.insert(1.2);
	list.insert(1.1);
	list.insert(1.2);
	list.insert(-1.4);
	assertTrue("Incorrect values",
			list.toString( ).equals("-1.4 1.1 1.2 1.2 1.3 "));
	}
	@Test public void d_checkEnd( ) {
   		SortedList list = new SortedList( );
	list.insert(10);
	list.insert(9);
	list.insert(8);
	list.insert(7);
	list.insert(6);
	list.insert(5);
	list.insert(4);
	list.insert(3);
	list.insert(2);
	list.insert(1);
	list.insert(11);
	assertTrue("Incorrect values",
			list.toString( ).equals("1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0 11.0 "));
	}
	@Test public void e_checkStart( ) {
   		SortedList list = new SortedList( );
	list.insert(10);
	list.insert(9);
	list.insert(8);
	list.insert(7);
	list.insert(6);
	list.insert(5);
	list.insert(4);
	list.insert(3);
	list.insert(2);
	list.insert(1);
	list.insert(0);
	list.insert(0.1);
	list.insert(-100);
	list.insert(0.1);
	assertTrue("Incorrect values",
			list.toString( ).equals("-100.0 0.0 0.1 0.1 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0 "));
	}

}
