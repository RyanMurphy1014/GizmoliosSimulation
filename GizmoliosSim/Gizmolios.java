/**
 * Class to hold information about what type of Gizmolio is being made
 * @author ryanm
 *
 */
public class Gizmolios {
	private String type;
	private int timeToMake;

	/**
	 * @param type - Type of Gizmolio
	 */
	public Gizmolios(String type, int creationTime) {
		this.type = type;
		this.timeToMake = creationTime;
		
	}

	public int getTimeToMake() {
		return timeToMake;
	}

	public void setTimeToMake(int timeToMake) {
		this.timeToMake = timeToMake;
	}

	public Gizmolios(){
		this.type = "";
		this.timeToMake = 0;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
