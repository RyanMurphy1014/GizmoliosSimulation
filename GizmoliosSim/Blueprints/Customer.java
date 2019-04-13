package Blueprints;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class that holds information for Customer Includes Customer Name, and Penalty
 * 
 * @author ryanm
 */
public class Customer {
	private String custName;
	private int penalty;

	/**
	 * Constructor
	 * 
	 * @param cName
	 *            - Customer Name
	 * @param penalty
	 *            - Penalty for late production
	 */
	public Customer(String cName, int penalty) {
		this.custName = cName;
		this.penalty = penalty;
	}

	/**
	 * Default constructor
	 */
	public Customer() {
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

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

}
