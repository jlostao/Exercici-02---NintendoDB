package com.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.Initializable;

public class ControllerDesktop implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private VBox yPane;

    @FXML
    private AnchorPane info;

    String opcions[] = { "Personatges", "Jocs", "Consoles" };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBox.getItems().addAll(opcions);
        choiceBox.setValue(opcions[0]);
        loadList();
        choiceBox.setOnAction((event) -> {loadList(); });
    }

    public void loadList() {
        String opcio = choiceBox.getValue();
        AppData appData = AppData.getInstance();
        showLoading();

        appData.load(opcio, (result) -> {
            if (result == null) {
                System.out.println("ControllerDesktop: Error loading data.");
            } else {
                try {
                    showList();
                } catch (Exception e) {
                    System.out.println("ControllerDesktop: Error showing list.");
                    e.printStackTrace();
                }
            }
        });
    }

    public void showList() throws Exception {
        String opcioSeleccionada = choiceBox.getValue();
        AppData appData = AppData.getInstance();
        JSONArray dades = appData.getData(opcioSeleccionada);

        URL resource = this.getClass().getResource("/assets/template_list_item.fxml");
        if (resource == null) {
            throw new Exception("FXML resource not found: /assets/template_list_item.fxml");
        }

        yPane.getChildren().clear();

        for (int i = 0; i < dades.length(); i++) {
            JSONObject consoleObject = dades.getJSONObject(i);

            if (consoleObject.has("nom")) {
                String nom = consoleObject.getString("nom");
                String imagePath = "assets/images/" + consoleObject.getString("imatge");
                Image imatge = new Image(imagePath);
                FXMLLoader loader = new FXMLLoader(resource);
                Parent itemTemplate = loader.load();
                ControllerListItem itemController = loader.getController();
                itemController.setText(nom);
                itemController.setImage(imatge);
                final String type = opcioSeleccionada;
                final int index = i;
                itemTemplate.setOnMouseClicked(event -> {
                    showInfo(type, index);
                });

                yPane.getChildren().add(itemTemplate);
            }
        }
    }

    public void showLoading() {
        yPane.getChildren().clear();
        ProgressIndicator progressIndicator = new ProgressIndicator();
        yPane.getChildren().add(progressIndicator);
    }

    void showInfo(String type, int index) {
        AppData appData = AppData.getInstance();
        JSONObject dades = appData.getItemData(type, index);

        URL resource;

        if (type.equals("Consoles")) {
            resource = this.getClass().getResource("/assets/template_info_consoles.fxml");
        } else if (type.equals("Jocs")) {
            resource = this.getClass().getResource("/assets/template_info_jocs.fxml");
        } else {
            resource = this.getClass().getResource("/assets/template_info_personatges.fxml");
        }

        if (resource == null) {
            System.out.println("FXML resource not found for type: " + type);
            return;
        }

        info.getChildren().clear();

        try {
            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerInfoItem itemController = loader.getController();
            itemController.setImage(new Image("assets/images/" + dades.getString("imatge")));
            itemController.setNom(dades.getString("nom"));
            switch (type) {
                case "Personatges":
                    itemController.setNomJoc(dades.getString("nom_del_videojoc"));
                    itemController.setColor(dades.getString("color"));
                    break;
                case "Consoles":
                    itemController.setData(dades.getString("data"));
                    itemController.setProcesador(dades.getString("procesador"));
                    itemController.setVenudes(dades.getInt("venudes"));
                    itemController.setColor(dades.getString("color"));
                    break;
                case "Jocs":
                    itemController.setAny(dades.getInt("any"));
                    itemController.setTipus(dades.getString("tipus"));
                    itemController.setDescripcio(dades.getString("descripcio"));
                    break;
            }

            info.getChildren().add(itemTemplate);
            AnchorPane.setTopAnchor(itemTemplate, 0.0);
            AnchorPane.setRightAnchor(itemTemplate, 0.0);
            AnchorPane.setBottomAnchor(itemTemplate, 0.0);
            AnchorPane.setLeftAnchor(itemTemplate, 0.0);

        } catch (Exception e) {
            System.out.println("ControllerDesktop: Error showing info.");
            e.printStackTrace();
        }
    }
}


