package com.project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class ControllerMobile0{
    
    public void switchToList() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout_mobile_list.fxml"));
        Parent itemTemplate = loader.load();
        
        Scene scene = new Scene(itemTemplate);
        
        itemTemplate.setOnMouseClicked(event -> {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        });

    }
    
    
}
