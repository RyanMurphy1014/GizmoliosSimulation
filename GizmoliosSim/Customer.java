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

	
	public LocalTime getTime() {
		return orderTime;
	}
	public void setTime(LocalTime time) {
		this.orderTime = time;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public LocalTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	
}
