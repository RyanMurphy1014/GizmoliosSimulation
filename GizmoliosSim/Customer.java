import java.time.LocalDate;
import java.time.LocalTime;

/**
	Class that holds information for Customer
	Includes First Name, Last Name, order ID, time, and penalty.
	@author ryanm
*/
public class Customer {
	private String custName;
	//private Order order;
	private LocalTime orderTime;
	private double penalty;

	/**
	 * Constructor
	 * @param firstName - First Name
	 * @param lastName - Last Name
	 * @param iD - Order ID
	 * @param time - Time of order
	 * @param penalty - Late penalty
	 */
	public Customer(String cName, LocalTime time, double penalty){
		this.custName = cName;
		//this.order = order;
		this.orderTime = time;
		this.penalty = penalty;
	}

	/**
	 * Default constructor
	 */
	public Customer(){
		this.custName = "";
		//this.order = new Order();
		this.orderTime = LocalTime.now();
		this.penalty = 0;
	}

	public String getcustName() {
		return custName;
	}
	public void setcustName(String firstName) {
		this.custName = firstName;
	}
	
	/*public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}*/
	public LocalTime getTime() {
		return orderTime;
	}
	public void setTime(LocalTime time) {
		this.orderTime = time;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
}
