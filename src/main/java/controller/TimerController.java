package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TimerController {
    @FXML
    private Label timerLabel;
    @FXML
    private Button startButton;

    @FXML
    Pane pane;

    private CountdownTimer countdownTimer;

    public void initialize() {
        countdownTimer = new CountdownTimer(this::updateTimerLabel);
        pane.setBackground(new Background(new BackgroundFill(Color.GRAY,null,null)));
    }

    @FXML
    private void handleStartButton() {
        startButton.setDisable(true);
        countdownTimer.start();
    }

    private void updateTimerLabel(String time) {
        timerLabel.setText(time);
        //timerLabel.setFont(Font.font("Arial", 24)); // Установка размера шрифта 24
    }
}
