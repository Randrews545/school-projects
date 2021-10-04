import java.util.Scanner;

public class Hourglass {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int space = 0;
		if(n < 1) {
			System.out.println("Input is less than 1");
		}
		hourMake(n, space);
	}

	public static void hourMake(int count, int space) {
		String stars = "*";
		if (count > 1) {
			printMany(count, space, stars);
			System.out.println("");
			hourMake(count - 1, space + 1);
			System.out.println("");
			printMany(count,space, stars);
		}
		else if(count == 1) {
			printMany(count, space, stars);
			System.out.println("");
			printMany(count,space, stars);
		}
	}
	public static void printMany(int count, int space, String stars) {
		if(space > 0 && count > 0) {
			System.out.print(" "); 
			space--;
			printMany(count, space, stars);
		}
		else if(space == 0 && count > 0) {
			count--;
			System.out.print(stars);
			System.out.print(" ");
			printMany(count, space, stars);
		}
	}
}
