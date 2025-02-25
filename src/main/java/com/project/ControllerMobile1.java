package com.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMobile1 implements Initializable {

    public static String selectedTheme = "Jocs"; // Default theme

    @FXML
    private VBox yPane;  // This is the VBox where the list will be added

    @FXML
    private ListView<AnchorPane> listView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // This is called when the FXML is loaded, but you can leave it empty if you want to set up in initializeView
    }

    // This method is called by setViewAnimating after the view has been set
    public void initializeView() {
        System.out.println("Initializing view for: " + selectedTheme);
        loadList(selectedTheme); // Load the list data after the view is initialized
    }

    // Load the list data based on the selected theme
    public void loadList(String category) {
        AppData appData = AppData.getInstance();
        JSONArray data = appData.getData(category);

        listView.getItems().clear();

        URL resource = getClass().getResource("/assets/template_list_item.fxml");
        if (resource == null) {
            System.out.println("FXML resource not found: /assets/template_list_item.fxml");
            return;
        }

        for (int i = 0; i < data.length(); i++) {
            JSONObject itemObject = data.getJSONObject(i);

            if (itemObject.has("nom")) {
                String nom = itemObject.getString("nom");
                String imagePath = "assets/images/" + itemObject.getString("imatge");
                Image image = new Image(imagePath);
                try {
                    FXMLLoader loader = new FXMLLoader(resource);
                    Parent itemTemplate = loader.load();
                    ControllerListItem itemController = loader.getController();
                    itemController.setText(nom);
                    itemController.setImage(image);
                    final int index = i;
                    itemTemplate.setOnMouseClicked(event -> showInfo(category, index));
                    listView.getItems().add((AnchorPane) itemTemplate);
                } catch (Exception e) {
                    System.out.println("ControllerMobile1: Error loading list item.");
                    e.printStackTrace();
                }
            }
        }
    }

    private void showInfo(String category, int index) {
        UtilsViews.setView("MobileInfo");
        ControllerMobileInfo controller = (ControllerMobileInfo) UtilsViews.getController("MobileInfo");
        controller.setItemDetails(category, index);
    }

    @FXML
    private void backToSelect(ActionEvent event) {
        UtilsViews.setView("Mobile0");
    }
}
