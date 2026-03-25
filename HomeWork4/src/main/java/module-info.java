module com.rupizza.homework4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.rupizza.homework4 to javafx.fxml;
    exports com.rupizza.homework4;
}