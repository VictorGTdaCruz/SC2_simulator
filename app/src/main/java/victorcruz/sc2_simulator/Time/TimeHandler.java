package victorcruz.sc2_simulator.Time;


import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import victorcruz.sc2_simulator.R;


public class TimeHandler {

    private ChronometerModified chronometerModified;
    private Button stcButton34;

    private boolean isStopped = true;
    private long timeWhenStoppedModified;

    private boolean gameStarted = false;


    public TimeHandler ( Button stcButton34, ChronometerModified chronometerModified){

        this.chronometerModified = chronometerModified;
        this.stcButton34 = stcButton34;

        gameStarted = false;
        isStopped = true;
    }

    public void resetChrono() throws InterruptedException {

        Thread.sleep(200);

        stcButton34.setText("START");

        chronometerModified.stop();
        chronometerModified.setBase(SystemClock.elapsedRealtime());
        timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
        gameStarted = false;
        isStopped = true;
    }

    public void startChrono(View view) {
        switch (view.getId()) {
            case R.id.chronometerModified:
                if (stcButton34.getText().equals("START")) {
                    chronometerModified.setBase(SystemClock.elapsedRealtime() + timeWhenStoppedModified);
                    chronometerModified.start();
                    isStopped = false;

                    stcButton34.setText("STOP");
                    if (!(gameStarted)) gameStarted = true;
                } else if (stcButton34.getText().equals("STOP")) {
                    timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
                    chronometerModified.stop();
                    isStopped = true;

                    stcButton34.setText("START");
                }
                break;
            case R.id.stcButton34:
                if (stcButton34.getText().equals("START")) {
                    chronometerModified.setBase(SystemClock.elapsedRealtime() + timeWhenStoppedModified);
                    chronometerModified.start();
                    isStopped = false;

                    stcButton34.setText("STOP");
                    if (!(gameStarted)) gameStarted = true;
                } else if (stcButton34.getText().equals("STOP")) {
                    timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
                    chronometerModified.stop();
                    isStopped = true;

                    stcButton34.setText("START");
                }
                break;
        }
    }

    public boolean isTimeRunning (){
        if (stcButton34.getText().equals("STOP")) return true;
        else return false;
    }

    public long getTimeWhenStopped(){
        return timeWhenStoppedModified;
    }

    public long getTime(){

        if (isStopped) {
            //System.out.println("TIME: " + (-timeWhenStoppedModified));
            return -timeWhenStoppedModified;
        }
        else {
            //System.out.println("TIME: " + (SystemClock.elapsedRealtime() - chronometerModified.getBase()));
            return SystemClock.elapsedRealtime() - chronometerModified.getBase();
        }
    }

    public boolean isGameStarted(){
        return gameStarted;
    }

}




/*public class TimeHandler {

    private ChronometerModified chronometerModified;
    private Button stcButton34;

    private boolean isStopped;
    private long timeWhenStoppedModified;

    private boolean gameStarted;


    public TimeHandler (Button stcButton34, ChronometerModified chronometerModified){

        this.chronometerModified = chronometerModified;
        this.stcButton34 = stcButton34;

        gameStarted = false;
        isStopped = true;
    }

    public void startChrono(View view) {
        switch (view.getId()) {
            case R.id.chronometerModified:
                if (stcButton34.getText().equals("START")) {
                    chronometerModified.setBase(SystemClock.elapsedRealtime() + timeWhenStoppedModified);
                    chronometerModified.start();
                    isStopped = false;

                    stcButton34.setText("STOP");
                    if (!(gameStarted)) gameStarted = true;
                } else if (stcButton34.getText().equals("STOP")) {
                    timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
                    chronometerModified.stop();
                    isStopped = true;

                    stcButton34.setText("START");
                }
                break;
            case R.id.stcButton34:
                if (stcButton34.getText().equals("START")) {
                    chronometerModified.setBase(SystemClock.elapsedRealtime() + timeWhenStoppedModified);
                    chronometerModified.start();
                    isStopped = false;

                    stcButton34.setText("STOP");
                    if (!(gameStarted)) gameStarted = true;
                } else if (stcButton34.getText().equals("STOP")) {
                    timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
                    chronometerModified.stop();
                    isStopped = true;

                    stcButton34.setText("START");
                }
                break;
        }
    }

    public void resetChrono() throws InterruptedException {

        Thread.sleep(200);

        stcButton34.setText("START");

        chronometerModified.stop();
        chronometerModified.setBase(SystemClock.elapsedRealtime());
        timeWhenStoppedModified = chronometerModified.getBase() - SystemClock.elapsedRealtime();
        gameStarted = false;
        isStopped = true;
    }

    public boolean isTimeRunning (){
        if (stcButton34.getText().equals("STOP")) return true;
        else return false;
    }

    public long getTimeWhenStopped(){
        return timeWhenStoppedModified;
    }

    public long getTime(){

        if (isStopped) {
            //System.out.println("TIME: " + (-timeWhenStoppedModified));
            return -timeWhenStoppedModified;
        }
        else {
            //System.out.println("TIME: " + (SystemClock.elapsedRealtime() - chronometerModified.getBase()));
            return SystemClock.elapsedRealtime() - chronometerModified.getBase();
        }
    }

    public boolean isGameStarted(){
        return gameStarted;
    }

}*/
