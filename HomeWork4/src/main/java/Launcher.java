package main.java;

import javafx.application.Application;

/** Launcher class that delegates to Main to avoid JavaFX module classpath issues. */
public class Launcher {
    /**
     * Application entry point.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}