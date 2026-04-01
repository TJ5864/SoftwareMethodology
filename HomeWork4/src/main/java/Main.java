package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
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
