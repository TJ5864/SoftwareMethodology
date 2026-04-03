package main.java;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Controller for the New York-style pizza customization view.
 */
public class NYStyleController {

    @FXML
    private ComboBox<String> cbPizzaType;

    @FXML
    private TextField tfCrust;

    @FXML
    private RadioButton rbSmall;

    @FXML
    private RadioButton rbMedium;

    @FXML
    private RadioButton rbLarge;

    @FXML
    private ListView<Topping> lvAvailableToppings;

    @FXML
    private ListView<Topping> lvSelectedToppings;

    @FXML
    private TextField tfPrice;

    @FXML
    private ImageView imgPizza;

    @FXML
    private Spinner<Integer> spinnerQuantity;

    @FXML
    private Button buttonMainMenu;

    private final ToggleGroup sizeGroup = new ToggleGroup();
    private final DecimalFormat money = new DecimalFormat("0.00");

    private final PizzaFactory nyFactory = new NYPizza();
    private Pizza currentPizza;

    /**
     * Initializes GUI controls.
     */
    @FXML
    public void initialize() {
        rbSmall.setToggleGroup(sizeGroup);
        rbMedium.setToggleGroup(sizeGroup);
        rbLarge.setToggleGroup(sizeGroup);

        cbPizzaType.setItems(FXCollections.observableArrayList("Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"));

        lvAvailableToppings.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvSelectedToppings.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tfCrust.setEditable(false);
        tfPrice.setEditable(false);

        sizeGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (currentPizza != null) {
                updatePizzaSize();
                updatePrice();
            }
        });

        spinnerQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));

        spinnerQuantity.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (currentPizza != null) {
                updatePrice();
            }
        });

        clearView();
    }

    /**
     * Handles pizza type selection from combo box.
     */
    @FXML
    private void handlePizzaTypeSelection() {
        String type = cbPizzaType.getValue();
        if (type == null) {
            return;
        }

        switch (type) {
            case "Deluxe":
                currentPizza = nyFactory.createDeluxe();
                break;
            case "BBQ Chicken":
                currentPizza = nyFactory.createBBQChicken();
                break;
            case "Meatzza":
                currentPizza = nyFactory.createMeatzza();
                break;
            case "Build Your Own":
                currentPizza = nyFactory.createBuildYourOwn();
                break;
            default:
                currentPizza = null;
                return;
        }

        rbSmall.setSelected(true);
        updatePizzaSize();
        updateCrustField();
        refreshToppingLists();
        updatePrice();
        updateImage();
    }

    /**
     * Adds a selected topping to build-your-own pizza.
     */
    @FXML
    private void handleAddSelectedTopping() {
        if (currentPizza == null) {
            showError("Please select a pizza type first.");
            return;
        }

        if (!isBuildYourOwn()) {
            showError("Only Build Your Own pizzas can be customized.");
            return;
        }

        Topping topping = lvAvailableToppings.getSelectionModel().getSelectedItem();
        if (topping == null) {
            showError("Please select a topping to add.");
            return;
        }

        BuildYourOwn byo = (BuildYourOwn) currentPizza;
        boolean added = byo.addTopping(topping);
        if (!added) {
            showError("You can add up to 5 toppings only.");
            return;
        }

        refreshToppingLists();
        updatePrice();
    }

    /**
     * Removes a selected topping from build-your-own pizza.
     */
    @FXML
    private void handleRemoveSelectedTopping() {
        if (currentPizza == null) {
            showError("Please select a pizza type first.");
            return;
        }

        if (!isBuildYourOwn()) {
            showError("Only Build Your Own pizzas can be customized.");
            return;
        }

        Topping topping = lvSelectedToppings.getSelectionModel().getSelectedItem();
        if (topping == null) {
            showError("Please select a topping to remove.");
            return;
        }

        BuildYourOwn byo = (BuildYourOwn) currentPizza;
        byo.removeTopping(topping);

        refreshToppingLists();
        updatePrice();
    }

    /**
     * Adds current pizza to the current order.
     */
    @FXML
    private void handleAddToCart() {
        if (currentPizza == null) {
            showError("Please select a pizza before adding it to the cart.");
            return;
        }

        int quantity = spinnerQuantity.getValue();

        for (int i = 0; i < quantity; i++) {
            Pizza pizzaToAdd = duplicatePizza(currentPizza);
            Store.currentOrder.addPizza(pizzaToAdd);
        }

        showInfo(quantity + " pizza(s) added to cart.");
        resetForNextPizza();
        spinnerQuantity.getValueFactory().setValue(1);
    }

    @FXML
    private void handleMainMenu() {
        MainController.loadScene(buttonMainMenu, "/main-view.fxml", "RU Pizza!");
    }

    /**
     * Updates the pizza size from radio buttons.
     */
    private void updatePizzaSize() {
        if (rbSmall.isSelected()) {
            currentPizza.setSize(Size.SMALL);
        } else if (rbMedium.isSelected()) {
            currentPizza.setSize(Size.MEDIUM);
        } else if (rbLarge.isSelected()) {
            currentPizza.setSize(Size.LARGE);
        }
    }

    /**
     * Updates crust display.
     */
    private void updateCrustField() {
        if (currentPizza == null || currentPizza.getCrust() == null) {
            tfCrust.clear();
            return;
        }
        tfCrust.setText(currentPizza.getCrust().toString());
    }

    /**
     * Refreshes toppings list views.
     */
    private void refreshToppingLists() {
        if (currentPizza == null) {
            lvAvailableToppings.getItems().clear();
            lvSelectedToppings.getItems().clear();
            return;
        }

        ArrayList<Topping> selected = currentPizza.getToppings();
        ArrayList<Topping> available = new ArrayList<>(Arrays.asList(Topping.values()));
        available.removeAll(selected);

        lvAvailableToppings.setItems(FXCollections.observableArrayList(available));
        lvSelectedToppings.setItems(FXCollections.observableArrayList(selected));
    }

    /**
     * Updates displayed price.
     */
    private void updatePrice() {
        if (currentPizza == null) {
            tfPrice.clear();
            return;
        }
        tfPrice.setText(money.format(currentPizza.price() * spinnerQuantity.getValue()));
    }

    private void updateImage() {
        if (cbPizzaType.getValue() == null) {
            imgPizza.setImage(null);
            return;
        }

        String fileName;
        switch (cbPizzaType.getValue()) {
            case "Deluxe":
                fileName = "/images/deluxe-dd.png";
                break;
            case "BBQ Chicken":
                fileName = "/images/bbqchicken-pan.png";
                break;
            case "Meatzza":
                fileName = "/images/meatzza-stuffed.png";
                break;
            case "Build Your Own":
                fileName = "/images/buildyourown-pan.png";
                break;
            default:
                fileName = null;
        }

        if (fileName == null) {
            imgPizza.setImage(null);
            return;
        }

        var stream = getClass().getResourceAsStream(fileName);

        if (stream == null) {
            imgPizza.setImage(null);
            return;
        }

        try {
            Image image = new Image(stream);
            imgPizza.setImage(image);
        } catch (Exception e) {
            imgPizza.setImage(null);
        }
    }

    /**
     * Checks whether the current pizza is build-your-own.
     * @return true if build-your-own
     */
    private boolean isBuildYourOwn() {
        return "Build Your Own".equals(cbPizzaType.getValue());
    }

    /**
     * Clears the screen after adding a pizza.
     */
    private void resetForNextPizza() {
        cbPizzaType.getSelectionModel().clearSelection();
        clearView();
    }

    /**
     * Clears the view controls.
     */
    private void clearView() {
        currentPizza = null;
        tfCrust.clear();
        tfPrice.clear();
        lvAvailableToppings.getItems().clear();
        lvSelectedToppings.getItems().clear();
        imgPizza.setImage(null);
        sizeGroup.selectToggle(null);
    }

    /**
     * Duplicates the current pizza before storing it in the order.
     * @param original original pizza
     * @return copied pizza
     */
    private Pizza duplicatePizza(Pizza original) {
        Pizza copy;

        switch (cbPizzaType.getValue()) {
            case "Deluxe":
                copy = nyFactory.createDeluxe();
                break;
            case "BBQ Chicken":
                copy = nyFactory.createBBQChicken();
                break;
            case "Meatzza":
                copy = nyFactory.createMeatzza();
                break;
            case "Build Your Own":
                copy = nyFactory.createBuildYourOwn();
                for (Topping topping : original.getToppings()) {
                    copy.getToppings().add(topping);
                }
                break;
            default:
                throw new IllegalStateException("Invalid pizza type.");
        }

        copy.setSize(original.getSize());
        return copy;
    }

    /**
     * Shows error alert.
     * @param message error message
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Shows information alert.
     * @param message info message
     */
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
