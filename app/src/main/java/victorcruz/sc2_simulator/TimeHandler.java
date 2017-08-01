package victorcruz.sc2_simulator;


import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class TimeHandler {

    private Chronometer chronometer;
    private ChronometerTest chronometerTest;
    private Button optButton34;

    private long timeWhenStopped;
    private long timeWhenStoppedTest;


    public TimeHandler (Chronometer chronometer, Button optButton34, ChronometerTest chronometerTest){

        this.chronometer = chronometer;
        this.chronometerTest = chronometerTest;
        this.optButton34 = optButton34;

    }

    public void startChrono(View view) {
        switch (view.getId()) {
            case R.id.chronometer:
                if (optButton34.getText().equals("START")) {
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();

                    chronometerTest.setBase(SystemClock.elapsedRealtime() + timeWhenStoppedTest);
                    chronometerTest.start();

                    optButton34.setText("STOP");
                } else if (optButton34.getText().equals("STOP")) {
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    chronometer.stop();

                    timeWhenStoppedTest = chronometerTest.getBase() - SystemClock.elapsedRealtime();
                    chronometerTest.stop();

                    optButton34.setText("START");
                }
                break;
            case R.id.optButton34:
                if (optButton34.getText().equals("START")) {
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();

                    chronometerTest.setBase(SystemClock.elapsedRealtime() + timeWhenStoppedTest);
                    chronometerTest.start();

                    optButton34.setText("STOP");
                } else if (optButton34.getText().equals("STOP")) {
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    chronometer.stop();

                    timeWhenStoppedTest = chronometerTest.getBase() - SystemClock.elapsedRealtime();
                    chronometerTest.stop();

                    optButton34.setText("START");
                }
                break;
        }
    }

    public void resetChrono(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
        timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
        optButton34.setText("START");

        chronometerTest.setBase(SystemClock.elapsedRealtime());
        chronometerTest.stop();
        timeWhenStoppedTest = chronometerTest.getBase() - SystemClock.elapsedRealtime();
    }

    public boolean isTimeRunning (){
        if (optButton34.getText().equals("STOP")) return true;
        else return false;
    }

    public long getTimeWhenStopped(){
        return timeWhenStopped;
    }

    public long getTime(){
        return SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    public void getTestTime(){ System.out.println(SystemClock.elapsedRealtime() - chronometerTest.getBase());   }

}
