package Main;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import Algorithm.GenerateOrder;
import Algorithm.Order;

/**
 * Contains main method of project.
 * @author ryanm
 *
 */
public class GizmoliosSimLauncher {
	static Scanner scan = new Scanner(System.in);
	public static LinkedList<Order> list = new LinkedList<Order>();     
	public static void main(String [] args) throws IOException{
		String type;
		int choice=0;
		boolean loop=true;
		
		while(loop)
		{
			menu();
			choice=scan.nextInt();
			if(choice==1) //fifo
			{
				GenerateOrder generator = new GenerateOrder();
				for(int c=0;c<generator.getOrdersList().size();c++)
				{
					list.add(generator.getOrdersList().get(c));
				}
				mainLoop(generator);
			}
			else if(choice==2)//Lifo
			{
				type="fifo";
				GenerateOrder generator = new GenerateOrder(type);
				mainLoop(generator);
			}
			else if(choice==3)
			{
				type="hpf";
				GenerateOrder generator = new GenerateOrder(type);
				mainLoop(generator);
			}
			else if(choice==4)
			{
				type="stf";
				GenerateOrder generator = new GenerateOrder(type);
				mainLoop(generator);
			}
			else if(choice==5)
			{
				type="spa";
				GenerateOrder generator = new GenerateOrder(type);
				mainLoop(generator);
			}
			else if(choice==6)
			{
				
			}
			else if(choice==7)
			{
				System.out.println("Thank you for using our simulation.");
				loop=false;
				System.exit(0);
			}
			for(int i=0;i<list.size();i++)
			{
				System.out.println(list.get(i));
			}
			
		}
	}
	public static void menu(){
		System.out.println("Enter the corresponding number");
		System.out.println("1.  Load in the data");
		System.out.println("2.  Run the FIFO(First in first out)");
		System.out.println("3.  Run the HPF(Highest penalty first)");
		System.out.println("4.  Run the STF(Shortest time first)");
		System.out.println("5.  Run the SPA(Smart penalty algorithm)");
		System.out.println("6.  Save the file");
		System.out.println("7.  Exit the simulation");
	}
	
	public static void mainLoop(GenerateOrder generator) {
		int c = 0;
		while(generator.getCurrentDate().compareTo(LocalDate.now().plusDays(10)) < 0) {
			generator.checkHourly();
			//list.add(generator.getOrders().get(c));
			System.out.println(generator);
			c++;
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Orders Processed: " + generator.getOrdersProcessed());
		System.out.println("Penalty: " + generator.getPenalty());
	}
}
