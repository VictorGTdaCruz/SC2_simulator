package victorcruz.sc2_simulator;


import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class TimeHandler {

    private Chronometer chronometer;
    private Button optButton34;

    private ResourcesHandler resourcesHandler;

    private long timeWhenStopped;

    public TimeHandler (Chronometer chronometer, Button optButton34, ResourcesHandler resourcesHandler){

        this.chronometer = chronometer;
        this.optButton34 = optButton34;
        this.resourcesHandler = resourcesHandler;

    }

    public void startChrono(View view) {
        switch (view.getId()) {
            case R.id.chronometer:
                if (optButton34.getText().equals("START")) {
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();
                    optButton34.setText("STOP");
                } else if (optButton34.getText().equals("STOP")) {
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    chronometer.stop();
                    optButton34.setText("START");
                }
                break;
            case R.id.optButton34:
                if (optButton34.getText().equals("START")) {
                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();
                    optButton34.setText("STOP");
                } else if (optButton34.getText().equals("STOP")) {
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    chronometer.stop();
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

}
