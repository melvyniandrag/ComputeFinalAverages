import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class will read in a .csv file of student names and grades.
 * Then it will compute the averages for each student.
 * This program will compute averages in the same way I will compute final
 * grades for the semester:
 * 
 *  - drop the two lowest grades
 *  - average the rest and turn it into a letter grade.
 * 
 * This is a practical exercise in using
 *  - ArrayList
 *  - HashMap
 *  - Scanner
 *  - Strings
 * 
 * @author melvyn
 *
 */
public class ComputeFinalGrades {
	
	private static enum ERROR_CODE{
		SUCCESS,
		FILE_NOT_FOUND_ERROR
	}
	
	
	/**
	 * Read a csv into a hashmap
	 * 
	 * @param csvFileName - name of the csv to read
	 * @param studentGrades - map of student name to list of grades
	 * 
	 * For example, read a csv like this:
	 * 
	 * John,Doe,100,100
	 * Jane,Doe,100,100
	 * 
	 * and turn it into a  map like this one:
	 * 
	 * {
	 * 		"JohnDoe": [100, 100],
	 * 		"JaneDoe": [100, 100]
	 * }
	 */
	private static ERROR_CODE readCSV(String csvFileName,
			Map<String , ArrayList<Double>> studentGrades) {
		File csv = new File(csvFileName);
		Scanner fileReader;
		
		try {
			fileReader = new Scanner(csv);
		} catch ( FileNotFoundException e ) {
			System.err.println(e.getMessage());
			return ERROR_CODE.FILE_NOT_FOUND_ERROR;
		}
		
		while(fileReader.hasNextLine()){
			String line = fileReader.nextLine();
			String[] splitLine = line.split(",");
			// TODO the first two fields from splitLine make a key for the map
			// TODO the remaining fields make the ArrayList<Double>
			// NOTE! The values in splitLine are Strings. 
			// We want Doubles.
			// To turn a String into a Double use this method:
			// Double d = Double.parseDouble(str);
			// e.g. Double d = Double.parseDouble("3.0") will create a double with value 3.
			//
			// To add a new item to the map, you do this:
			// String key = "JohnDoe";
			// ArrayList<Double> grades = new ArrayList<Double>(){1.0, 2.0}; <- something like this. I forget the contructor.
			// studentGrades.put(key, listOfGrades);
			
		}
		
		return ERROR_CODE.SUCCESS;
	}
	
	/**
	 * Compute the averages for each student using each student's ArrayList of grades
	 * 
	 * @param studentGrades - map of student name to his/her list of grades
	 * @param studentAverages - map of student name to his/her average
	 * 
	 * The average is computed by dropping the two lowest grades and then averaging.
	 */
	private static void computeAverages(Map<String, ArrayList<Double>> studentGrades,
								Map<String, Double> studentAverages) {
		// TODO 
		// loop over the studentGrades map.
		// for every entry, create a new entry in studentAverages
	}
	
	/**
	 * Convert numeric average to a letter following NJCU guidelines:
	 * https://www.njcu.edu/directories/offices-centers/registrar/grading-system
	 * 
	 * @param studentAverages - map of name to number grade
	 * @param studentFinalLetterGrades - map of name to letter grade
	 * 
	 * Use the numbers in studentAverage to populate the studentFinalLetterGrades 
	 * map.
	 */
	
	private static void convertDoubleAverageToLetterGrade(Map<String, Double> studentAverages,
			Map<String, String> studentFinalLetterGrades) {
		// TODO 
		// This could just be a big if / else if thing
		// Here, I'll get it started for you:
		for(String key : studentAverages.keySet()) {
			if(studentAverages.get(key) >= 96.25 ) {
				studentFinalLetterGrades.put(key, "A");
			} // TODO put all the other letter grades in here.
			else {
				studentFinalLetterGrades.put(key, "F");
			}
		}
		
	}
	
	private static void printFinalGrades(Map<String, String> grades) {
		for(String student : grades.keySet()) {
			System.out.printf("%s earned a %s\n", student, grades.get(student));
		}
	}
	
	public static void main(String[] args) {
		String gradeFileName = "grades.csv";
		Map<String, ArrayList<Double>> studentGrades = new HashMap<String, ArrayList<Double>>();
		Map<String, Double> studentAverages = new HashMap<String, Double>();
		Map<String, String> studentFinalLetterGrades = new HashMap<String, String>();
		
		ERROR_CODE processedSuccessfully  = readCSV(gradeFileName, studentGrades);
		if(processedSuccessfully != ERROR_CODE.SUCCESS) {
			return;
		}
		computeAverages(studentGrades, studentAverages);
		convertDoubleAverageToLetterGrade(studentAverages, studentFinalLetterGrades);

		printFinalGrades(studentFinalLetterGrades);
	}

}
