module com.rupizza.homework4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens main.java to javafx.fxml;
    exports main.java;
}
