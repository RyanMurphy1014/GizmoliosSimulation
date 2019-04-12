import java.time.LocalDate;
import java.time.LocalTime;

/**
	Class that holds information for Customer
	Includes Customer Name, and Penalty
	@author ryanm
*/
public class Customer {
	private String custName;
	private double penalty;

	/**
	 * Constructor
	 * @param cName - Customer Name
	 * @param penalty - Penalty for late production
	 */
	public Customer(String cName, double penalty){
		this.custName = cName;
		this.penalty = penalty;
	}

	/**
	 * Default constructor
	 */
	public Customer(){
		this.custName = "";
		this.penalty = 0;
	}
	
	public String toString() {
		return custName + "," + penalty;
	}

	

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	
}
