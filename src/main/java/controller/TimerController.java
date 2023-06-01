package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    @FXML
    public Button shortBreakMinus;
    @FXML
    public Button shortBreakPlus;
    @FXML
    public TextField shortBreakField;
    @FXML
    public Button longBreakMinus;
    @FXML
    public Button longBreakPlus;
    @FXML
    public TextField longBreakField;
    @FXML
    public Button pomodoroMinus;
    @FXML
    public Button pomodoroPlus;
    @FXML
    public TextField pomodoroField;


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

    public void onPomodoroPlusClicked(MouseEvent mouseEvent) {
        if (CountdownTimer.getWorkTime()<99) {
            CountdownTimer.setWorkTime(CountdownTimer.getWorkTime() + 1);
            pomodoroField.setText(String.valueOf(CountdownTimer.getWorkTime()));
        }
    }

    public void onPomodoroMinusClicked(MouseEvent mouseEvent) {
        if (CountdownTimer.getWorkTime()>1) {
            CountdownTimer.setWorkTime(CountdownTimer.getWorkTime() - 1);
            pomodoroField.setText(String.valueOf(CountdownTimer.getWorkTime()));
        }
    }

    public void onLongBreakPlusClicked(MouseEvent mouseEvent) {
        if (CountdownTimer.getLongBreakTime() < 99) {
            CountdownTimer.setLongBreakTime(CountdownTimer.getLongBreakTime() + 1);
            longBreakField.setText(String.valueOf(CountdownTimer.getLongBreakTime()));
        }
    }

    public void onLongBreakMinusClicked(MouseEvent mouseEvent) {
            if (CountdownTimer.getLongBreakTime()>1) {
                CountdownTimer.setLongBreakTime(CountdownTimer.getLongBreakTime() - 1);
                longBreakField.setText(String.valueOf(CountdownTimer.getLongBreakTime()));
        }
    }

    public void onShortBreakPlusClicked(MouseEvent mouseEvent) {
        if (CountdownTimer.getShortBreakTime() < 99) {
            CountdownTimer.setShortBreakTime(CountdownTimer.getShortBreakTime() + 1);
            shortBreakField.setText(String.valueOf(CountdownTimer.getShortBreakTime()));
        }
    }

    public void onShortBreakMinusClicked(MouseEvent mouseEvent) {
        if (CountdownTimer.getShortBreakTime() >1) {
            CountdownTimer.setShortBreakTime(CountdownTimer.getShortBreakTime() - 1);
            shortBreakField.setText(String.valueOf(CountdownTimer.getShortBreakTime()));
        }
    }

    public void checkSettingsFields(MouseEvent mouseEvent) {

        pomodoroField.setText(String.valueOf(CountdownTimer.getWorkTime()));
        shortBreakField.setText(String.valueOf(CountdownTimer.getShortBreakTime()));
        longBreakField.setText(String.valueOf(CountdownTimer.getLongBreakTime()));


    }

}
