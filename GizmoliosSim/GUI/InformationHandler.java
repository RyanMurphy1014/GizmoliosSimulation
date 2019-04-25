package GUI;

import java.util.ArrayList;
import java.util.LinkedList;

import Algorithm.Order;

public class InformationHandler {
	private static LinkedList<Order> orders = new LinkedList<Order>();
	
	public static LinkedList<Order> getList(){
		return orders;
	}
	
	public static void setList(LinkedList<Order> linkedList) {
		InformationHandler.orders = linkedList;
	}
}
