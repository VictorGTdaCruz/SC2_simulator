package victorcruz.sc2_simulator.Time;


import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import victorcruz.sc2_simulator.R;

public class TimeHandler {

    //private Chronometer chronometer;
    private ChronometerModified chronometerModified;
    private Button optButton34;

    private long timeWhenStopped;
    private long timeWhenStoppedModified;

    private boolean gameStarted;


    public TimeHandler (Button optButton34, ChronometerModified chronometerModified){

        //this.chronometer = chronometer;
        this.chronometerModified = chronometerModified;
        this.optButton34 = optButton34;

        gameStarted = false;

    }

    public void startChrono(View view) {
        switch (view.getId()) {
            case R.id.chronometer:
                if (optButton34.getText().equals("START")) {
                    //chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    //chronometer.start();

                    chronometerModified.setBase(SystemClock.elapsedRealtime() + timeWhenStoppedModified);
                    chronometerModified.start();

                    optButton34.setText("STOP");
                    if (!(gameStarted)) gameStarted = true;
                } else if (optButton34.getText().equals("STOP")) {
                    //timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    //chronometer.stop();

                    timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
                    chronometerModified.stop();

                    optButton34.setText("START");
                }
                break;
            case R.id.optButton34:
                if (optButton34.getText().equals("START")) {
                    //chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    //chronometer.start();

                    chronometerModified.setBase(SystemClock.elapsedRealtime() + timeWhenStoppedModified);
                    chronometerModified.start();

                    optButton34.setText("STOP");
                    if (!(gameStarted)) gameStarted = true;
                } else if (optButton34.getText().equals("STOP")) {
                    //timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    //chronometer.stop();

                    timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
                    chronometerModified.stop();

                    optButton34.setText("START");
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

        optButton34.setText("START");

        chronometerModified.stop();
        chronometerModified.setBase(SystemClock.elapsedRealtime());
        timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
        gameStarted = false;
    }

    public boolean isTimeRunning (){
        if (optButton34.getText().equals("STOP")) return true;
        else return false;
    }

    public long getTimeWhenStopped(){
        return timeWhenStoppedModified;
    }

    public long getTime(){
        return SystemClock.elapsedRealtime() - chronometerModified.getBase();
    }

    public void getTestTime(){ System.out.println(SystemClock.elapsedRealtime() - chronometerModified.getBase());   }

    public boolean isGameStarted(){
        return gameStarted;
    }

}