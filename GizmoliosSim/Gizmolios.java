/**
 * Class to hold information about what type of Gizmolio is being made
 * @author ryanm
 *
 */
public class Gizmolios {
	private String type;

	/**
	 * @param type - Type of Gizmolio
	 */
	public Gizmolios(String type) {
		this.type = type;
		
	}

	public Gizmolios(){
		this.type = "";
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
