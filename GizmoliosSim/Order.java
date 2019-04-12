/**
 * Order class contains information on the order's Date and time for its arrival, requested finish, start of production
 * and end of production, as well as the Customer of the order and the type of Gizmolio being made.
 * 
 * All data for the project is accessed through here with various dot operators. Refer to UML Diagram 
 * on how to locate the desired data.
 * @author ryanm
 *
 */
public class Order{
	
	private Customer customer;
	private int time;
	private double lastPenalty;
	private Gizmolios candy;
	InitialTimeRecord iTR = new InitialTimeRecord();
	FinalTimeRecord fTR = new FinalTimeRecord();
	
	public Order() {
		super();
	}

	public Order(Customer customer, InitialTimeRecord iTR) {
		super();
		this.customer = customer;
		String[] type = {"Red", "Blue", "Green","Orange","White"};
		this.iTR = iTR;
		this.time = (int)(Math.random() * (36-10)) + 10;
		int i = (int)(Math.random() * 5);
		candy = new Gizmolios(type[i],this.time);
	}
	
	public String toString() {
		return this.candy.toString() + "," + this.customer.toString() +  
				"," + this.iTR.toString();
	}
	
	public String toStringF() {
		return this.toString() + "," + this.lastPenalty;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	public FinalTimeRecord gettR() {
		return fTR;
	}

	public void settR(FinalTimeRecord fTR) {
		this.fTR = fTR;
	}
	
	
	public InitialTimeRecord getiTR() {
		return iTR;
	}

	public void setiTR(InitialTimeRecord iTR) {
		this.iTR = iTR;
	}

	public int compareTo() {
		
		
		if (this.iTR.getRequest().getLd().compareTo(this.fTR.getEnd().getLd()) > 0) {
			
			return 0;
		}
		else if (this.iTR.getRequest().getLd().compareTo(this.fTR.getEnd().getLd()) < 0) {
			return 1;
		}
		else {
			if (this.iTR.getRequest().getLt().compareTo(this.fTR.getEnd().getLt()) >= 0) {
				return 0;
			}
			else if (this.iTR.getRequest().getLt().compareTo(this.fTR.getEnd().getLt()) < 0) {
				return 1;
			}
		}
			
		return 0;
	}
	
	public double lastPenalty() {
		
		if (this.compareTo() > 0) {
			lastPenalty = this.customer.getPenalty();
		}
		else
			lastPenalty = 0;
		
		return lastPenalty;
	}
	
	
}
