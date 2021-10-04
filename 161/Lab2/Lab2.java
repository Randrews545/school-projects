/*******************************************************************************
 * 
 * Lab2 program
 * 
 * Purpose:  This program asks the user for a specific number of seconds and then calculates the amount of days, hours, and minutes that are equal to the number of seconds.
 * 
 * @author Ryan Andrews
 *
 * @version 1.0 (September 11, 2017)      
 * 
 ******************************************************************************/
import java.util.Scanner;

public class Lab2 {
	/**=========================================================================
	 * CLASS METHODS
	 * 
	 * Program methods are listed below in alphabetical order or in the order
     * called within main.
	 *========================================================================*/
	// Method that prints out the prompt asking the user to enter an integer
	public static void printPrompt() {
		System.out.print("\nEnter a number of seconds as a whole number or 0 to quit: ");
	}
	// Method that gets the users input for the number of seconds
	public static int getSeconds(Scanner seconds) {
		int x = seconds.nextInt();
		return x;
	}
	// Method that calculates the number of days based on the number of seconds
	public static int getDays(int secondCount) {
		int x = secondCount/86400;
		return x;
	}
	// Method that calculates the number of hours based on the number of seconds	
	public static int getHours(int secondCount) {
		int x = secondCount/3600;
		return x;
	}
	// Method that calculates the number of minutes based on the number of seconds
	public static int getMinutes(int secondCount) {
		int x = secondCount/60;
		return x;
	}
	// Method that calculates the number of days, hours, minutes, and seconds in a clock format
	public static void getTime(int secondCount) {
		int day = secondCount/86400;
		int hour = (secondCount-(day*86400))/3600;
		int minute = ((secondCount-(day*86400)-(hour*3600)))/60;
		int second = ((secondCount-(day*86400)-(hour*3600)-(minute*60)));
		System.out.println(day + ":" + hour + ":" + minute + ":" + second + " days, hours, minutes, seconds (D:HH:MM:SS)");	
	}
	// Method that prints out the message for the user when they decide to exit the program
	public static void printGoodBye() {
		System.out.println("\nGood bye!");	
	}
	/**=========================================================================
	 * CLASS main
	 * 
	 * @param args - The command-line arguments passed to the running program.
	 *========================================================================*/
	// Main method that prints out the required outputs
	public static void main(String[] args) {
		
		// Initializing the variables
		int secondCount;
		int minuteCount;
		int hourCount;
		int daysCount;
		Scanner seconds = new Scanner(System.in);
		
		// Get the initial value
		printPrompt();
		secondCount = getSeconds(seconds);
		
		// While not equal to 0
		while (secondCount != 0) {
			// Prints out the number of seconds the user entered
			System.out.println("The number of seconds you entered was " + secondCount + " and that equates to: ");
			// Calls the getDays method
			daysCount = getDays(secondCount);
			// Prints the number of days and sets daysCount to the returned variable
			System.out.println(daysCount + " days");
			// Calls the getHours method and sets hoursCount to the returned variable
			hourCount = getHours(secondCount);
			// Prints out the number of hours
			System.out.println(hourCount + " hours");
			// Calls the getMinutes method and sets minuteCount to the returned variable
			minuteCount = getMinutes(secondCount);
			//Prints out the number of minutes
			System.out.println(minuteCount + " minutes");
			//Calls the getTime method
			getTime(secondCount);
			
			// Asks the user for a new number of seconds
			
			printPrompt();
			
			secondCount = getSeconds(seconds);				
		}

		printGoodBye();
	}

}
        
        