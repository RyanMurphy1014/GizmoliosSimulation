/**
 * Class to hold information about the Gizmolios. Includes type, and customer ID
 * @author ryanm
 *
 */
public class Gizmolios {
	private String type;
	//I'm not sure what type of data start process
	//and end process is as per the blueprint picture.
	private Customer id;

	/**
	 * @param type - Type of Gizmolio
	 * @param id - Customer ID
	 */
	public Gizmolios(String type, Customer id) {
		this.type = type;
		this.id = id;
	}

	public Gizmolios(){
		this.type = "";
		this.id = null;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Customer getId() {
		return id;
	}
	public void setId(Customer id) {
		this.id = id;
	}
}
