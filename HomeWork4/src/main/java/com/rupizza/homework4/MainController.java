package com.rupizza.homework4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    public void initialize() {

    }


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
