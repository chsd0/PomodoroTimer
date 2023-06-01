module com.example.pomodorotimer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pomodorotimer to javafx.fxml;
    exports com.example.pomodorotimer;
    exports controller;
    opens controller to javafx.fxml;
}