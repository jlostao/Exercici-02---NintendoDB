package com.project;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class UtilsViews {

    public static StackPane parentContainer = new StackPane();
    private static Map<String, Parent> views = new HashMap<>();
    private static Map<String, Object> controllers = new HashMap<>();

    // Add one view to the list
    public static void addView(Class<?> clazz, String name, String fxmlPath) throws Exception {
        FXMLLoader loader = new FXMLLoader(clazz.getResource(fxmlPath));
        Parent view = loader.load();
        views.put(name, view);
        controllers.put(name, loader.getController());
    }

    // Get controller by view id (viewId)
    public static Object getController(String name) {
        return controllers.get(name);
    }

    // Set visible view by its id (viewId)
    public static void setView(String name) {
        parentContainer.getChildren().clear();
        parentContainer.getChildren().add(views.get(name));
    }

    // Set visible view by its id (viewId) with an animation
    public static void setViewAnimating(String viewId) {
        // Animation code here...
    }
}
