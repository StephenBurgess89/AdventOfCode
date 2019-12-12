package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class advent2 {
	
	public static String Intcode(File input) {
		
		List<Integer> numbers = new ArrayList<Integer>();
		try {
			Scanner scan = new Scanner(input);
			scan.useDelimiter("[,\\s]");
			while(scan.hasNextInt()) {
				numbers.add(scan.nextInt());
			}
			scan.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		int[] codes = new int[numbers.size()];
		for(int i=0; i<numbers.size(); i++) {
			codes[i] = numbers.get(i);
		}

		for(int i=0; i<100; i++) {
			for(int j=0; j<100;j++) {
				int [] memory = Arrays.copyOf(codes, codes.length);
				memory[1] = i;
				memory[2] = j;
				String s = getOutput(memory);
				if(s != null) {
					return s;
				}
			}	
		}
		return null;
		
	
	}
	
	private static String getOutput(int[] memory) {
		
		for(int m = 0; m<memory.length; m+=4) {
			
			int x = memory[m];
			if(x == 1) {				
				memory[memory[m+3]] = memory[memory[m+1]] + memory[memory[m+2]];
			}
			else if(x == 2) {
				memory[memory[m+3]] = memory[memory[m+1]] * memory[memory[m+2]];			
			}
			else if(x == 99) {
				break;
			}
		}
		if(memory[0] == 19690720) {
			return "("+memory[1]+","+memory[2]+")";
		}
		else {
			return null;
		}
		
	}
}
