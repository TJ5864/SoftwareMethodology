package main.java;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Controller for the Chicago-style pizza customization view.
 */
public class ChicagoStyleController {

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

    private final ToggleGroup sizeGroup = new ToggleGroup();
    private final DecimalFormat money = new DecimalFormat("0.00");

    private final PizzaFactory chicagoFactory = new ChicagoPizza();
    private Pizza currentPizza;
    private Order currentOrder;

    /**
     * Sets the current order used by this view.
     * @param order current order
     */
    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }

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
                currentPizza = chicagoFactory.createDeluxe();
                break;
            case "BBQ Chicken":
                currentPizza = chicagoFactory.createBBQChicken();
                break;
            case "Meatzza":
                currentPizza = chicagoFactory.createMeatzza();
                break;
            case "Build Your Own":
                currentPizza = chicagoFactory.createBuildYourOwn();
                break;
            default:
                currentPizza = null;
                return;
        }

        tfCrust.setText(currentPizza.getCrust().toString());

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

        if (currentOrder == null) {
            showError("Current order is not set.");
            return;
        }

        Pizza pizzaToAdd = duplicatePizza(currentPizza);
        currentOrder.addPizza(pizzaToAdd);

        showInfo("Pizza added to cart.");
        resetForNextPizza();
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
        tfPrice.setText(money.format(currentPizza.price()));
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
        //System.out.println("Looking for: " + fileName);
        //System.out.println("Stream is null: " + (stream == null));

        if (stream == null) {
            imgPizza.setImage(null);
            return;
        }

        try {
            Image image = new Image(stream);
            //System.out.println("Image error: " + image.isError());
            //System.out.println("Image width: " + image.getWidth());
            imgPizza.setImage(image);
        } catch (Exception e) {
            //System.out.println("Exception: " + e.getMessage());
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
                copy = chicagoFactory.createDeluxe();
                break;
            case "BBQ Chicken":
                copy = chicagoFactory.createBBQChicken();
                break;
            case "Meatzza":
                copy = chicagoFactory.createMeatzza();
                break;
            case "Build Your Own":
                copy = chicagoFactory.createBuildYourOwn();
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
     * Formats enum names for display.
     * @param text enum text
     * @return formatted text
     */
    private String formatEnum(String text) {
        String lower = text.toLowerCase().replace("_", " ");
        String[] words = lower.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }

        return result.toString().trim();
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