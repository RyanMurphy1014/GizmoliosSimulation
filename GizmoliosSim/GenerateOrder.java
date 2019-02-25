import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class GenerateOrder {
	private LocalDate currentDate = LocalDate.now();
	private LocalTime currentTime = LocalTime.of(0, 0);
	private ArrayList<Order> orders = new ArrayList<>();
	private Random rand = new Random();
	private Machine machine;
	private int penalty;
	private int ordersProcessed;

	//Random chance adjustments
	private final int MAX_REQUESTED_DAYS = 3;   //This max number is added to the current date and becomes the requested date
	private final int CHANCE_TO_GENERATE = 10;	// 0-99 chance that a new order will be generated and added to the list
	private final int MAX_PENALTY = 200;	
	private final int MIN_PENALTY = 25;
	
	/**
	 * Creates generation object and creates first order.
	 * Creates first order and stores it in orders list
	 * Sets current time and date based on initial order
	 * 
	 * The creation of a GenerateOrder object also creates 
	 * an internal machine object. This machine object is 
	 * handled completely inside of GenerateOrder so the
	 * Machine class never has to be touched.
	 */
	public GenerateOrder() {
		penalty = 0;
		ordersProcessed = 0;
		generate();
		machine = new Machine(true, 1, null);
		sendToMachine(orders.get(0));
	}


	/**
	 * This method is the most important inside GenerateOrder. This hooks together all of the methods.
	 * This is the only method that needs to be interacted with.
	 * 
	 * This method is used by creating a while loop and running it for a specified amount of time
	 * by comparing to the current date and time. This simulates an hourly check the machine and orders
	 * 
	 * checkHourly() performs the following actions:
	 * 1.Checks if a new order should be generated
	 * 2.If the machine has had an hour of down time it will send it a new order to manufacture
	 * 3.Checks if order is complete and handles penalties
	 * 4.And increments the hours
	 */
	public void checkHourly() {
		//Generates new order by random chance
		if(rand.nextInt(100) <= CHANCE_TO_GENERATE) {
			generate();
		}
		//Sends new order to the machine
		if(machine.isRunning() == false) {
			machine.setRunningStatus(true);
			sendToMachine(orderToRun());
		}

		//Checks If order is complete 
		if(currentDate.equals(machine.getCurrentOrder().getEndingDate())) {				//if it is the finishing date
			if(currentTime.equals(machine.getCurrentOrder().getEndingTime())) {				//if it is the finishing time
				if(machine.getCurrentOrder().getEndingDate().compareTo(machine.getCurrentOrder().getRequestedDate()) > 0) {    //If the date is late
					if(machine.getCurrentOrder().getEndingTime().compareTo(machine.getCurrentOrder().getRequestedTime()) > 0) {	//If the time is late
						penalty += machine.getCurrentOrder().getCustomer().getPenalty();	
						machine.setRunningStatus(false);
						ordersProcessed++;
					}
				}else { //If on time
					machine.setRunningStatus(false);
					ordersProcessed++;
				}

			}
		}

		//Increments hours and handles day roll over
		if(currentTime.plusHours(1).equals(LocalTime.of(0, 0))) {
			currentDate = currentDate.plusDays(1);
			currentTime = currentTime.plusHours(1);
		}else {
			currentTime = currentTime.plusHours(1);
		}

	}


	/**
	 * Creates a new order and adds it to the list of available orders.
	 * Random number generation values can be adjusted with final variables
	 * at the beginning of the file
	 */
	public void generate() {
		orders.add(new Order(new Customer(generateName(rand.nextInt(30)), rand.nextInt(MAX_PENALTY - MIN_PENALTY)+MIN_PENALTY), 
				getCurrentDate(), getCurrentTime(),
				generateType(rand.nextInt(5)), getCurrentTime().plusHours(rand.nextInt(23)), 
				getCurrentDate().plusDays(rand.nextInt(MAX_REQUESTED_DAYS))));
	}

	/**
	 * List of random names to be used as the customer's name.
	 * @param seed - random number to choose which name to return. Handled inside generate()
	 * @return - A random name to give a customer
	 */
	public String generateName(int seed) {
		String [] names = {"Melaine Robillard",
				"Vivien Mcmonagle",
				"Glendora Schwartzkopf",
				"Josue Loll",
				"Becky Hines",
				"Taunya Furry",
				"Bernardina Rosebrook",
				"Phil Keep",
				"Loretta Schon",
				"Linh Gibby",
				"Kermit Falkowski",
				"Paulita Canada",
				"Claribel Dames",
				"Eldridge Mercado",
				"Sulema Sands",
				"Hosea Mance",
				"Aurelio Lover",
				"Marvis Aubuchon",
				"Shea Condon",
				"Spencer Chea",
				"Jill Lones",
				"Brenna Liu",
				"Thaddeus Gurney",
				"Olen Trial",
				"Felicita Barrus",
				"Luther Sorge",
				"Heath Lenzi",
				"Yadira Ying",
				"Liane Getz",
		"Dixie Batt"};
		return names[seed];
	}

	/**
	 * Generates a gizmolio to pass to the new order. Gizmolio color and time to make are stored in here
	 * @param seed - random number to choose which gizmolio to return. Handled inside generate()
	 * @return - A random gizmolio to assign to a customer
	 */
	public Gizmolios generateType(int seed) {
		Gizmolios [] types= {new Gizmolios("Red", 10), new Gizmolios("Blue", 18), new Gizmolios("Green", 32), new Gizmolios("Orange", 15),
				new Gizmolios("White", 36), new Gizmolios("Purple", 12)};
		return types[seed];
	}

	/**
	 * Handles passing an order to the machine and sets the ending date and time of the order.
	 * When an order is sent to the machine, it is removed from the available orders list
	 * @param order - Which order to be sent to be manufactured
	 */
	public void sendToMachine(Order order) {
		machine.setCurrentOrder(order);
		order.setStartingDate(currentDate);
		order.setStartingTime(currentTime);

		//sets ending date and time
		if(order.getType().getTimeToMake() + currentTime.getHour() > 23) {
			order.setEndingDate(currentDate.plusDays((order.getType().getTimeToMake() + currentTime.getHour()) / 23));
			order.setEndingTime(currentTime.plusHours(order.getType().getTimeToMake()));
		}else {
			order.setEndingTime(currentTime.plusHours(order.getType().getTimeToMake()));
			order.setEndingDate(currentDate);
		}
		System.out.println("||||||||Processing: " + order + "|||||||||");
		System.out.println("||||||||Finishing: " + order.getEndingDate() + "    " + order.getEndingTime());
		
		orders.remove(order);
	}


	/**
	 * Chooses the best order to be manufactured next.
	 * This method needs work to optimize order selection
	 * @return - The order that is most beneficial to manufacture.
	 */
	public Order orderToRun() {
		Order highestPenalty = orders.get(0);
		Order shortestTime = orders.get(0);

		//Find lowest Penalty
		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(0).getCustomer().getPenalty() > highestPenalty.getCustomer().getPenalty()) {
				highestPenalty = orders.get(0);
			}
		}

		//Find shortest completion time
		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(0).getRequestedDate().compareTo(shortestTime.getRequestedDate()) < 0) {
				if(orders.get(0).getRequestedTime().compareTo(shortestTime.getRequestedTime()) < 0) {
					shortestTime = orders.get(i);
				}
			}
		}

		//If the highest penalty order will cause a penalty, then manufacture the shortest time to make
		if(causePenalty(highestPenalty)) {
			return shortestTime;
		}else {
			return highestPenalty;
		}

	}


	/**
	 * Checks if the passed in order will have a penalty if it is manufactured now.
	 * @param order - Order to check is it will cause a penalty
	 * @return - Boolean if a penalty will be incurred
	 */
	public boolean causePenalty(Order order) {
		if(order.getType().getTimeToMake() + currentTime.getHour() > 23) {
			if(currentDate.plusDays(1).compareTo(order.getRequestedDate()) > 0) {		//If day is after requested
				if(currentTime.plusHours(order.getType().getTimeToMake()).compareTo(order.getRequestedTime()) > 0) {  //IF time is after requested
					return true;
				}
			}
		}
		if(currentTime.plusHours(order.getType().getTimeToMake()).compareTo(order.getRequestedTime()) > 0) {  //IF time is after requested
			return true;
		}
		return false;  //Default to no penalty 

	}


	public String toString() {
		String output = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
		output += ("\nDate: " + currentDate + "   Time: " + currentTime);
		output += ("\nAvailable Orders:");
		for(int i = 0; i < orders.size(); i++) {
			output += ("\n" + orders.get(i));
		}
		return output;
	}
	
	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public LocalTime getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(LocalTime currentTime) {
		this.currentTime = currentTime;
	}

	public int getPenalty() {
		return penalty;
	}


	public ArrayList<Order> getOrders(){
		return orders;
	}

	public int getOrdersProcessed() {
		return ordersProcessed;
	}

	public Machine getMachine() {
		return machine;
	}
}
