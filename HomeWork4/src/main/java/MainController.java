package main.java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controller for the main menu view of RU Pizza.
 * Handles navigation to Chicago-style, New York-style,
 * current order, and all orders views.
 */
public class MainController {

    @FXML
    private BorderPane root;

    /**
     * Navigates to the Chicago-Style pizza ordering view.
     */
    @FXML
    private void handleChicagoStyle() {
        loadScene("/ChicagoStyle-view.fxml", "RU Pizza - Chicago Style");
    }

    /**
     * Navigates to the New York-Style pizza ordering view.
     */
    @FXML
    private void handleNewYorkStyle() {
        loadScene("/NYStyle-view.fxml", "RU Pizza - New York Style");
    }

    /**
     * Navigates to the Current Order view.
     */
    @FXML
    private void handleCurrentOrder() {
        loadScene("/CurrentOrder-view.fxml", "RU Pizza - Current Order");
    }

    /**
     * Navigates to the View All Orders view.
     */
    @FXML
    private void handleViewAllOrders() {
        loadScene("/AllOrders-view.fxml", "RU Pizza - All Orders");
    }

    /**
     * Loads a new scene into the current stage.
     * @param fxmlPath the path to the FXML file
     * @param title the title to set on the stage
     */
    private void loadScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}