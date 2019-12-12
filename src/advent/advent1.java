package advent;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class advent1 {
	
	static int mass = 0;
    static int fuel = 0;
    static int total =0;
    
	public static Integer fuelCount(File input){
		
		try{            
            Scanner scan = new Scanner(input);
            
    	    while(scan.hasNextInt()){
    	    	mass = scan.nextInt();  	    	
    	    	total += getFuel(mass);   	        
    	    }
    	    scan.close();
    	    return total;
        }
        catch(FileNotFoundException e){
        	System.out.println("File not found.");
            return null;
        }
	}
	
	private static int getFuel(int mass) {
		fuel = 0;
		fuel = (mass/3) - 2;
		if(fuel > 6) {
			fuel += getFuel(fuel);
		}
		return fuel;
	}
}
