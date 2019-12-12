package advent;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class advent3 {

	
	private static List<Point> lineOne = new ArrayList<Point>();
	private static List<Point> lineTwo = new ArrayList<Point>();
	private static List<Point> currentLine;
	private static List<Point> intersections;
	private static Point origin = new Point(0,0);
	private static double min = 0;
	private static int minSteps = 0;
	
	public static String getDistance(File input) { 
		
		try {
			Scanner scan = new Scanner(input);
			String pathOne = scan.nextLine();
			String pathTwo = scan.nextLine();	
			scan.close();
			currentLine = lineOne;
			drawLine(pathOne);
			currentLine = lineTwo;
			drawLine(pathTwo);
			findIntersections(); //this needs to be improved
			getSteps();
			return "Closest intersection: "+min+"\nMinimum Steps: "+minSteps;
		}
		catch (FileNotFoundException e) {
			return null;
		}
	}
	
	
	
	
	private static void drawLine(String line) {
		
		Scanner scan = new Scanner(line);
		scan.useDelimiter("[,\\s]");
		while(scan.hasNext()) {
			String move = scan.next();
			makeMove(move);
		}	
		origin.setLocation(0,0);
		scan.close();			
	}
	
	private static void makeMove(String move) {
		
		char direction = move.charAt(0);
		int distance = Integer.parseInt(move.substring(1));		
		
		if(direction == 'R') {			
			for(int i=0; i<distance; i++) {	
				origin.setLocation(origin.getX()+1, origin.getY());
				currentLine.add(new Point((int)origin.getX(),(int)origin.getY()));				
			}
		}
		else if(direction == 'L') {
			for(int i=0; i<distance; i++) {				 
				origin.setLocation(origin.getX()-1,origin.getY());
				currentLine.add(new Point((int)origin.getX(),(int)origin.getY()));
			}
		}
		else if(direction == 'U') {
			for(int i=0; i<distance; i++) {				 
				origin.setLocation(origin.getX(),origin.getY()+1);
				currentLine.add(new Point((int)origin.getX(),(int)origin.getY()));
			}
		}
		else if(direction == 'D') {
			for(int i=0; i<distance; i++) {				 
				origin.setLocation(origin.getX(),origin.getY()-1);
				currentLine.add(new Point((int)origin.getX(),(int)origin.getY()));
			}
		}		
	}
	
	
	private static void findIntersections() {
		intersections = new ArrayList<>(lineOne);
		intersections.retainAll(lineTwo);
		for(int i=0; i<intersections.size(); i++) {
			double temp = manhattan(intersections.get(i));
			if(min == 0) {
				min = temp;
			}
			else if(min > temp) {
				min = temp;												
			}
		}	
	}
	/*old
	private static void findIntersections() {		
		for(int i=0; i<lineOne.size(); i++) {
			for(int j=0; j<lineTwo.size(); j++) {
				if(lineOne.get(i).equals(lineTwo.get(j))) {
					intersections.add(lineOne.get(i));
					double temp = manhattan(lineOne.get(i));
					if(min == 0) {
						min = temp;
					}
					else if(min > temp) {
						min = temp;												
					}
				}
			}
		}
	}
	*/
	
	private static double manhattan(Point p) {
		return Math.abs(p.getX() - 0) + Math.abs(p.getY() - 0);
	}
	
	private static void getSteps() {
		int a =0;
		int b = 0;
		minSteps = 0;
		for(int i=0; i<intersections.size(); i++) {
			for(int j=0; j<lineOne.size(); j++) {
				if(lineOne.get(j).equals(intersections.get(i))) {
					a = j+1;
					break;
				}
			}
			for(int j=0; j<lineTwo.size(); j++) {
				if(lineTwo.get(j).equals(intersections.get(i))) {
					b = j+1;
					break;
				}
			}
			int temp = a+b;
			if(minSteps == 0) {
				minSteps = temp;
			}
			else if(minSteps > temp) {
				minSteps = temp;
			}
		}
	}
	
}
