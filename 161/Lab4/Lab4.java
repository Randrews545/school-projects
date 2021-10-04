

import java.util.Scanner;

public class Lab4 {
	
/**========================================================================= 
* 
* CLASS METHODS
* 
* Program methods are listed below in alphabetical order or in the order
* called within main.
*========================================================================*/
	
	public static final int MAX_INT = Integer.MAX_VALUE;
	public static final int MIN_INT = Integer.MIN_VALUE; 
//This method is used to find the Maximum number out of the set
	public static int getMax(int numInt, int newInt, int maxNum){
		int max = maxNum;
		if(numInt != 1 && newInt >= max && newInt > MIN_INT && newInt < MAX_INT){
			max = newInt;
			return max;
		}
		else if(numInt == 1 && newInt >= max && newInt > MIN_INT && newInt < MAX_INT){
		max = newInt;
		System.out.print("Maximum integer in series.................");
		System.out.println(max);
		return max;
		}
		else if (numInt == 1 && newInt <= max && newInt > MIN_INT && newInt < MAX_INT){
			System.out.print("Maximum integer in series.................");
			System.out.println(max);
			return max;
		}
		return max;
	}
	
//This method is used to find the Minimum number	
	public static int getMin(int numInt, int newInt, int minNum){
		int min = minNum;
		if(numInt != 1 && newInt <= min && newInt > MIN_INT && newInt < MAX_INT){
			min = newInt;
			return min;
		}
		else if(numInt == 1 && newInt <= min && newInt > MIN_INT && newInt < MAX_INT){
		min = newInt;
		System.out.print("Minimum integer in series.................");
		System.out.println(min);
		return min;
		}
		else if (numInt == 1 && newInt >= min && newInt > MIN_INT && newInt < MAX_INT){
			System.out.print("Minimum integer in series.................");
			System.out.println(min);
			return min;
		}
		return min;
	}
	
//This method is used to find the amount of zeros in the set of numbers
	public static int getZero(int i,int newInt, int zeroNum) {
		if(i != 1 && newInt == 0 && newInt > MIN_INT && newInt < MAX_INT){
			zeroNum++;
			return zeroNum;
		}
		else if(i == 1 && newInt == 0 && newInt > MIN_INT && newInt < MAX_INT){
			zeroNum++;
			System.out.print("Number of 0s in series....................");
			System.out.println(zeroNum);
			return zeroNum;
			}
			else if(i == 1 && newInt != 0 && newInt > MIN_INT && newInt < MAX_INT){
			System.out.print("Number of 0s in series....................");
			System.out.println(zeroNum);
			return zeroNum;	
			}
		return zeroNum;
	}
	
//This method is used to find the amount of even numbers in the set
	public static int getEven(int i,int newInt, int evenNum) {
		if(i != 1 && newInt%2 == 0 && newInt > MIN_INT && newInt < MAX_INT){
			evenNum++;
			return evenNum;
		}
		else if(i == 1 && newInt%2 == 0 && newInt > MIN_INT && newInt < MAX_INT){
			evenNum++;
			System.out.print("Number of even integers in series.........");
			System.out.println(evenNum);
			return evenNum;
			}
			else if(i == 1 && newInt%2 == 1 && newInt > MIN_INT && newInt < MAX_INT){
			System.out.print("Number of even integers in series.........");
			System.out.println(evenNum);
			return evenNum;	
			}
		return evenNum;
	}
	
//This method is used to find all of the odd numbers in the set
	public static int getOdd(int i,int newInt, int oddNum) {
		if(i != 1 && newInt%2 == 1 && newInt > MIN_INT && newInt < MAX_INT){
			oddNum++;
			return oddNum;
		}
		else if(i == 1 && newInt%2 == 1 && newInt > MIN_INT && newInt < MAX_INT){
			oddNum++;
			System.out.print("Number of odd integers in series..........");
			System.out.println(oddNum);
			return oddNum;
			}
			else if(i == 1 && newInt%2 == 0 && newInt > MIN_INT && newInt < MAX_INT){
			System.out.print("Number of odd integers in series..........");
			System.out.println(oddNum);
			return oddNum;	
			}
		return oddNum;
	}
	
//This method is used to find the average of all numbers in the set
	public static int getAve(int i,int newInt, int aveNum, int intNum) {
		double hopeWork;
		double trueAvg;
		if(i != 1 && newInt > MIN_INT && newInt < MAX_INT){
			aveNum = aveNum + newInt;
			return aveNum;
		}
		else if(i == 1 && newInt > MIN_INT && newInt < MAX_INT){
			aveNum = aveNum + newInt;
			hopeWork = aveNum;
			trueAvg = hopeWork/intNum;
			System.out.print("Average of all integers in series.........");
			System.out.println(trueAvg);
			return aveNum;
			}
		return aveNum;
	}
	
//This method is used to find the total of all numbers in the set
	public static int getTotal(int i,int newInt, int totalNum) {
		if(i != 1 && newInt > MIN_INT && newInt < MAX_INT){
			totalNum = totalNum + newInt;
			return totalNum;
		}
		else if(i == 1 && newInt > MIN_INT && newInt < MAX_INT){
			totalNum = totalNum + newInt;
			System.out.print("Total of all integers in series...........");
			System.out.println(totalNum);
			return totalNum;
			}
		return totalNum;
	}
	
/**=========================================================================
* CLASS main
* 
* @param args - The command-line arguments passed to the running program.
*========================================================================*/
	
	public static void main(String[] args) {
int intNum = 0;
int maxNum = MIN_INT;
int minNum = MAX_INT;
int zeroNum = 0;
int evenNum = 0;
int oddNum = 0;
int aveNum = 0;
int totalNum = 0;
System.out.println("Greetings!");
System.out.println("");
System.out.println("I am the \"Number Wizard\" and I will astound you with my ability to analyze an integer series.");
System.out.println("How many integers are in your series? ");
Scanner x = new Scanner (System.in);
int numInt = x.nextInt();
	for(int i = numInt; i>0; i--){
		intNum++;
		System.out.print("Your integer # ");
		System.out.print(intNum);
		System.out.print(" is?");
		int newInt = x.nextInt();
		maxNum = getMax(i, newInt, maxNum);
		minNum = getMin(i, newInt, minNum);
		zeroNum = getZero(i, newInt, zeroNum);
		evenNum = getEven(i, newInt, evenNum);
		oddNum = getOdd(i, newInt, oddNum);
		aveNum = getAve(i, newInt, aveNum, intNum);
		totalNum = getTotal(i, newInt, totalNum);
	}
	}
}
