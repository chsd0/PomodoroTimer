package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class TimerController {
    @FXML
    private Label timerLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button restartButton;
    boolean flag = true;
    private CountdownTimer countdownTimer;

    public void initialize() {
        countdownTimer = new CountdownTimer(this::updateTimerLabel);
        startButton.setText("Start");
        startButton.setDisable(false);
    }


    @FXML
    private void handleStartButton() {
        if (flag){
            countdownTimer.start();
            flag = false;
            return;
        }
        System.out.println(countdownTimer.isRunning());
        if (countdownTimer.isRunning()) {
            startButton.setText("Start");
            countdownTimer.pause();
        } else {
            countdownTimer.resume();
            startButton.setText("Stop");
        }
    }

    @FXML
    private void handleNextButton() {
        countdownTimer.stop();
        countdownTimer.goToNextStage();
        startButton.setDisable(false);
        nextButton.setDisable(false);
    }

    @FXML
    private void handleRestartButton() {
        countdownTimer.restartStage();
        startButton.setDisable(true);
        nextButton.setDisable(false);
    }

    private void updateTimerLabel(String time) {
        timerLabel.setText(time);
    }
}
