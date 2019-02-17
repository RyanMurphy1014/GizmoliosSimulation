import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 * Static Class used to load in data from the sampledata spreadsheet that has been converted to .csv
 * 
 * @author ryanm
 *
 */
public class SpreadsheetProcessing {

	public static ArrayList<Order> getOrderList() throws IOException{
		ArrayList<Order> orderList = new ArrayList<>();
		BufferedReader reader = openRead();
		boolean loadfile = true;			//Boolean to control if there are more lines to import
		String input = reader.readLine();	
		while(loadfile){					//Run loop until each line of spreadsheet has been imported
			input = reader.readLine();
			if(input == null){				//Checks to see if last line has been reached
				loadfile = false;			//Ends loop if last line has been reached
			}else{							//Otherwise create new order in list
				String [] parts = input.split(",");
				orderList.add(new Order(new Customer(parts[3], Double.parseDouble(parts[4])), 
						stringToDate(parts[5]), stringToTime(parts[5]), new Gizmolios(parts[1],  Integer.parseInt(parts[2])), 
						stringToTime(parts[6]), stringToDate(parts[6])));	
			}
		}
		return orderList;
	}

	/**
	 * Creates a Buffered Reader object. This is a short term solution to work on the algorithm
	 * before the database is created.
	 * @return - A BufferedReader object to read through the sample data. Sample data must be converted to .csv
	 */
	public static BufferedReader openRead() {
		Frame f = new Frame();
		// decide from where to read the file
		FileDialog foBox = new FileDialog(f, "Pick location for reading your file", FileDialog.LOAD);
		foBox.setVisible(true);
		// get the absolute path to the file
		String foName = foBox.getFile();
		String dirPath = foBox.getDirectory();

		// create a file instance for the absolute path
		File inFile = new File(dirPath + foName);
		if (!inFile.exists()) {
			System.out.println("That file does not exist");
			System.exit(0);
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(inFile));
		} catch (IOException e) {
			System.out.println("You threw an exception. ");
		}
		return in;

	}

	/**
	 * Takes a string and converts it to LocalDate format
	 * @param s - String to be converted
	 * @return - LocalDate of passed in String
	 */
	public static LocalDate stringToDate(String s){
		String [] parts = s.split(" ");		
		parts = parts[0].split("/");
		return LocalDate.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
	}

	/**
	 * Takes a string and converts it to LocalTime format
	 * @param s - String to be converted
	 * @return - LocalTime of passed in String
	 */
	public static LocalTime stringToTime(String s){
		String [] parts	= s.split(" ");
		if(parts[1].length() == 4){
			return LocalTime.of(Integer.parseInt(parts[1].substring(0, 2))-1, Integer.parseInt(parts[1].substring(2)));
		}else{
			return LocalTime.of(Integer.parseInt(parts[1].substring(0, 1))-1, Integer.parseInt(parts[1].substring(1)));
		}
	}
}
