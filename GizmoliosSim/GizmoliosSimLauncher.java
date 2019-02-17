
import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains main method of project.
 * @author ryanm
 *
 */
public class GizmoliosSimLauncher {
	public static void main(String [] args) throws IOException{
		ArrayList<Order> orderList = SpreadsheetProcessing.getOrderList();
		for(int i = 0; i < orderList.size(); i++){
			System.out.println("Order #" + (i + 1) + " " + orderList.get(i));
		}
		System.exit(0) 
		//There is a bug where the program won't terminate
		//Likely due to the bufferedreader openRead() method in the processing class
	}
}
