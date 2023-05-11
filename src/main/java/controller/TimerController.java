package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TimerController {
    @FXML
    private Button startButton;

    @FXML
    private Label timerLabel;

    private CountdownTimer countdownTimer;

    @FXML
    private void initialize() {
        countdownTimer = new CountdownTimer(60, timerLabel); // Установите здесь необходимое количество секунд для таймера и метку
    }

    @FXML
    private void handleStartButtonAction() {
        countdownTimer.start();
    }
}
