/**
 * Class to hold information about the Gizmolios. Includes type, and customer ID.
 * @author ryanm
 *
 */
public class Gizmolios {
	private String type;
	//I'm not sure what type of data start process
		//and end process is as per the blueprint picture.
		//private Customer customer;

	/**
	 * @param type - Type of Gizmolio
	 * @param customer - Customer ID
	 */
	public Gizmolios(String type) {
		this.type = type;
		//this.customer = customer;
	}

	public Gizmolios(){
		this.type = "";
		//this.customer = null;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/*public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer id) {
		this.customer = id;
	}*/
}
