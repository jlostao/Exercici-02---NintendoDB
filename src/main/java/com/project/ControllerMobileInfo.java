package com.project;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerMobileInfo {

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

    @FXML
    private Label itemDetails;

    private String currentCategory;
    private int currentIndex;

    public void setItemDetails(String category, int index) {
        this.currentCategory = category;
        this.currentIndex = index;

        AppData appData = AppData.getInstance();
        JSONObject itemData = appData.getItemData(category, index);

        itemName.setText(itemData.getString("nom"));
        itemImage.setImage(new Image("assets/images/" + itemData.getString("imatge")));

        StringBuilder details = new StringBuilder();
        switch (category) {
            case "Personatges":
                details.append("Game: ").append(itemData.getString("nom_del_videojoc")).append("\n");
                details.append("Color: ").append(itemData.getString("color")).append("\n");
                break;
            case "Consoles":
                details.append("Release Date: ").append(itemData.getString("data")).append("\n");
                details.append("Processor: ").append(itemData.getString("procesador")).append("\n");
                details.append("Units Sold: ").append(itemData.getInt("venudes")).append("\n");
                details.append("Color: ").append(itemData.getString("color")).append("\n");
                break;
            case "Jocs":
                details.append("Year: ").append(itemData.getInt("any")).append("\n");
                details.append("Type: ").append(itemData.getString("tipus")).append("\n");
                details.append("Description: ").append(itemData.getString("descripcio")).append("\n");
                break;
        }
        itemDetails.setText(details.toString());
    }

    @FXML
    private void backToList(ActionEvent event) {
        UtilsViews.setView("Mobile1");
    }
}