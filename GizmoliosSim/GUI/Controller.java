package GUI;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	
	public ComboBox<String> algorithms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	algorithms.getItems().addAll("Highest Penalty First", "Smart Penalty");
    
    }
    
    

}