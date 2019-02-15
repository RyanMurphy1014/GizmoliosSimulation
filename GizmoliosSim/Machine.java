/**
 * Class to hold information on manufacturing machine.
 * @author ryanm
 *
 */
public class Machine {
	
	private boolean status;
	private int waitTime;
	private Order currentOrder;


	/**
	 * @param status - If the machine is running or not
	 * @param waitTime - How many hours until machine is ready
	 * @param iD - Id of order it is currently working on
	 */
	public Machine(boolean status, int waitTime, Order order) {
		super();
		this.status = status;
		this.waitTime = waitTime;
		this.currentOrder = order;
	}

	public Machine(){
		this.status = false;
		this.waitTime = 1;
		this.currentOrder = new Order();
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

	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}
	
}
