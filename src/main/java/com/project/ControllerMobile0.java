package com.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControllerMobile0 {

    @FXML
    private VBox categoryPane;

    @FXML
    private Button btnPersonatges;

    @FXML
    private Button btnJocs;

    @FXML
    private Button btnConsoles;

    @FXML
    public void initialize() {
        btnPersonatges.setOnAction(event -> showList("Personatges"));
        btnJocs.setOnAction(event -> showList("Jocs"));
        btnConsoles.setOnAction(event -> showList("Consoles"));
    }

    @FXML
    private void toPersonatgesList() {
        showList("Personatges");
    }

    @FXML
    private void toJocsList() {
        showList("Jocs");
    }

    @FXML
    private void toConsolesList() {
        showList("Consoles");
    }

    private void showList(String category) {
        AppData appData = AppData.getInstance();
        appData.load(category, (result) -> {
            if (result == null) {
                System.out.println("ControllerMobile0: Error loading data.");
            } else {
                UtilsViews.setView("Mobile1");
                ControllerMobile1 controller = (ControllerMobile1) UtilsViews.getController("Mobile1");
                controller.loadList(category);
            }
        });
    }
}
