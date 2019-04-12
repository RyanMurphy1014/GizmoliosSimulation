
import java.io.IOException;
import java.time.LocalDate;

/**
 * Contains main method of project.
 * @author ryanm
 *
 */
public class GizmoliosSimLauncher {
	public static void main(String [] args) throws IOException{
		
		GenerateOrder generator = new GenerateOrder();
		while(generator.getCurrentDate().compareTo(LocalDate.now().plusDays(3)) < 0) {
			//generator.checkHourly();
			System.out.println(generator);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Orders Processed: " + generator.getOrdersProcessed());
		System.out.println("Penalty: " + generator.getPenalty());
	}
}
