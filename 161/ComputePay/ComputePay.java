
public class ComputePay {

	public static void main(String[] args) {
		
		int mondaytime = 4;
		int tuesdaytime = 5;
		int wednesdaytime = 8;
		int thursdaytime = 4;
		int hoursworked;
		double hoursalary = 8.75;
		double totalpay;
		double taxpercent = 0.20;
		double taxowed;
		
		System.out.println("My total hours worked:");
		
		hoursworked = mondaytime + tuesdaytime + wednesdaytime + thursdaytime;
		
		System.out.println(hoursworked);
		
		
		System.out.println("My hourly salary:");
		
		System.out.print("$");
		
		System.out.println(hoursalary);
		
		
		System.out.println("My total pay:");
		
		totalpay = hoursworked * hoursalary;
		
		System.out.println(totalpay);
		
		
		System.out.println("My taxes owed:"); // 20% tax
		
		taxowed = totalpay * taxpercent;
		
		System.out.println(taxowed);
		
	}

}
