package GUI;
import GUI.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import Algorithm.GenerateOrder;
import Algorithm.Order;
import Blueprints.FileWriter;
import Database.DBUtilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Contains main method of project.
 * @author ryanm
 *
 */
public class GizmoliosSimLauncher extends Application{
	static Scanner scan = new Scanner(System.in);
	public static LinkedList<Order> list = new LinkedList<Order>();
	Button button;
	public static void main(String [] args) throws IOException{
		launch(args); //Starts GUI
		String type;
		int choice=0;
		boolean loop=true;
		
		while(loop)
		{
			menu();
			choice=scan.nextInt();
			
			if(choice==1){
				list.clear();
				type="hpf";
				GenerateOrder generator = new GenerateOrder(type);
				mainLoop(generator);
				for(int p=0;p<generator.getProcessed().size();p++)
				{
					list.add(generator.getProcessed().get(p));
				}
			}
			else if(choice == 2){
				list.clear();
				type="spa";
				GenerateOrder generator = new GenerateOrder(type);
				mainLoop(generator);
				for(int p=0;p<generator.getProcessed().size();p++)
				{
					list.add(generator.getProcessed().get(p));
				}
			}
			else if(choice == 3){
				FileWriter.outputData(list);
			}
			else if(choice == 4) {
				{
		        	DBUtilities.createTableOrder(true);	            	
		        }
			}
			else if(choice == 5) {
				 {
			        	DBUtilities.createTableOrder(true);
			    		try
			    		{
			    			DBUtilities.storeOrder(list, true);
			    		}
			        
					catch(Exception e){
						System.out.println("There is no data in memory to store");
						}
			        	              
			        }
			}
			else if(choice == 6) {
				DBUtilities.closeConnection();
				System.out.println("Successfully close your connection from Database");
			}
			else if(choice == 7){
				System.out.println("Thank you for using our simulation.");
				loop=false;
				System.exit(0);
			}
			/*for(int i=0;i<list.size();i++)
			{
				System.out.println(list.get(i).toStringF());
			}*/
			//System.out.println();
		}
	}
	public static void menu(){
		
		System.out.println("\nEnter the corresponding number");
		System.out.println("1.  Run the HPF(Highest penalty first)");
		System.out.println("2.  Run the SPA(Smart penalty algorithm)");
		System.out.println("3.  Save the file as .csv");
		System.out.println("4.  Create Orders Table in Datbase");
		System.out.println("5.  Save date from memory to Database");
		System.out.println("6.  Disconneting Database form simulation");
		System.out.println("7.  Exit the simulation");
	}
	public static void mainLoop(GenerateOrder generator) {
		while(generator.getCurrentDate().compareTo(LocalDate.now().plusDays(10)) < 0){
			generator.checkHourly();
			System.out.println(generator);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Orders Processed: " + generator.getOrdersProcessed());
		System.out.println("Penalty: " + generator.getPenalty());
	}
	
	/*
	 * (non-Javadoc)
	 * This is the method that starts the GUI elements
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	  public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gizmolio Simulation");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

}









