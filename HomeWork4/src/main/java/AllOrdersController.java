package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.text.DecimalFormat;

/**
 * Controller for the all orders view.
 * Displays all placed orders and allows exporting them to a file.
 */
public class AllOrdersController {

    @FXML
    private TextArea txtAllOrders;

    @FXML
    private Button btnExportAllOrdersToFile;

    @FXML
    private Button btnMainMenu;

    private static final double NJ_TAX_RATE = 0.06625;
    private final DecimalFormat money = new DecimalFormat("0.00");

    /**
     * Initializes the all orders view.
     */
    @FXML
    public void initialize() {
        txtAllOrders.setEditable(false);
        refreshView();
    }

    /**
     * Handles exporting all placed orders to a text file.
     */
    @FXML
    private void handleExportAllOrdersToFile() {
        if (Store.orderManager.getOrders().isEmpty()) {
            showError("There are no placed orders to export.");
            return;
        }

        String path = "HomeWork4/orders.txt";

        try {
            Store.orderManager.exportOrders(path);
            showInfo("Orders exported successfully to:\n" + path);
        } catch (RuntimeException e) {
            showError(e.getMessage());
        }
    }

    /**
     * Returns to the main menu.
     */
    @FXML
    private void handleMainMenu() {
        MainController.loadScene(btnMainMenu, "/main-view.fxml", "RU Pizza!");
    }

    /**
     * Refreshes the all orders display.
     */
    private void refreshView() {
        txtAllOrders.setText(buildAllOrdersText());
    }

    /**
     * Builds the display text for all placed orders.
     * @return formatted text for all orders
     */
    private String buildAllOrdersText() {
        if (Store.orderManager.getOrders().isEmpty()) {
            return "No orders have been placed yet.";
        }

        StringBuilder output = new StringBuilder();

        for (Order order : Store.orderManager.getOrders()) {
            double subtotal = order.getTotal();
            double salesTax = subtotal * NJ_TAX_RATE;
            double total = subtotal + salesTax;

            output.append("Order #").append(order.getNumber()).append("\n");

            for (Pizza pizza : order.getPizzas()) {
                output.append("  ").append(pizza).append("\n");
            }

            output.append("  Order Total: $").append(money.format(total)).append("\n\n");
        }

        return output.toString();
    }

    /**
     * Shows an error alert.
     * @param message error message
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Shows an information alert.
     * @param message information message
     */
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}