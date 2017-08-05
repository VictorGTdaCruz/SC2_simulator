package victorcruz.sc2_simulator;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import victorcruz.sc2_simulator.Buildings.BuildingHandler;
import victorcruz.sc2_simulator.Resources.ResourcesHandler;
import victorcruz.sc2_simulator.Supply.SupplyHandler;
import victorcruz.sc2_simulator.Time.ChronometerModified;
import victorcruz.sc2_simulator.Time.TimeHandler;
import victorcruz.sc2_simulator.Units.UnitHandler;


public class MainSimulator extends AppCompatActivity {

    // Android components
    private GridLayout shortcutsLayout, unitsLayout;

    private Button stcButton34;

    //private Chronometer chronometer;
    private ChronometerModified chronometerModified;


    private TextView minTextView, gasTextView, supplyTextView, supplyMaxTextView, larvaTextView;

    // aux variables
    private long currentTimeModified = 0, lastTick = 0;// used on onTick

    // Handlers
    private SupplyHandler supplyHandler;
    private ResourcesHandler resourcesHandler;
    private TimeHandler timeHandler;
    private UnitHandler unitHandler;
    private BuildingHandler buildingHandler;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_options:
                    unitsLayout.setVisibility(View.INVISIBLE);
                    shortcutsLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_buildings:
                    unitsLayout.setVisibility(View.INVISIBLE);
                    shortcutsLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_units:
                    unitsLayout.setVisibility(View.VISIBLE);
                    shortcutsLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_shortcuts:
                    unitsLayout.setVisibility(View.INVISIBLE);
                    shortcutsLayout.setVisibility(View.VISIBLE);
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
        unitsLayout = (GridLayout) findViewById(R.id.UnitsLayout);
        shortcutsLayout = (GridLayout) findViewById(R.id.ShortcutsLayout);

        // buttons
        stcButton34 = (Button) findViewById(R.id.stcButton34);

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
        supplyHandler = new SupplyHandler(supplyTextView, supplyMaxTextView);
        resourcesHandler = new ResourcesHandler(minTextView, gasTextView);
        timeHandler = new TimeHandler(stcButton34, chronometerModified);
        unitHandler = new UnitHandler(resourcesHandler, timeHandler, supplyHandler, larvaTextView);
        buildingHandler = new BuildingHandler(resourcesHandler, timeHandler, supplyHandler);

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
                    buildingHandler.buildingProduction(currentTimeModified);

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
        unitHandler = new UnitHandler(resourcesHandler, timeHandler, supplyHandler, larvaTextView);
        buildingHandler = new BuildingHandler(resourcesHandler, timeHandler, supplyHandler);
    }

    public void makeUnit(View view){
        unitHandler.makeUnit(view);
    }

    public void makeBuilding(View view){buildingHandler.makeBuilding(view);}

    public void printQueue(View view){
        while(unitHandler.unitPriorityQueue.size() != 0){
            System.out.println(unitHandler.unitPriorityQueue.peek().getReady());
            System.out.println(unitHandler.unitPriorityQueue.remove());
        }
    }

}

/*  Cronometro pula um segundo la pros 6 min e pouco pq ele conta de 1002 milisegundos ao inves de 1000.

    implementar novo painel com botoes para estruturas
    melhorar printlns pra facilitar entendimento do que esta acontecendo
    implementar estruturas?
 */