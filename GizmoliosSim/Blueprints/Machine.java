package Blueprints;

import Algorithm.Order;

/**
 * Class to hold information on the manufacturing machine. Includes status of if
 * the machine is running. How long of a wait it needs between orders, and the
 * current order it is manufacturing.
 * 
 * @author ryanm
 *
 */
public class Machine {

	private boolean status;
	private int waitTime;
	private Order currentOrder;

	/**
	 * @param status
	 *            - If the machine is running or not
	 * @param waitTime
	 *            - How many hours until machine is ready
	 * @param Order
	 *            - Order it is currently working on
	 */
	public Machine(boolean status, int waitTime, Order order) {
		super();
		this.status = status;
		this.waitTime = waitTime;
		this.currentOrder = order;
	}

	/**
	 * Default Constructor
	 */
	public Machine() {
		this.status = false;
		this.waitTime = 1;
		this.currentOrder = new Order();
	}

	public boolean isRunning() {
		return status;
	}

	public void setRunningStatus(boolean status) {
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
