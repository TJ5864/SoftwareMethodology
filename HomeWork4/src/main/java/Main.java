package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** JavaFX Application entry point for RU Pizza. Loads the main view and sets up the primary stage. */
public class Main extends Application {
    /**
     * Starts the JavaFX application and displays the main menu.
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/main-view.fxml"));
        //System.out.println(Main.class.getResource("/project4/resources/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 604, 490);
        stage.setTitle("RU Pizza!");
        stage.setScene(scene);
        stage.show();
    }
}
