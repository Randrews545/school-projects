/*******************************************************************************
 * 
 * Lab3 program
 * 
 * Purpose:  This program takes two numbers and count up or down from the first to the second
 * 
 * @author Ryan Andrews
 *
 * @version 1.0 (September 27, 2017)      
 * 
 ******************************************************************************/

import java.util.Scanner;

public class Lab3 {

/**========================================================================= 
* 
* CLASS METHODS
* 
* Program methods are listed below in alphabetical order or in the order
* called within main.
*========================================================================*/
	
//This method prints out all the numbers for if the first number is greater than the second
	public static void getnumX(int first, int second){
		for(int t = first; t>=second; t--){
			System.out.println(t);
		}
	}
//This method prints out all the numbers for if the second number is greater than the first
	public static void getnumY(int first, int second){
		for(int t = first; t<=second; t++){
			System.out.println(t);
		}
	}

	/**=========================================================================
	 * CLASS main
	 * 
	 * @param args - The command-line arguments passed to the running program.
	 *========================================================================*/
	public static void main(String[] args) {
		System.out.print("Please enter the first integer: ");
		Scanner x = new Scanner (System.in);
		int firstNum = x.nextInt();
		System.out.println("");
		System.out.println("Please enter the second integer: ");
		Scanner y = new Scanner (System.in);
		int secondNum = y.nextInt();
		System.out.println("");
		if(firstNum>=secondNum){
			getnumX(firstNum, secondNum);
		}
		else if(secondNum>=firstNum){
			getnumY(firstNum,secondNum);
		}
		else if(firstNum==secondNum){
			System.out.print(firstNum);
		}
	}

}
