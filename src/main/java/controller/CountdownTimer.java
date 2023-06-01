package controller;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.File;
import java.util.Objects;

public class CountdownTimer {
    private static  int WORK_TIME = 25; // Время работы в минутах
    private static  int SHORT_BREAK_TIME = 5; // Время короткого перерыва в минутах
    private static  int LONG_BREAK_TIME = 20; // Время длительного перерыва в минутах
    private static  int CYCLES_BEFORE_LONG_BREAK = 5; // Количество циклов работы до длительного перерыва

    private int cycleCount = 1;
    private int currentTime;
    private boolean isWorkingTime = true;
    private boolean isPaused = false;
    private boolean isRunning = false;

    private int remainingTime;
    private TimerListener listener;
    private Timeline timeline;
    private Data data = new Data();

    public static int getWorkTime() {
        return WORK_TIME;
    }

    public static void setWorkTime(int workTime) {
        WORK_TIME = workTime;
    }

    public static int getShortBreakTime() {
        return SHORT_BREAK_TIME;
    }

    public static void setShortBreakTime(int shortBreakTime) {
        SHORT_BREAK_TIME = shortBreakTime;
    }

    public static int getLongBreakTime() {
        return LONG_BREAK_TIME;
    }

    public static void setLongBreakTime(int longBreakTime) {
        LONG_BREAK_TIME = longBreakTime;
    }

    public CountdownTimer(TimerListener listener) {
        this.listener = listener;
    }

    public void start() {
        isRunning = true;
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
                if(cycleCount%2==1) {
                    data.addWorkingCycle();
                } else {
                    data.addBreakCycle();
                }
                goToNextStage();
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
        System.out.println(cycleCount);
        System.out.println(isWorkingTime);
        if (cycleCount == 1 || cycleCount == 3 || cycleCount == 5 || cycleCount==7){
            currentTime = WORK_TIME * 60;
            listener.onTimerUpdate(formatTime(currentTime));
        } else if (cycleCount == 8) {
            cycleCount = 0;
            currentTime = LONG_BREAK_TIME * 60;
            listener.onTimerUpdate(formatTime(currentTime));
        } else {
            currentTime = SHORT_BREAK_TIME * 60;
            listener.onTimerUpdate(formatTime(currentTime));
        }
//        if (isWorkingTime) {
//            if (cycleCount == CYCLES_BEFORE_LONG_BREAK) {
//                cycleCount = 0;
//                currentTime = LONG_BREAK_TIME * 60;
//                listener.onTimerUpdate(formatTime(currentTime));
//            } else {
//                currentTime = SHORT_BREAK_TIME * 60;
//                listener.onTimerUpdate(formatTime(currentTime));
//            }
//        } else {
//            currentTime = WORK_TIME * 60;
//            listener.onTimerUpdate(formatTime(currentTime));
//        }
//        isWorkingTime = !isWorkingTime;
//        isRunning = false;
//        start();
    }

    public void restartStage() {
        if (cycleCount == 1 || cycleCount == 3 || cycleCount == 5 || cycleCount==7){
            currentTime = WORK_TIME * 60;
            listener.onTimerUpdate(formatTime(currentTime));
        } else if (cycleCount == 8) {
            cycleCount = 0;
            currentTime = LONG_BREAK_TIME * 60;
            listener.onTimerUpdate(formatTime(currentTime));
        } else {
            currentTime = SHORT_BREAK_TIME * 60;
            listener.onTimerUpdate(formatTime(currentTime));
        }
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
