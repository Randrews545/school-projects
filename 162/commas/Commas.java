import java.util.Scanner;

public class Commas {

	public static void main(String[] args) {
		String commaNum = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter number");
		long x = input.nextLong();
		while(input.hasNextLine()) {
		commaNum = commaIn(x, commaNum);
		System.out.println(commaNum);
		commaNum = "";
		x = input.nextLong();
		}
	}
	public static String commaIn(long x, String commaNum) {
		if(x<0 && x <= -1000) {
			commaNum = commaIn(-x, commaNum);
			commaNum = "-" + commaNum;
		}
		else if(x<0 && x > -1000) {
			commaNum = Long.toString(x);
			return commaNum;
		}	
		if(x%1000 == 0 && x >= 1000) {
			commaNum = commaIn(x/1000, commaNum);
			commaNum +="," + "000";
		}
		else if(x%1000 > 0 && x%1000 < 100 && x%1000 >= 10 && x >= 1000) {
			commaNum = commaIn(x/1000, commaNum);
			commaNum +="," + "0" + Long.toString(x%1000);
		}
		else if(x%1000 > 0 && x%1000 < 10 && x%1000 > 0 && x >= 1000) {
			commaNum = commaIn(x/1000, commaNum);
			commaNum +="," + "00" + Long.toString(x%1000);
		}
		else if(x%1000 > 0 && x%1000 > 100 && x >= 1000) {
			commaNum = commaIn(x/1000, commaNum);
			commaNum +="," + Long.toString(x%1000);
			}
		else if(x < 1000 && x > -1000) {
			commaNum = Long.toString(x) + commaNum;
			return commaNum;
		}
		return commaNum;
	}
}
