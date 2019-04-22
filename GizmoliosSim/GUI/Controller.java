package GUI;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	
	public ComboBox<String> algorithms;

	public Button exit;
	
	public Slider speedControl;
	public Label hourPerSecond;
	
	public void exitClick() {
		System.exit(0);
	}
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	algorithms.getItems().addAll("Highest Penalty First", "Smart Penalty");
    	
    	speedControl.setMin(0);
    	speedControl.setMax(12);
    	speedControl.setValue(6);
    	speedControl.setShowTickLabels(true);
    	speedControl.setMajorTickUnit(1);
    	speedControl.setBlockIncrement(1);
    	
    	
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
    

}