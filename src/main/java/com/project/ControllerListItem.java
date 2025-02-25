package com.project;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ControllerListItem {

    @FXML
    private ImageView itemImage;

    @FXML
    private Text itemName;

    public void setText(String text) {
        itemName.setText(text);
    }

    public void setImage(Image image) {
        itemImage.setImage(image);
    }
}
