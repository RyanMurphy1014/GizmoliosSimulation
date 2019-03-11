import java.util.ArrayList;

/**
 * Order class contains information on the order's Date and time for its arrival, requested finish, start of production
 * and end of production, as well as the Customer of the order and the type of Gizmolio being made.
 * 
 * All data for the project is accessed through here with various dot operators. Refer to UML Diagram 
 * on how to locate the desired data.
 * @author ryanm
 *
 */
public class Order1 implements Comparable{
	
	private Customer customer;
	private Gizmolios type;
	
	ArrayList<TimeRecord> tR = new ArrayList<TimeRecord>(); 
	
	public Order1() {
		super();
	}

	public Order1(Customer customer, Gizmolios type, ArrayList<TimeRecord> tR) {
		super();
		this.customer = customer;
		this.type = type;
		this.tR = tR;
	}
	
	public String toString() {
		return null;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Gizmolios getType() {
		return type;
	}

	public void setType(Gizmolios type) {
		this.type = type;
	}

	public ArrayList<TimeRecord> gettR() {
		return tR;
	}

	public void settR(ArrayList<TimeRecord> tR) {
		this.tR = tR;
	}

	@Override
	public int compareTo(Object other) {
		/*if(this.arrivalDate.compareTo(((Order1) other).getArrivalDate()) > 0) {
			return 1;
		}else if(this.arrivalDate.compareTo(((Order1) other).getArrivalDate()) == 0) {
			if(this.arrivalTime.compareTo(((Order1) other).getArrivalTime()) > 0) {
				return 1;
			}else if(this.arrivalTime.compareTo(((Order1) other).getArrivalTime()) == 0) {
				return 0;
			}else {
				return -1;
			}
		}else {
			return -1;
		}*/
		
		return 0;
	}
	
	
	
}
