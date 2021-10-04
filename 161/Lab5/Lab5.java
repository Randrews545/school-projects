import java.io.*;
import java.util.*;

public class Lab5 {

/**========================================================================= 
* 
* CLASS METHODS
* 
* Program methods are listed below in alphabetical order or in the order
* called within main.
*========================================================================*/

	//This method prints the intro to the program
	public static void printIntro(){
		System.out.println("Welcome to the Diver Scoring program. This program will calculate an overall score for a diver, based on individual dives.");
	}
	//This method calculates the dive score for one dive 
	public static double calculateDiveScore(String diveLine){
		Scanner data = new Scanner(diveLine);
		double max = 0.0, min = 0.0;
		double finalDive = 0.0;
		double diveScore1 = 0.0, diveScore2= 0.0, diveScore3 = 0.0, diveScore4 = 0.0, diveScore5 = 0.0, diveScore6 = 0.0, diveScore7 = 0.0;
		double difficulty = 0.0;
		double diveNum = 0.0;
		for (int i = 0; i <= 9; i++) {
		if (i == 1){
			diveNum = data.nextDouble();
		}
		else if (i == 2){
			difficulty = data.nextDouble();
		}
		else if (i == 3){
			diveScore1 = data.nextDouble();
		}
		else if (i == 4){
			diveScore2 = data.nextDouble();
		}
		else if (i == 5){
			diveScore3 = data.nextDouble();
		}
		else if (i == 6){
			diveScore4 = data.nextDouble();
		}
		else if (i == 7){
			diveScore5 = data.nextDouble();
		}
		else if (i == 8){
			diveScore6 = data.nextDouble();
		}
		else if (i == 9){
			diveScore7 = data.nextDouble();
			max = Math.max(diveScore1, diveScore2);
			max = Math.max(max, diveScore3);
			max = Math.max(max, diveScore4);
			max = Math.max(max, diveScore5);
			max = Math.max(max, diveScore6);
			max = Math.max(max, diveScore7);
			
			min = Math.min(diveScore1, diveScore2);
			min = Math.min(min, diveScore3);
			min = Math.min(min, diveScore4);
			min = Math.min(min, diveScore5);
			min = Math.min(min, diveScore6);
			min = Math.min(min, diveScore7);

			finalDive = (((diveScore1 + diveScore2 + diveScore3 + diveScore4 + diveScore5 + diveScore6 + diveScore7) - (max + min))* difficulty)*0.6;
			return finalDive;
		}
		}
		return finalDive;
	}
	
/**=========================================================================
* CLASS main
* *
* @param args - The command-line arguments passed to the running program.
*========================================================================*/
		
	public static void main(String[] args) throws FileNotFoundException {
		double finalGet = 0.0, finalTrue = 0.0;
		int diveNum = 0;
		int y = 0;
		Scanner input = new Scanner(new File("DiveData.txt"));
		printIntro();
		System.out.println("");
			while(input.hasNextLine()){
				diveNum++;
				String diveLine = input.nextLine();
				finalGet = calculateDiveScore(diveLine);
				System.out.printf("The diver's score for dive " + diveNum + " is " + "%.2f",finalGet);
				System.out.print(".");
				System.out.println("");
				finalTrue = finalGet + finalTrue;
			}
			System.out.println("");
			System.out.printf("The average score for these dives is " + "%.2f", finalTrue/diveNum);
			System.out.println(".");
		}
		}

        
        