/*
************************** Note: this for testing only remove main method when running the the app to avoid errors **************************

*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
public class Main{
	
	
	
	/*
	 * static void readClassesInfo(String filePath, HashMap<String, Variable> map) {
	 * 
	 * String input = ""; try { File myObj = new File(filePath); Scanner myReader =
	 * new Scanner(myObj); while (myReader.hasNextLine()) { String data =
	 * myReader.nextLine(); input += data + "\n"; System.out.println(data); }
	 * myReader.close(); } catch (FileNotFoundException e) {
	 * System.out.println("An error occurred."); e.printStackTrace(); }
	 * 
	 * System.out.println(input);
	 * 
	 * String[] lines = input.split("\n"); for (String line : lines) {
	 * 
	 * String[] tokens = line.split(" ");
	 * 
	 * String variableName = tokens[0]; String className = tokens[1]; String
	 * classType = tokens[2]; ArrayList<Integer> points = new ArrayList<>();
	 * 
	 * for (int i = 3; i < tokens.length; i++) {
	 * points.add(Integer.parseInt(tokens[i])); }
	 * 
	 * FuzzySet newSet = new FuzzySet(className, classType, points);
	 * map.get(variableName).addFuzzySet(newSet); }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * public static void main(String[] args) {
	 * 
	 * 
	 * HashMap<String, Variable> map = new HashMap<>();
	 * 
	 * Variable v1 = new Variable("proj_funding", "in", 0, 100); Variable v2 = new
	 * Variable("exp_level", "in", 0, 60); Variable v3 = new Variable("risk", "out",
	 * 0, 100);
	 * 
	 * map.put("proj_funding", v1); map.put("exp_level", v2); map.put("risk", v3);
	 * 
	 * // read classes' data from file
	 * 
	 * readClassesInfo("input.txt", map);
	 * System.out.println("*********** print main map ***********");
	 * System.out.println(map);
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 */
	

}


/*
 * FuzzySet s1 = new FuzzySet("small", "TRAP", new ArrayList<>(Arrays.asList(0,
 * 0, 20, 40))); FuzzySet s2 = new FuzzySet("ordinary", "TRAP", new
 * ArrayList<>(Arrays.asList(20, 40, 60, 80)));
 * 
 * s1.calcMemValue(50); System.out.println(s1);
 * 
 * s2.calcMemValue(50); System.out.println(s2);
 * 
 * FuzzySet s3 = new FuzzySet("small", "TRI", new ArrayList<>(Arrays.asList(0,
 * 0, 50))); FuzzySet s4 = new FuzzySet("ordinary", "TRI", new
 * ArrayList<>(Arrays.asList(0, 50, 100)));
 * 
 * s3.calcMemValue(50); System.out.println(s3);
 * 
 * s4.calcMemValue(50); System.out.println(s4);
 */