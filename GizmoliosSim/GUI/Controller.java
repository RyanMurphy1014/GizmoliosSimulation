package GUI;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import Algorithm.GenerateOrder;

public class Controller implements Initializable {

	public ComboBox<String> algorithms;

	public TextField numDays;
	public TextField percentToGenerate;
	public TextField maxPenalty;
	public TextField minPenalty;
	
	public Slider speedControl;
	public Label hourPerSecond;
	
	
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

		System.out.println(numDays.getText());
		
		hourPerSecond.textProperty().bind(
				Bindings.format(
						"%.0f hours per second",
						speedControl.valueProperty()
						)
				);
		

	}

	public void updateSpeed() {
		hourPerSecond.setText(Double.toString(speedControl.getValue()));
		System.out.println();
	}

	public void run() {
		/*
		System.out.println("Max Penalty" + getMaxPenalty() + "\nMin Penalty" + getMinPenalty()
		 + "\nPercent To Generate" + getPercentToGenerate());
		*/
		GenerateOrder generator = new GenerateOrder(algorithms.getValue(),
				Integer.parseInt(percentToGenerate.getText()), 
				Integer.parseInt(maxPenalty.getText()), 
				Integer.parseInt(minPenalty.getText()));
		
		GizmoliosSimLauncher.mainLoop(generator, Integer.parseInt(numDays.getText()));
		
		System.out.println(numDays.getText());
		System.out.println(percentToGenerate.getText());
		System.out.println(maxPenalty.getText());
		System.out.println(minPenalty.getText());
		System.out.println("Got this far");
	}
	
	public void exitClick() {
		System.exit(0);
	}
	
	public int getDays() {
		return Integer.parseInt(numDays.getText());
	}

	

}