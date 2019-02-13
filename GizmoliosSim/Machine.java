/**
 * Class to hold information on manufacturing machine.
 * @author ryanm
 *
 */
public class Machine {
	//I'm not adding type yet because I thought machines can make any type of Gizmolios
	private boolean status;
	private int waitTime;
	private int iD;


	/**
	 * @param status - If the machine is running or not
	 * @param waitTime - How many hours until machine is ready
	 * @param iD - Id of order it is currently working on
	 */
	public Machine(boolean status, int waitTime, int iD) {
		super();
		this.status = status;
		this.waitTime = waitTime;
		this.iD = iD;
	}

	public Machine(){
		this.status = false;
		this.waitTime = 1;
		this.iD = -999;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
}
