import java.time.LocalDate;
import java.time.LocalTime;

/**
	Class that holds information for Customer
	Includes First Name, Last Name, order ID, time, and penalty
	@author ryanm
*/
public class Customer {
	private String firstName;
	private String lastName;
	private int iD;
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
	public Customer(String firstName, String lastName, int iD, LocalTime time, double penalty){
		this.firstName = firstName;
		this.lastName = lastName;
		this.iD = iD;
		this.orderTime = time;
		this.penalty = penalty;
	}

	/**
	 * Default constructor
	 */
	public Customer(){
		this.firstName = "John";
		this.lastName = "Doe";
		this.iD = -999;
		this.orderTime = LocalTime.now();
		this.penalty = -999;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
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
