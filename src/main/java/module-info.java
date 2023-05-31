module com.example.pomodorotimer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.pomodorotimer to javafx.fxml;
    exports com.example.pomodorotimer;
    exports controller;
    opens controller to javafx.fxml;
}