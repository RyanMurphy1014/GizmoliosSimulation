package GUI;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Algorithm.GenerateOrder;
import Algorithm.Order;
import Database.DBUtilities;

public class Controller implements Initializable {

	public GenerateOrder generator;
	public ComboBox<String> algorithms;

	public TextField numDays;
	public TextField percentToGenerate;
	public TextField maxPenalty;
	public TextField minPenalty;

	public Slider speedControl;
	public Label hourPerSecond;

	public NumberAxis x;
	public NumberAxis y;
	public XYChart.Series series;

	public ListView<Order> availableOrders;  
	public Label currentOrd;

	public Label currentPenalty;

	public LineChart<?, ?> graph;

	public TextField username;
	public TextField password;
	public TextField dbName;

	public Label errorMsg;
	
	public Button Run;
	public Button sendToDatabse;
	public Button exit;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		algorithms.getItems().addAll("Highest Penalty First", "Smart Penalty");

	
		/*
		speedControl.setMin(0);
		speedControl.setMax(12);
		speedControl.setValue(6);
		speedControl.setShowTickLabels(true);
		speedControl.setMajorTickUnit(1);
		speedControl.setBlockIncrement(1);


		x.setLabel("Hours Passed");
		y.setLabel("Penalty Amount");
		graph.setTitle("Penalty Over Time");
		 */

		series = new XYChart.Series();
		//series.getData().add(new XYChart.Data("1",2));
		graph.getData().addAll(series);
		graph.getXAxis().setTickLabelsVisible(false);
		graph.getXAxis().setOpacity(0);
		graph.setTitle("");
		graph.setLegendVisible(false);


		//System.out.println(numDays.getText());
		/*
		hourPerSecond.textProperty().bind(
				Bindings.format(
						"%.0f hours per second",
						speedControl.valueProperty()
						)
				);

		 */
	}

	public void updateSpeed() {
		hourPerSecond.setText(Double.toString(speedControl.getValue()));
		//System.out.println();
	}

	public void run(){
		boolean readyToRun = true;
		if(algorithms.getValue() == null) {
			readyToRun = false;
			errorMsg.setText("Please select an algorithm to test.");
		}
		
		if(numDays.getText().isEmpty() || percentToGenerate.getText().isEmpty() ||
				maxPenalty.getText().isEmpty() || minPenalty.getText().isEmpty()) {
			readyToRun = false;
			errorMsg.setText("Please fill in all parameters before running.");
		}
		
		if(Integer.parseInt(maxPenalty.getText()) < Integer.parseInt(minPenalty.getText())) {
			readyToRun = false;
			errorMsg.setText("The maximum penalty must be greater than the minimum.");
		}
		
		
		if(readyToRun == true) {
			readyToRun = false;
			errorMsg.setText("");


			availableOrders.getItems().clear();
			series.getData().clear();
			generator = new GenerateOrder(algorithms.getValue(),
					Integer.parseInt(percentToGenerate.getText()), 
					Integer.parseInt(maxPenalty.getText()), 
					Integer.parseInt(minPenalty.getText()));

			while(generator.getCurrentDate().compareTo(LocalDate.now().plusDays(Integer.parseInt(numDays.getText()))) < 0){
				generator.checkHourly();

				series.getData().add(new XYChart.Data(Integer.toString(generator.getHoursPassed()),generator.getPenalty()));
				graph.getData().addAll(series);

				availableOrders.getItems().clear();

				currentPenalty.setText(Integer.toString(generator.getPenalty()));


				if(generator.getMachine().isRunning() == true) {
					currentOrd.setText(generator.getMachine().getCurrentOrder().toString());
				}else {
					currentOrd.setText("The machine is currently off.");
				}
				availableOrders.getItems().addAll(generator.getOrders());
				//System.out.println(generator);

			}
			//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			//System.out.println("Orders Processed: " + generator.getOrdersProcessed());
			//System.out.println("Penalty: " + generator.getPenalty());
			Run.setText("Run");

		}



		//GizmoliosSimLauncher.mainLoop(generator, Integer.parseInt(numDays.getText()));

		//System.out.println(InformationHandler.getList());


	}


	public void dataBaseClick() {
		boolean readyToRun = true;
		if(username.getText().isEmpty() || password.getText().isEmpty() || dbName.getText().isEmpty()) {
			errorMsg.setText("Please fill in all parameters before trying to connect.");
			readyToRun = false;
		}
		
		if(readyToRun == true) {
			readyToRun = false;
			errorMsg.setText("");
			DBUtilities.createConnection(username.getText(), password.getText(), dbName.getText());
			DBUtilities.createTableOrder(true);	     
			try
			{
				DBUtilities.storeOrder(generator.getOrders(), true);
			}

			catch(Exception e){
				errorMsg.setText("There is no data in memory to store");
				System.out.println("There is no data in memory to store");
			}
			DBUtilities.closeConnection();
		}
	}


	public void exitClick() {
		System.exit(0);
	}

	public int getDays() {
		return Integer.parseInt(numDays.getText());
	}



}