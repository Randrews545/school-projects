import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab7 {

	/**========================================================================= 
	* 
	* CLASS METHODS
	* 
	* Program methods are listed below in alphabetical order or in the order
	* called within main.
	*========================================================================*/
	
	//This method prints out the maze with the changes made by the user input
	public static void printmaze(int pointerX, int pointerY, char pointer) throws FileNotFoundException{
		int arrayX = 0, arrayY = 0;
		char [] [] A;
		A = new char[30][12];
		Scanner textInput = new Scanner((new File("maze.txt")));
		while(textInput.hasNextLine()){
			String mazeline = textInput.nextLine();
		for (int i = 0; i < mazeline.length(); i++){
			char mazespot = 'a';
			mazespot = mazeline.charAt(i);
			if(arrayX != pointerX || arrayY != pointerY){
			A [arrayX] [arrayY] = mazespot;
			}
			else if(arrayX == pointerX && arrayY == pointerY && mazespot != '#'){
				A [arrayX] [arrayY] = pointer;
			}
			else if(arrayX == pointerX && arrayY == pointerY && mazespot == '#'){
				A [arrayX] [arrayY] = mazespot;
			}
			else if(pointerX == 29 && pointerY == 11){
				System.out.print("You Win!");
			}
			System.out.print(A [arrayX] [arrayY]);
			if (arrayX < 29){
				arrayX++;
			}
			else if(arrayX == 29){
				arrayX = 0;
				arrayY++;
			}
			}
			System.out.println("");
	}
	}
	/**=========================================================================
	* CLASS main
	* *
	* @param args - The command-line arguments passed to the running program.
	*========================================================================*/			
	public static void main(String[] args) throws FileNotFoundException {
		int pointerX = 1, pointerY = 1, end = 0;
		char pointer = '>';
			printmaze(pointerX, pointerY, pointer);
			System.out.println("");
			while (end == 0){
			System.out.print("Next command (L, R, F, B or Q): ");
			Scanner userI = new Scanner(System.in);
			String userInput = userI.next();
			if (pointer == '>' & userInput.equals("R")){
				pointer = 'V';
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == 'V' && userInput.equals("R")){
				pointer = '<';
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '<' && userInput.equals("R")){
				pointer = '^';
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '^' && userInput.equals("R")){
				pointer = '>';
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '>' && userInput.equals("L")){
				pointer = '^';
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '^' && userInput.equals("L")){
				pointer = '<';
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '<' && userInput.equals("L")){
				pointer = 'V';
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == 'V' && userInput.equals("L")){
				pointer = '>';
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '>' && userInput.equals("F")){
				pointerX++;
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '^' && userInput.equals("F")){
				pointerY--;
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '<' && userInput.equals("F")){
				pointerX--;
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == 'V' && userInput.equals("F")){
				pointerY++;
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '>' && userInput.equals("B")){
				pointerX--;
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '^' && userInput.equals("B")){
				pointerY++;
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == '<' && userInput.equals("B")){
				pointerX++;
				printmaze(pointerX, pointerY, pointer);
			}
			else if(pointer == 'V' && userInput.equals("B")){
				pointerY--;
				printmaze(pointerX, pointerY, pointer);
			}
			else if(userInput.equals("Q")){
				end++;
			}
			}
		System.out.print("GoodBye.");
	}
	}