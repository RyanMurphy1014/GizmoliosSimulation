package GUI;


import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
    }
    
    public Button button;
    
    
    
    public void click(){
    	System.out.println("The button has been clicked.");
    	
    }
    
    
}