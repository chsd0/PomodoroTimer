package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class TimerController {
    @FXML
    private Label timerLabel;
    @FXML
    private Button startButton;
    @FXML
    private StackPane stackPane;

    @FXML
    private Button mainButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button historyButton;
    boolean flag = true;
    private CountdownTimer countdownTimer;

    public void initialize() {
        countdownTimer = new CountdownTimer(this::updateTimerLabel);
    }


    @FXML
    private void handleStartButton() {
        if (flag){
            countdownTimer.start();
            flag = false;
            startButton.setText("Stop");
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
    }

    @FXML
    private void handleRestartButton() {
        countdownTimer.restartStage();
    }

    private void updateTimerLabel(String time) {
        timerLabel.setText(time);
    }
    @FXML
    protected void handleSettingsButton() {
        // Обработчик события для кнопки settingsButton
        // Переключение на сцену настроек
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pomodorotimer/settings-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = settingsButton.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleHistoryButton() {
        // Обработчик события для кнопки historyButton
        // Переключение на сцену истории
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pomodorotimer/history-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = historyButton.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleMenuButton() {
        // Обработчик события для кнопки mainButton
        // Переключение на главную сцену
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pomodorotimer/hello-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = mainButton.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
