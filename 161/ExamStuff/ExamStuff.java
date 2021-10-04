import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExamStuff {

	public static void main(String[] args) throws FileNotFoundException  {
		//Scanner input = new Scanner (new File("now.txt"));
		//while (input.hasNextLine()) {
		//String line = input.nextLine();
		//System.out.println(line);
		//}
		Scanner input = new Scanner(new File("now.txt"));
		while (input.hasNext()) {
		String token = input.next();
		System.out.println(token);
		}

	}

}
