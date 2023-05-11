package controller;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class CountdownTimer {
    private Timer timer;
    private int seconds;
    private Label label;

    public CountdownTimer(int seconds, Label label) {
        this.seconds = seconds;
        this.label = label;
    }

    public void start() {
        label.setText("ОВфроОРВРООЫВ");
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (seconds <= 0) {
                    timer.cancel();
                } else {
                    Platform.runLater(() -> {
                        label.setText(String.valueOf(seconds));
                    });
                    seconds--;
                }
            }
        }, 0, 1000);
    }
}
