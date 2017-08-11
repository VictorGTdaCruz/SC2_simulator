package victorcruz.sc2_simulator.Time;


import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import victorcruz.sc2_simulator.R;

public class TimeHandler {

    private ChronometerModified chronometerModified;
    private Button stcButton34;

    private long timeWhenStoppedModified;

    private boolean gameStarted;


    public TimeHandler (Button stcButton34, ChronometerModified chronometerModified){

        this.chronometerModified = chronometerModified;
        this.stcButton34 = stcButton34;

        gameStarted = false;

    }

    public void startChrono(View view) {
        switch (view.getId()) {
            case R.id.chronometerModified:
                if (stcButton34.getText().equals("START")) {
                    chronometerModified.setBase(SystemClock.elapsedRealtime() + timeWhenStoppedModified);
                    chronometerModified.start();

                    stcButton34.setText("STOP");
                    if (!(gameStarted)) gameStarted = true;
                } else if (stcButton34.getText().equals("STOP")) {
                    timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
                    chronometerModified.stop();

                    stcButton34.setText("START");
                }
                break;
            case R.id.stcButton34:
                if (stcButton34.getText().equals("START")) {
                    chronometerModified.setBase(SystemClock.elapsedRealtime() + timeWhenStoppedModified);
                    chronometerModified.start();

                    stcButton34.setText("STOP");
                    if (!(gameStarted)) gameStarted = true;
                } else if (stcButton34.getText().equals("STOP")) {
                    timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
                    chronometerModified.stop();

                    stcButton34.setText("START");
                }
                break;
        }
    }

    public void resetChrono(View view) throws InterruptedException {
        /*chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
        timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
        */


        Thread.sleep(200);

        stcButton34.setText("START");

        chronometerModified.stop();
        chronometerModified.setBase(SystemClock.elapsedRealtime());
        timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
        gameStarted = false;
    }

    public boolean isTimeRunning (){
        if (stcButton34.getText().equals("STOP")) return true;
        else return false;
    }

    public long getTimeWhenStopped(){
        return timeWhenStoppedModified;
    }

    public long getTime(){
        return SystemClock.elapsedRealtime() - chronometerModified.getBase();
    }

    public boolean isGameStarted(){
        return gameStarted;
    }

}
