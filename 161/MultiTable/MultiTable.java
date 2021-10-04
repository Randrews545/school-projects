/*******************************************************************************
 * 
 * MultiTable program
 * 
 * Purpose:  This program prints out a multiplication table
 * 
 * @author Ryan Andrews
 *
 * @version 1.0 (September 25, 2017)      
 * 
 ******************************************************************************/
public class MultiTable {
	
	/**=========================================================================
	 * CLASS METHODS
	 * 
	 * Program methods are listed below in alphabetical order.
	 *========================================================================*/
	
	/**
	 * Prints out the header for the multiplication table
	 */
public static void printHeader(){
	System.out.print("   #");
	for (int i=0; i<=10; i++){
		if (i<=1){
		System.out.print("  ");
		System.out.print(i);
		}
		else if(i>=2){
			System.out.print("   ");
			System.out.print(i);
		}
	}
	System.out.println();
	System.out.println("###############################################");
}
/**
 * Creates and prints out the multiplication table
 */
public static void printTable(){
	for (int i=0; i<=10; i++) {
	    for (int j=0; j<=10; j++) {
	    	if (j==0 && i<=9) {
	    	System.out.print(" ");
	    	System.out.print(i);
	    	System.out.print(" ");
	    	System.out.print("#");
	        System.out.printf("%3d ", i*j);
	    }
	    	else if (j==0 && i==10) {
	    		System.out.print(i);
		    	System.out.print(" ");
		    	System.out.print("#");
	    		System.out.printf("%3d ", i*j);
	    	}
	    	
	    	else {
	    		System.out.printf("%3d ", i*j);
	    	}
	    }
	    System.out.println();
	}
}
/**=========================================================================
 * CLASS main
 * 
 * @param args - The command-line arguments passed to the running program.
 *========================================================================*/
	public static void main(String[] args) {
		printHeader();
		printTable();
	}
}
