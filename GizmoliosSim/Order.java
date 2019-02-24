import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Order class contains information on the order's Date and time for its arrival, requested finish, start of production
 * and end of production, as well as the Customer of the order and the type of Gizmolio being made.
 * 
 * All data for the project is accessed through here with various dot operators. Refer to UML Diagram 
 * on how to locate the desired data.
 * @author ryanm
 *
 */
public class Order implements Comparable{
	private Customer customer;
	private LocalDate arrivalDate;
	private LocalTime arrivalTime;
	private Gizmolios type;
	private LocalTime requestedTime;
	private LocalDate requestedDate;
	
	//The following variables are not input data, they are created by running through the machine
	private LocalDate startingDate;
	private LocalDate endingDate;
	private LocalTime startingTime;
	private LocalTime endingTime;
	
	/**
	 * 
	 * @param customer - Customer who placed the order
	 * @param arrivalDate - Date the order was placed
	 * @param arrivalTime - Time the order was placed
	 * @param type - Type of Gizmolio being made
	 * @param requestedTime - Time when the order is requested to be finished
	 * @param requestedDate - Date when the order is requested to be finished
	 */
	public Order(Customer customer, LocalDate arrivalDate, LocalTime arrivalTime, Gizmolios type,
			LocalTime requestedTime, LocalDate requestedDate) {
		this.customer = customer;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.type = type;
		this.requestedTime = requestedTime;
		this.requestedDate = requestedDate;
	}

	/**
	 * Default Constructor
	 */
	public Order(){
		this.customer = new Customer();
		this.arrivalDate = LocalDate.now();
		this.arrivalTime = LocalTime.now();
		this.type = new Gizmolios();
		this.requestedTime = LocalTime.now();
		this.requestedDate = LocalDate.now();
		this.startingDate = LocalDate.now();
		this.endingDate = LocalDate.now();
		this.startingTime = LocalTime.now();
		this.endingTime = LocalTime.now();
	}

	public String toString(){
		return "Gizmolio type:" + type.getType() + " Time to make:" + type.getTimeToMake() +
				" Customer Name:" + customer.getCustName() + " Late Penalty:" + customer.getPenalty() +
				" Order Arrival:" + arrivalDate + " " + arrivalTime + " Requested finish:" + requestedDate +
				" " + requestedTime;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime.plusHours(1);
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Gizmolios getType() {
		return type;
	}

	public void setType(Gizmolios type) {
		this.type = type;
	}
	public LocalTime getRequestedTime() {
		return requestedTime.plusHours(1);
	}
	public void setRequestedTime(LocalTime requestedTime) {
		this.requestedTime = requestedTime;
	}

	public LocalDate getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(LocalDate requestedDate) {
		this.requestedDate = requestedDate;
	}

	public LocalDate getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}

	public LocalDate getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}

	public LocalTime getStartingTime() {
		return startingTime.plusHours(1);
	}

	public void setStartingTime(LocalTime startingTime) {
		this.startingTime = startingTime;
	}



	public LocalTime getEndingTime() {
		return endingTime.plusHours(1);
	}



	public void setEndingTime(LocalTime endingTime) {
		this.endingTime = endingTime;
	}

	

	@Override
	public int compareTo(Object other) {
		if(this.arrivalDate.compareTo(((Order) other).getArrivalDate()) > 0) {
			return 1;
		}else if(this.arrivalDate.compareTo(((Order) other).getArrivalDate()) == 0) {
			if(this.arrivalTime.compareTo(((Order) other).getArrivalTime()) > 0) {
				return 1;
			}else if(this.arrivalTime.compareTo(((Order) other).getArrivalTime()) == 0) {
				return 0;
			}else {
				return -1;
			}
		}else {
			return -1;
		}
	}
	
	
	
}
