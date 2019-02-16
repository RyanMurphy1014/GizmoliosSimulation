import java.time.LocalDate;
import java.time.LocalTime;

/**
	Class that holds information for Customer
	Includes Customer Name, Order Time, and Penalty
	@author ryanm
*/
public class Customer {
	private String custName;
	private LocalTime orderTime;
	private double penalty;

	/**
	 * Constructor
	 * @param cName - Customer Name
	 * @param time - Order Time
	 * @param penalty - Penalty for late production
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
