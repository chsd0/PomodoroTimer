package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class CountdownTimer {
    private static final int WORK_TIME = 25; // Время работы в минутах
    private static final int SHORT_BREAK_TIME = 5; // Время короткого перерыва в минутах
    private static final int LONG_BREAK_TIME = 20; // Время длительного перерыва в минутах
    private static final int CYCLES_BEFORE_LONG_BREAK = 4; // Количество циклов работы до длительного перерыва

    private int cycleCount = 0;
    private int currentTime;
    private boolean isWorkingTime = true;
    private TimerListener listener;
    private Timeline timeline;

    public CountdownTimer(TimerListener listener) {
        this.listener = listener;
    }

    public void start() {
        if (isWorkingTime) {
            currentTime = WORK_TIME * 60;
            listener.onTimerUpdate(formatTime(currentTime));
        } else {
            currentTime = SHORT_BREAK_TIME * 60;
            listener.onTimerUpdate(formatTime(currentTime));
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            currentTime--;
            if (currentTime <= 0) {
                cycleCount++;
                if (isWorkingTime) {
                    if (cycleCount >= CYCLES_BEFORE_LONG_BREAK) {
                        cycleCount = 0;
                        currentTime = LONG_BREAK_TIME * 60;
                        listener.onTimerUpdate(formatTime(currentTime));
                    } else {
                        currentTime = SHORT_BREAK_TIME * 60;
                        listener.onTimerUpdate(formatTime(currentTime));
                    }
                } else {
                    currentTime = WORK_TIME * 60;
                    listener.onTimerUpdate(formatTime(currentTime));
                }
                isWorkingTime = !isWorkingTime;
            } else {
                listener.onTimerUpdate(formatTime(currentTime));
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public interface TimerListener {
        void onTimerUpdate(String time);
    }
}
