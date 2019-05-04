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

public class Controller implements Initializable {

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
	
	public Button Run;
	public Button exit;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		algorithms.getItems().addAll("Highest Penalty First", "Smart Penalty");



		speedControl.setMin(0);
		speedControl.setMax(12);
		speedControl.setValue(6);
		speedControl.setShowTickLabels(true);
		speedControl.setMajorTickUnit(1);
		speedControl.setBlockIncrement(1);

		/*
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

		hourPerSecond.textProperty().bind(
				Bindings.format(
						"%.0f hours per second",
						speedControl.valueProperty()
						)
				);


	}

	public void updateSpeed() {
		hourPerSecond.setText(Double.toString(speedControl.getValue()));
		//System.out.println();
	}

	public void run(){
		/*
		System.out.println("Max Penalty" + getMaxPenalty() + "\nMin Penalty" + getMinPenalty()
		 + "\nPercent To Generate" + getPercentToGenerate());
		 */
		Run.setText("Running:");
		
		availableOrders.getItems().clear();
		series.getData().clear();
		GenerateOrder generator = new GenerateOrder(algorithms.getValue(),
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




		//GizmoliosSimLauncher.mainLoop(generator, Integer.parseInt(numDays.getText()));

		//System.out.println(InformationHandler.getList());


	}



	public void exitClick() {
		System.exit(0);
	}

	public int getDays() {
		return Integer.parseInt(numDays.getText());
	}



}