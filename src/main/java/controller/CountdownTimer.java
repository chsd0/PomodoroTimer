package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class CountdownTimer {
    private static final int WORK_TIME = 2; // Время работы в минутах
    private static final int SHORT_BREAK_TIME = 5; // Время короткого перерыва в минутах
    private static final int LONG_BREAK_TIME = 20; // Время длительного перерыва в минутах
    private static final int CYCLES_BEFORE_LONG_BREAK = 4; // Количество циклов работы до длительного перерыва

    private int cycleCount = 0;
    private int currentTime;
    private boolean isWorkingTime = true;
    private boolean isPaused = false;
    private boolean isRunning = false;
    private int absCycleCount = 0;

    private int remainingTime;
    private TimerListener listener;
    private Timeline timeline;
    private Data data = new Data();
    public CountdownTimer(TimerListener listener) {
        this.listener = listener;
    }

    public void start() {

        isRunning = true;
        if (isWorkingTime) {
            currentTime = WORK_TIME ;
            listener.onTimerUpdate(formatTime(currentTime));
        } else {
            currentTime = SHORT_BREAK_TIME * 60;
            listener.onTimerUpdate(formatTime(currentTime));
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            currentTime--;
            if (currentTime <= 0) {
                goToNextStage();
                data.addWorkingCycle();
            } else {
                listener.onTimerUpdate(formatTime(currentTime));
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void pause() {
        if (timeline != null) {
            remainingTime = currentTime;
            timeline.pause();
            isPaused = true;
            isRunning = false;
            System.out.println("[EQ");
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
    public void resume() {
        timeline.play();
        isRunning = true;
    }

    protected void goToNextStage() {
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
    }

    public void restartStage() {
        if (timeline != null) {
            timeline.stop();
        }
        start();
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
