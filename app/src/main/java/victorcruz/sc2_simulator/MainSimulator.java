package victorcruz.sc2_simulator;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TextView;

import victorcruz.sc2_simulator.Resources.ResourcesHandler;
import victorcruz.sc2_simulator.Time.ChronometerModified;
import victorcruz.sc2_simulator.Time.TimeHandler;
import victorcruz.sc2_simulator.Units.UnitHandler;


public class MainSimulator extends AppCompatActivity {

    // Android components
    private GridLayout optionsLayout;

    private Button optButton34, optButton14;

    //private Chronometer chronometer;
    private ChronometerModified chronometerModified;


    private TextView minTextView, gasTextView, supplyTextView, supplyMaxTextView, larvaTextView;

    // aux variables
    private long currentTime = 0, currentTimeModified = 0, lastTick = 0;// used on onTick

    // Handlers
    private ResourcesHandler resourcesHandler;
    private TimeHandler timeHandler;
    private UnitHandler unitHandler;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    optionsLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    optionsLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_notifications:
                    optionsLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_options:
                    optionsLayout.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulator);

        initializeVariables();
    }

    private void initializeVariables (){

        // bottom navigation bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // layouts
        optionsLayout = (GridLayout) findViewById(R.id.OptionsLayout);

        // buttons
        optButton34 = (Button) findViewById(R.id.optButton34);
        optButton14 = (Button) findViewById(R.id.optButton14);

        //game variables
        //chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometerModified = (ChronometerModified) findViewById(R.id.chronometerModified);

        // player variables
        minTextView = (TextView) findViewById(R.id.MinTextView);
        gasTextView = (TextView) findViewById(R.id.GasTextView);
        supplyTextView = (TextView) findViewById(R.id.SupplyTextView);
        supplyMaxTextView = (TextView) findViewById(R.id.SupplyMaxTextView);
        larvaTextView = (TextView) findViewById(R.id.LarvaTextView);

        // handlers
        resourcesHandler = new ResourcesHandler(minTextView, gasTextView);
        //timeHandler = new TimeHandler(chronometer, optButton34, chronometerModified);
        timeHandler = new TimeHandler(optButton34, chronometerModified);
        unitHandler = new UnitHandler(resourcesHandler, timeHandler, supplyTextView, supplyMaxTextView, larvaTextView);

        // onTick method
        /*chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                currentTime = SystemClock.elapsedRealtime() - chronometer.getBase();

                if ((currentTime / 1000) > (lastTick / 1000) ) {
                    System.out.println("CurrentTime at Main:" + currentTime);

                    unitHandler.unitProduction(currentTime);
                    unitHandler.growLarva(currentTime);
                    resourcesHandler.resourceMining(currentTime);

                }

                lastTick = SystemClock.elapsedRealtime() - chronometer.getBase();
            }
        });*/

        chronometerModified.setOnChronometerTickListener(new ChronometerModified.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(ChronometerModified chronometerModified) {
                /*System.out.println("TICKED!!!!!!!");
                timeHandler.getTestTime();*/

                currentTimeModified = SystemClock.elapsedRealtime() - chronometerModified.getBase();

                if ((currentTimeModified / 100) > (lastTick / 100) ) {
                    System.out.println("CurrentTime at Main:" + currentTimeModified);

                    unitHandler.unitProduction(currentTimeModified);
                    unitHandler.growLarva(currentTimeModified);
                    resourcesHandler.resourceMining(currentTimeModified);

                }

                lastTick = SystemClock.elapsedRealtime() - chronometerModified.getBase();

            }
        });
    }

    public void startTime(View view){
        timeHandler.startChrono(view);
    }

    public void resetMatch(View view) throws InterruptedException {
        timeHandler.resetChrono(view);
        resourcesHandler = new ResourcesHandler(minTextView, gasTextView);
        unitHandler = new UnitHandler(resourcesHandler, timeHandler, supplyTextView, supplyMaxTextView, larvaTextView);
    }

    public void makeUnit(View view){
        unitHandler.makeUnit(view);
    }

    public void printQueue(View view){
        while(resourcesHandler.minPriorityQueue.size() != 0){
            System.out.println(resourcesHandler.minPriorityQueue.peek().getReady());
            System.out.println(resourcesHandler.minPriorityQueue.remove());
        }
    }

}

/*  Cronometro pula um segundo la pros 6 min e pouco pq ele conta de 1002 milisegundos ao inves de 1000.

    implementar um handler apenas pras larvas
    implementar estruturas?
 */