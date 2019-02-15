import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Order class contains information on the order's arrival Date and time,
 * type of gismolio, and its requested finish time.
 * @author ryanm
 *
 */
public class Order {
	private Customer customer;
	private LocalDate arrivalDate;
	private LocalTime arrivalTime;
	private Gizmolios type;
	private LocalTime requestedTime;
	private LocalDate requestedDate;
	private LocalDate startingDate;
	private LocalDate endingDate;
	private LocalTime startingTime;
	private LocalTime endingTime;
	

	/**
	 *
	 * @param customer - Order ID
	 * @param arrivalDate - Arrival Date
	 * @param arrivalTime - Arrival Time
	 * @param type - Type of gizmolios
	 * @param requestedTime - requested finish time
	 */
	public Order(Customer customer, LocalDate arrivalDate, LocalTime arrivalTime, Gizmolios type, LocalTime requestedTime) {
		this.customer = customer;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.type = type;
		this.requestedTime = requestedTime;
	}
	
	

	public Order(Customer customer, LocalDate arrivalDate, LocalTime arrivalTime, Gizmolios type,
			LocalTime requestedTime, LocalDate requestedDate, LocalDate startingDate, LocalDate endingDate,
			LocalTime startingTime, LocalTime endingTime) {
		super();
		this.customer = customer;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.type = type;
		this.requestedTime = requestedTime;
		this.requestedDate = requestedDate;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
	}


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
		return arrivalTime;
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
		return requestedTime;
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
		return startingTime;
	}

	public void setStartingTime(LocalTime startingTime) {
		this.startingTime = startingTime;
	}

	public LocalTime getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(LocalTime endingTime) {
		this.endingTime = endingTime;
	}
	
	
}
