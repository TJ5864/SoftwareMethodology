package main.java;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class CurrentOrderController {

    @FXML
    private Button btnRemoveSelected;

    @FXML
    private Button btnCancelOrder;

    @FXML
    private Button btnCompleteOrder;

    @FXML
    private ListView<Pizza> lvPizzasList;

    @FXML
    private Button btnMainMenu;

    @FXML
    private TextField txtSubTotal;

    @FXML
    private TextField txtTotal;

    private final DecimalFormat money = new DecimalFormat("0.00");

    @FXML
    public void initialize() {
        txtSubTotal.setEditable(false);
        txtTotal.setEditable(false);
        refreshView();
    }

    @FXML
    private void handleRemoveSelected() {
        Pizza selectedPizza = lvPizzasList.getSelectionModel().getSelectedItem();

        if (selectedPizza == null) {
            showError("Please select a pizza to remove.");
            return;
        }

        Store.currentOrder.removePizza(selectedPizza);
        refreshView();
        showInfo("Pizza removed from current order.");
    }

    @FXML
    private void handleCancelOrder() {
        if (Store.currentOrder.getPizzas().isEmpty()) {
            showError("There is no current order to cancel.");
            return;
        }

        Store.currentOrder = Store.orderManager.createOrder();
        refreshView();
        showInfo("Current order canceled.");
    }

    @FXML
    private void handleCompleteOrder() {
        if (Store.currentOrder.getPizzas().isEmpty()) {
            showError("There is no pizza in the current order.");
            return;
        }

        int completedOrderNumber = Store.currentOrder.getNumber();
        Store.orderManager.placeOrder(Store.currentOrder);
        Store.currentOrder = Store.orderManager.createOrder();

        refreshView();
        showInfo("Order #" + completedOrderNumber + " has been placed.");
    }

    @FXML
    private void handleMainMenu() {
        MainController.loadScene(btnMainMenu, "/main-view.fxml", "RU Pizza!");
    }

    /**
     * Refreshes the whole current order view.
     */
    private void refreshView() {
        refreshPizzaList();
        updateTotals();
    }

    /**
     * Refreshes the ListView with pizzas from the current order.
     */
    private void refreshPizzaList() {
        lvPizzasList.setItems(FXCollections.observableArrayList(Store.currentOrder.getPizzas()));
    }

    /**
     * Updates subtotal and total fields.
     */
    private void updateTotals() {

        double NJtaxRate = 0.06625;
        double subtotal = Store.currentOrder.getTotal();
        double total = subtotal * ( 1 + NJtaxRate); // change this later if you add tax

        txtSubTotal.setText(money.format(subtotal));
        txtTotal.setText(money.format(total));
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
     * @param message info message
     */
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}