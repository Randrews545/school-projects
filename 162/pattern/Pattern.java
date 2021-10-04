import java.util.Scanner;

public class Pattern {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int point = 1;
		if(n < 1) {
			System.out.println("Input is less than 1");
		}
		System.out.println("");
		patternMaker(n);
		System.out.println("");
	}

	public static void patternMaker(int pMake) {
		if(pMake > 1 && pMake <= 5) {
		patternMaker(pMake - 1);
		System.out.print(" ");
		System.out.print(pMake);
		System.out.print(" ");
		patternMaker(pMake - 1);
		}
		else if(pMake > 5) {
			patternMaker(pMake - 1);
			System.out.print(" ");
			System.out.print(pMake);
			System.out.println("");
			patternMaker(pMake - 1);
		}
		else if (pMake == 1) {
			System.out.print(pMake);
		}
	}
}
