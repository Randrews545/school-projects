import java.util.Random;
import java.util.Scanner;

/**
 * Plays through the life game using 2d arrays.
 * <p>
 * @author Ryan Andrews
 */

public class Life {

	public int height;
	public int width;
	public long seed;
	public int birthLow;
	public int birthHigh;
	public int liveLow;
	public int liveHigh;
	public boolean lifeMatrix[][];
	
	/**
	 * Constructor that creates a matrix object to be used in Console.java or Graphical.java
	 * <p>
	 * @param rand the long to be used as a seed for the random number
	 * @param height the height of the 2d array
	 * @param width the width of the 2d array
	 * @param lowBirth the minimum number for a birth to occur
	 * @param highBirth the maximum number for a birth to occur
	 * @param lowLive the minimum number for a life to continue
	 * @param highLive the maximum number for a life to continue
	 */
	
	public Life(long rand, int newHeight, int newWidth, int lowBirth, int highBirth, int lowLive, int highLive) {
		
		Scanner input = new Scanner(System.in);
		seed = rand;
		
		height = newHeight;
		width = newWidth;
		if (height < 1) {
		throw new IllegalArgumentException("height must be positive, not " + height);
		}
		if (width < 1) {
		throw new IllegalArgumentException("width must be positive, not " + width);
		}
		
		birthLow = lowBirth;
		birthHigh = highBirth;
		if (birthLow < 1) {
		throw new IllegalArgumentException("the birth minimum must be between 1 and 9, not " + birthLow);
		}
		if (birthHigh < 1) {
		throw new IllegalArgumentException("the birth maximum must be between 1 and 9, not " + birthHigh);
		}
		
		liveLow = lowLive;
		liveHigh = highLive;
		if (liveLow < 1) {
		throw new IllegalArgumentException("live minimum must be between 1 and 9, not " + liveLow);
		}
		if (liveHigh < 1) {
		throw new IllegalArgumentException("live maximum must be between 1 and 9, not " + liveHigh);
		}
		Random randomgenerator = new Random(seed);
		
		lifeMatrix = new boolean [height][width];
		for (int i = 0; i < height; i++){
	 		System.out.println("");
			for (int j = 0; j < width; j++){
				if (i == 0 || j == 0 || i == height - 1 || j == width - 1){
					lifeMatrix[i][j] = false;
				}
				else if (randomgenerator.nextBoolean() == true){
					lifeMatrix[i][j] = true;
				}
				else {
					lifeMatrix[i][j] = false;
				}
			}
		}
		
	}
	
	/**
	 * Modifier that calls the lifeGame method to update the matrix object and then returns the updated matrix
	 * <p>
	 * @return lifeMatrix
	 */
	
	public boolean[][] update(){
	 	lifeGame(lifeMatrix, height, width, birthLow, birthHigh, liveLow, liveHigh);
		return lifeMatrix;
	}
 
	/**
	 * Accessor that makes a clone of the matrix object and returns it
	 * <p>
	 * @return newMatrix
	 */
	
	public boolean[][] world() {
		boolean [][] newMatrix = lifeMatrix.clone();
	 	
		 for (int row=0; row < lifeMatrix.length; row++) {
	     newMatrix[row] = lifeMatrix[row].clone();
		 }
		 
		 return newMatrix;
 	}
 
	/**
	 * Creates a 2d array and fills it with "false".
	 * <p>
	 * @param height the height of the 2d array
	 * @param width the width of the 2d array
	 * @return the newly created 2d array.
	 */
	
	 public static boolean[][] makeMatrix(int height, int width){
		 	boolean[][] startMatrix = new boolean[height][width];
		 	for (int i = 0; i < height; i++)
		    {
		        for (int j = 0; j < width; j++)
		        {
		            startMatrix[i][j] = false;
		        }
		    }
		    return startMatrix;
	 }
	 
	 /**
	  * Takes the previously created 2d array and fills the array with "#" when the random boolean is positive 
	  * <p>
	  * @param finalMatrix the original 2d array
	  * @param lifeRand the random
	  * @param height the height of the 2d array
	  * @param width the width of the 2d array
	  */
	 
	 public static boolean[][] makeLife(boolean[][] finalMatrix, Random lifeRand, int height, int width){
		 	boolean[][] newMatrix = finalMatrix;
		 	for (int i = 0; i < height; i++){
		 		System.out.println("");
				for (int j = 0; j < width; j++){
					if (i == 0 || j == 0 || i == height - 1 || j == width - 1){
						System.out.print(" - ");
					}
					else if (lifeRand.nextBoolean() == true){
						newMatrix[i][j] = true;
						System.out.print(" # ");
					}
					else {
						newMatrix[i][j] = false;
						System.out.print(" - ");
					}
				}
			}
		 	System.out.println("");
		 	return newMatrix;
	 }
	 
	 /**
		 * Clones the original 2d array and fills a new 2d array with the same data.
		 * These are used in conjunction to find out which spaces should have a birth and death in the life game.
		 * After the spaces are figured out the array is updated accordingly, this is repeated 5 times.
		 * <p>
		 * @param newMatrix the original array
		 * @param height the height of the 2d arrays
		 * @param width the width of the 2d arrays
		 * @param birthLow lowest number for new birth
		 * @param birthHigh highest number for new birth
		 * @param liveLow lowest number for death
		 * @param liveHigh highest number for death
		 * @return the newly created 2d array.
		 */
	 
	 public static boolean[][] lifeGame(boolean[][] newMatrix, int height, int width, int birthLow, int birthHigh, int liveLow, int liveHigh){
		 	int lifeCount = 0;
		 	boolean [][] lifeMatrix = newMatrix.clone();
		 	
			 for (int row=0; row < newMatrix.length; row++) {
		     lifeMatrix[row] = newMatrix[row].clone();
			 }
			 
			 for(int y = 1; y < height-1; y++) {
				 for(int x = 1; x < width-1; x++) {
					 if (lifeMatrix[y-1][x-1] == true) {
						 lifeCount++;
					 }
					 if (lifeMatrix[y-1][x] == true) {
					   lifeCount++;
					 }
					 if (lifeMatrix[y-1][x+1] == true) {
					   lifeCount++;
					 }
					 if (lifeMatrix[y][x-1] == true) {
					   lifeCount++;
					 }
					 if (lifeMatrix[y][x+1] == true) {
					   lifeCount++;
					 }
					 if (lifeMatrix[y+1][x-1] == true) {
					   lifeCount++;
					 }
					 if (lifeMatrix[y+1][x] == true) {
					   lifeCount++;
					 }
					 if (lifeMatrix[y+1][x+1] == true) {
					   lifeCount++;
					 }
					 if (lifeMatrix[y][x] == true) {
					   lifeCount++;
					 }
					 if(lifeCount > liveHigh || lifeCount < liveLow) {
					   newMatrix[y][x] = false;
					   }
					 if(lifeCount <= birthHigh && lifeCount >= birthLow) {
					   newMatrix[y][x] = true;
					 }
					 lifeCount = 0;
				 }
			 }
			 
		 	return lifeMatrix;
	}
	 
	 /**
		 * Prints out an updated matrix
		 * <p>
		 * @param newMatrix[][] the updated matrix
		 * @param height the height of the matrix
		 * @param width the width of the matrix
		 * @return lifeMatrix
		 */
	 
	public static void printGame(boolean newMatrix[][], int height, int width) {
		for (int i = 0; i < height; i++) {
			 System.out.println("");
			 for (int j = 0; j < width; j++)
			 {
				 if(newMatrix[i][j] == true){
					 System.out.print(" # ");
				 }
				else if(newMatrix[i][j] == false){
				 System.out.print(" - ");
				}
			 }
		}
	}
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int height = input.nextInt();
		int width = input.nextInt();
		long seed = input.nextLong();
		int birthLow = input.nextInt();
		int birthHigh = input.nextInt();
		int liveLow = input.nextInt();
		int liveHigh = input.nextInt();
		int repeater = 0;
		
		Random randomgenerator = new Random(seed);
		
		boolean [][] newMatrix = makeMatrix(height, width);
		
		newMatrix = makeLife(newMatrix, randomgenerator, height, width);
		
		while (repeater < 5) {
		
		repeater++;
		
		lifeGame(newMatrix, height, width, birthLow, birthHigh, liveLow, liveHigh);
		
		printGame(newMatrix, height, width);
		
		}
	}
}
