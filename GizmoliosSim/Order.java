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

	public Order(){
		this.customer = new Customer();
		this.arrivalDate = LocalDate.now();
		this.arrivalTime = LocalTime.now();
		this.type = new Gizmolios();
		this.requestedTime = LocalTime.now();
	}

	public Customer getiD() {
		return customer;
	}
	public void setiD(Customer customer) {
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
}
