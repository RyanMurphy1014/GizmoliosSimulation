package Algorithm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import Blueprints.Customer;
import Blueprints.Gizmolios;
import Blueprints.Machine;
import TimeRecords.FinalTimeRecord;
import TimeRecords.InitialTimeRecord;
import TimeRecords.TimeDate;

public class GenerateOrder {
	private LocalDate currentDate = LocalDate.now();
	private LocalTime currentTime = LocalTime.of((int)(Math.random()*22)+1, 0);
	private LinkedList<Order> orders = new LinkedList<>();
	private LinkedList<Order> ordersList = new LinkedList<>();
	private String algorithm;

	private Random rand = new Random();
	private Machine machine;
	private int penalty;
	private int ordersProcessed;

	// Random chance adjustments

	private final int CHANCE_TO_GENERATE = 10; // 0-99 chance that a new order will be generated and added to the list
	private final int MAX_PENALTY = 200;
	private final int MIN_PENALTY = 25;
	//private final Gizmolios[] type = new Gizmolios[5];

	/**
	 * Creates generation object and creates first order. Creates first order and
	 * stores it in orders list Sets current time and date based on initial order
	 * 
	 * The creation of a GenerateOrder object also creates an internal machine
	 * object. This machine object is handled completely inside of GenerateOrder so
	 * the Machine class never has to be touched.
	 */
	public GenerateOrder() {
		penalty = 0;
		ordersProcessed = 0;
		generate();
		machine = new Machine(true, 1, null);
		sendToMachine(orderToRun());
	}

	public GenerateOrder(String type) {
		penalty = 0;
		ordersProcessed = 0;
		generate();
		machine = new Machine(true, 1, null);
		this.algorithm = type;
		sendToMachine(orderToRun());
		// checkHourly();
	}

	/**
	 * Creates a new order and adds it to the list of available orders. Random
	 * number generation values can be adjusted with final variables at the
	 * beginning of the file
	 */
	public void generate() {
		
		orders.add(new Order(
				new Customer(generateName(rand.nextInt(30)), rand.nextInt(MAX_PENALTY - MIN_PENALTY) + MIN_PENALTY),
				new InitialTimeRecord(new TimeDate(currentDate, currentTime),
						new TimeDate(currentDate.plusDays(((int) Math.random() * 3) + 1), LocalTime.NOON)),
				new FinalTimeRecord(new TimeDate(currentDate, currentTime), new TimeDate(currentDate, currentTime)),
				penalty));
	}

	/**
	 * List of random names to be used as the customer's name.
	 * 
	 * @param seed
	 *            - random number to choose which name to return. Handled inside
	 *            generate()
	 * @return - A random name to give a customer
	 */
	public String generateName(int seed) {
		String[] names = { "Melaine Robillard", "Vivien Mcmonagle", "Glendora Schwartzkopf", "Josue Loll",
				"Becky Hines", "Taunya Furry", "Bernardina Rosebrook", "Phil Keep", "Loretta Schon", "Linh Gibby",
				"Kermit Falkowski", "Paulita Canada", "Claribel Dames", "Eldridge Mercado", "Sulema Sands",
				"Hosea Mance", "Aurelio Lover", "Marvis Aubuchon", "Shea Condon", "Spencer Chea", "Jill Lones",
				"Brenna Liu", "Thaddeus Gurney", "Olen Trial", "Felicita Barrus", "Luther Sorge", "Heath Lenzi",
				"Yadira Ying", "Liane Getz", "Dixie Batt" };
		return names[seed];
	}

	/**
	 * This method is the most important inside GenerateOrder. This hooks together
	 * all of the methods. This is the only method that needs to be interacted with.
	 * 
	 * This method is used by creating a while loop and running it for a specified
	 * amount of time by comparing to the current date and time. This simulates an
	 * hourly check the machine and orders
	 * 
	 * checkHourly() performs the following actions: 1.Checks if a new order should
	 * be generated 2.If the machine has had an hour of down time it will send it a
	 * new order to manufacture 3.Checks if order is complete and handles penalties
	 * 4.And increments the hours
	 */
	public void checkHourly() {

		//if (rand.nextInt(100) <= CHANCE_TO_GENERATE) {
			generate();
		//}

		// Sends new order to the machine
		if (machine.isRunning() == false) {
			machine.setRunningStatus(true);
			
			if (algorithm != null) {
				if (algorithm.equals("fifo")) {
					sendToMachine(fifo());
					//machine.setRunningStatus(false);
				} else if (algorithm.equals("hpf")) {
					sendToMachine(hpf());
					//machine.setRunningStatus(false);
				} else if (algorithm.equals("stf")) {
					//sendToMachine(stf());
					//machine.setRunningStatus(false);
				} else if (algorithm.equals("spa")) {
					sendToMachine(spa());
					//machine.setRunningStatus(false);
				} 
			}
			else {
				sendToMachine(orderToRun());
				//machine.setRunningStatus(false);
			}
		}

		// Checks If order is complete
		if (currentDate.equals(machine.getCurrentOrder().getfTR().getEnd().getLd())) { // if it is the finishing date
			if (currentTime.equals(machine.getCurrentOrder().getfTR().getEnd().getLt())) { // if it is the finishing time
				
				
				/*int penal = machine.getCurrentOrder().lastPenalty();
				penalty = penalty + penal;
				ordersProcessed++;
				machine.setRunningStatus(false);*/
				
				if (machine.getCurrentOrder().getfTR().getEnd().getLd().compareTo(machine.getCurrentOrder().getiTR().getRequest().getLd()) >= 0) { // If the date is late
					if (machine.getCurrentOrder().getfTR().getEnd().getLt().compareTo(machine.getCurrentOrder().getiTR().getRequest().getLt()) > 0) { // If the time is late
						
						penalty += machine.getCurrentOrder().getCustomer().getPenalty();
						machine.setRunningStatus(false);
						ordersProcessed++;
						
					}
					else {
						machine.setRunningStatus(false);
						ordersProcessed++;
					}
				} else { // If on time
					penalty = 0;
					machine.setRunningStatus(false);
					ordersProcessed++;
				}

			}
		}

		// Increments hours and handles day roll over
		if (currentTime.plusHours(1).equals(LocalTime.of(0, 0))) {
			currentDate = currentDate.plusDays(1);
			currentTime = currentTime.plusHours(1);
		} else {
			currentTime = currentTime.plusHours(1);
		}

	}

	/**
	 * Handles passing an order to the machine and sets the ending date and time of
	 * the order. When an order is sent to the machine, it is removed from the
	 * available orders list
	 * 
	 * @param order
	 *            - Which order to be sent to be manufactured
	 */
	public void sendToMachine(Order order) {
		
		
		machine.setCurrentOrder(order);

		order.getfTR().getStart().setLd(currentDate);
		order.getfTR().getStart().setLt(currentTime);

		// sets ending date and time
		if (order.getCandy().getTimeToMake() + currentTime.getHour() > 23) {

			order.getfTR().getEnd()
					.setLd(currentDate.plusDays((order.getCandy().getTimeToMake() + currentTime.getHour()) / 23));
			order.getfTR().getEnd().setLt(currentTime.plusHours(order.getCandy().getTimeToMake()));

		} else {
			order.getfTR().getEnd().setLt(currentTime.plusHours(order.getCandy().getTimeToMake()));
			order.getfTR().getEnd().setLd(currentDate);
		}

		System.out.println("||||||||Processing: " + order + "|||||||||");
		System.out.println(
				"||||||||Finishing: " + order.getfTR().getEnd().getLd() + "    " + order.getfTR().getEnd().getLt());

		orders.remove(order);

	}
	
	public Order orderToRun() {
		Order highestPenalty = orders.get(0);
		Order shortestTime = orders.get(0);

		//Find lowest Penalty
		for(int i = 1; i < orders.size(); i++) {
			if(orders.get(i).getCustomer().getPenalty() > highestPenalty.getCustomer().getPenalty()) {
				highestPenalty = orders.get(i);
			}
		}

		//Find shortest completion time
		for(int i = 1; i < orders.size(); i++) {
			if(orders.get(i).getiTR().getRequest().getLd().compareTo(shortestTime.getiTR().getRequest().getLd()) < 0) {
				if(orders.get(i).getiTR().getRequest().getLt().compareTo(shortestTime.getiTR().getRequest().getLt()) < 0) {
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

	public Order hpf() {
		/*
		 * orders.clear();
		 * for(int z=0;z<ordersList.size();z++) {
		 * orders.add(ordersList.get(z)); }
		 */
		int hpf = orders.get(0).getCustomer().getPenalty();
		
		for (int i = 0; i < orders.size(); i++) {
			if (hpf < orders.get(i).getCustomer().getPenalty()) {
				hpf = orders.get(i).getCustomer().getPenalty();
			}
		}
		return orders.get(hpf);
	}

	public Order fifo() {
		
		  for(int j = 0; j<orders.size(); j++){
		  		System.out.println(orders);
		 }
		
		Order temp = new Order();
		Queue<Order> order = null;
		
		for (int c = 0; c < orders.size(); c++) {
			order.add(orders.get(c));
		}
		for (int i = 0; i < order.size(); i++) {
			temp = orders.get(i);
			if (currentDate.compareTo((ChronoLocalDate) temp.iTR.getRequest()) < 0) {
				orders.clear();
				return order.remove();
			} else {
				penalty = penalty + orders.get(i).getCustomer().getPenalty();
				orders.clear();
				return order.remove();
			}
		}
		orders.clear();
		return temp;
	}

	public Order stf() {

		/*
		 * for(int z=0;z<ordersList.size();z++) { orders.add(ordersList.get(z)); }
		 */
		Order shortestTime = orders.get(0);
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(0).getiTR().getRequest().getLd().compareTo(shortestTime.getiTR().getRequest().getLd()) < 0) {
				if (orders.get(0).getiTR().getRequest().getLt()
						.compareTo(shortestTime.getiTR().getRequest().getLt()) < 0) {
					shortestTime = orders.get(i);
				}
			}
		}
		orders.clear();
		return null;
	}

	/**
	 * Chooses the best order to be manufactured next. This method needs work to
	 * optimize order selection
	 * 
	 * @return - The order that is most beneficial to manufacture.
	 */
	public Order spa() {

		/*
		 * for(int z=0;z<ordersList.size();z++) { orders.add(ordersList.get(z)); }
		 */
		Order highestpenalty = orders.get(0);
		Order shortestTime = orders.get(0);

		// Find lowest Penalty
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(0).getCustomer().getPenalty() > highestpenalty.getCustomer().getPenalty()) {
				highestpenalty = orders.get(0);
			}
		}

		// Find shortest completion time
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(0).getiTR().getRequest().getLd().compareTo(shortestTime.getiTR().getRequest().getLd()) < 0) {
				if (orders.get(0).getiTR().getRequest().getLt()
						.compareTo(shortestTime.getiTR().getRequest().getLt()) < 0) {
					shortestTime = orders.get(i);
				}
			}
		}

		// If the highest penalty order will cause a penalty, then manufacture the
		// shortest time to make
		if (causePenalty(highestpenalty)) {
			orders.clear();
			return shortestTime;
		} else {
			orders.clear();
			return highestpenalty;
		}

	}

	/**
	 * Checks if the passed in order will have a penalty if it is manufactured now.
	 * 
	 * @param order
	 *            - Order to check is it will cause a penalty
	 * @return - Boolean if a penalty will be incurred
	 */
	public boolean causePenalty(Order order) {
		if (order.getCandy().getTimeToMake() + currentTime.getHour() > 23) {
			if (currentDate.plusDays(1).compareTo(order.getiTR().getRequest().getLd()) > 0) { // If day is after
																								// requested
				if (currentTime.plusHours(order.getCandy().getTimeToMake()).compareTo(order.getiTR().getRequest().getLt()) > 0) { // IF time is after requested
					return true;
				}
			}
		}
		if (currentTime.plusHours(order.getCandy().getTimeToMake())
				.compareTo(order.getiTR().getRequest().getLt()) > 0) { // IF time is after requested
			return true;
		}
		return false; // Default to no penalty

	}

	public String toString() {
		String output = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
		output += ("\nDate: " + currentDate + "   Time: " + currentTime);
		/*output += ("\nAvailable Orders:");
		for (int i = 0; i < orders.size(); i++) {
			output += ("\n" + orders.get(i).toStringF());
		}*/
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

	public LinkedList<Order> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(LinkedList<Order> ordersList) {
		this.ordersList = ordersList;
	}

	public LinkedList<Order> getOrders() {
		return orders;
	}

	public int getOrdersProcessed() {
		return ordersProcessed;
	}

	public Machine getMachine() {
		return machine;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

}
