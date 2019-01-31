package gapp.gui;

import javafx.animation.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MyTimer {

    private long Time = 0, startTime = 0, durationTime = 60000;
    private AnimationTimer timer;
    Text text;


    /** Crea uno StackPane che visualliza un countdown generico.
     * @return lo StackPane contenente il timer.  */
    public StackPane getTimer() {
        StackPane timerBox = new StackPane();
        timerBox.getStyleClass().add("timer");
        timerBox.setPadding(new Insets(10, 0, 0, 20));

        text = new Text("" + Math.round(durationTime / 1000d));
        
        timer = new AnimationTimer() {
            public void handle(long now) {
                long t = System.currentTimeMillis();
                double countdown = (Math.round((durationTime - (t - startTime)) / 100) / 10d);
                if(countdown == 0) this.stop();
                text.setText("" + countdown);
            }
        };
        timerBox.getChildren().add(text);
        return timerBox;
    }

    /** Imposta la durata del countdown.
     * @param duration quanto deve durare il countdown.  */
    public void setDurationTime(long duration) {
        this.durationTime = duration;
    }

    /** Fa partire il countdown.*/
    public void start() {
        this.startTime = System.currentTimeMillis();
        timer.start();
    }

    /** Imposta la durata del countdown e lo fa partire immediatamente.
     * @param duration la durata del countdown.  */
    public void start(long duration) {
        this.setDurationTime(duration);
        this.start();
    }

    /** Mette in pausa il countdown.*/
    public void pause() {
        timer.stop();
    }

    /** Resetta il countdown. */
    public void stop() {
        timer.stop();
        text.setText("" + Math.round(durationTime/1000d));
    }

}
