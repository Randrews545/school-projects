import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Lab10 {
	
	/**========================================================================= 
	* 
	* CLASS METHODS
	* 
	* Program methods are listed below in alphabetical order or in the order
	* called within main.
	*========================================================================*/

//This method gets both zipcodes and calculates the distance between the two
	public static void firstZip (String[] zip, double[] latT, double[] longT, String[] City, int x){
		
		String zipOne = "x";
		double latOne = 0.0;
		double longOne = 0.0;
		String cityOne = " ";
		
		String zipTwo = "y";
		double latTwo = 0.0;
		double longTwo = 0.0;
		String cityTwo = " ";
		
		Scanner zipGet = new Scanner(System.in);
		System.out.print("Please enter first zipcode: ");
		String userzipOne = zipGet.next();
		
		for(int i = 0; i <= x; i++){
			if(userzipOne.equals(zip[i])){
				zipOne = zip[i];
				latOne = latT[i];
				longOne = longT[i];
				cityOne = City[i];
			}
			else if(i == 99 && zipOne.equals("x")){
				System.out.print("That zipcode does not exist, please enter a new zipcode");
				userzipOne = zipGet.next();
				i = 0;
			}
		}
			System.out.print("Please enter second zipcode: ");
			String userzipTwo = zipGet.next();
		for(int j = 0; j <= x; j++){
			if(userzipTwo.equals(zip[j])){
				zipTwo = zip[j];
				latTwo = latT[j];
				longTwo = longT[j];
				cityTwo = City[j];
			}
			else if(j == 99 && zipTwo.equals("y")){
				System.out.print("That zipcode does not exist, please enter a new zipcode");
				userzipTwo = zipGet.next();
				j = 0;
			}
		}
		int radius = 6371;
		double dLat = Math.toRadians(latTwo-latOne);
		double dLon = Math.toRadians(longTwo-longOne);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
					Math.cos(Math.toRadians(latOne)) * Math.cos(Math.toRadians(latTwo)) 
				      * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double d = radius * c;
		System.out.print(cityOne + " " + zipOne + " is ");
		System.out.printf("%.2f", d);
		System.out.print(" miles from " + cityTwo + " " + zipTwo);
		
	}
	
	/**=========================================================================
	* CLASS main
	* *
	* @param args - The command-line arguments passed to the running program.
	*========================================================================*/
			
	public static void main(String[] args) throws FileNotFoundException {
		String [] zCode;
		double[] latiT;
		double [] longT;
		String[] City;
		int x = 0;
		Scanner input = new Scanner(new File("zipcodes.txt"));
		
		try {
			List<String> lines = Files.readAllLines(Paths.get("zipcode.txt"));
			x = lines.size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		zCode = new String [x];
		latiT = new double[x];
		longT = new double[x];
		City = new String [x];
		x = -1;
		while (input.hasNextLine()){
			x++;
		String zipLine = input.nextLine();
		Scanner data = new Scanner (zipLine);
		zCode [x] = data.next();
		latiT [x] = data.nextDouble();
		longT [x] = data.nextDouble();
		City [x] = data.next();
		if (data.hasNext())
		{
			City [x] = City [x] + data.next();
		}
		
		}
		firstZip(zCode, latiT, longT, City, x);
	}

}
