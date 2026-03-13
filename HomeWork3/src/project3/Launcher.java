package project3;

import javafx.application.Application;

/**
 * Launcher entry point for the JavaFX application.
 * This class exists as a workaround for IntelliJ not recognizing
 * Application subclasses as valid main classes directly.
 */
public class Launcher {

    /**
     * Main method — launches the JavaFX application.
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
