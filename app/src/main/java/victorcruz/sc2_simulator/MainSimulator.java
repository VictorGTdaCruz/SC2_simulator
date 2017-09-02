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
import victorcruz.sc2_simulator.Requisites.TechHandler;
import victorcruz.sc2_simulator.Resources.ResourcesHandler;
import victorcruz.sc2_simulator.Supply.SupplyHandler;
import victorcruz.sc2_simulator.Time.ChronometerModified;
import victorcruz.sc2_simulator.Time.TimeHandler;
import victorcruz.sc2_simulator.Units.UnitHandler;


public class MainSimulator extends AppCompatActivity {

    // Android components
    private GridLayout statsLayouts, optionsLayouts, buildingsLayouts, advbdLayout, mutbdLayout,
            unitsLayout, mutUnitsLayout, shortcutsLayout;

    private Button stcButton34, // start/stop button
                    stcButton22, stcButton21; // send to gas/ take out of gas buttons

    private Button unitButton11, unitButton12, unitButton13,  unitButton14,
                    unitButton21, unitButton22, unitButton23, unitButton24,
                    unitButton31, unitButton32, unitButton33,
                    mutunitButton11, mutunitButton12,
                    mutunitButton21, mutunitButton22,
                    mutunitButton31,
                    bdButton11, bdButton12,
                    bdButton21, bdButton22, bdButton23, bdButton24,
                    bdButton31, bdButton32,
                    advbdButton11, advbdButton12,
                    advbdButton21, advbdButton22,
                    advbdButton31,
                    mutbdButton11, mutbdButton12,
                    mutbdButton21, mutbdButton22,
                    mutbdButton31;

    private Button[] buttons;

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
    private TechHandler techHandler;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_stats:
                    statsLayouts.setVisibility(View.VISIBLE);
                    optionsLayouts.setVisibility(View.INVISIBLE);
                    buildingsLayouts.setVisibility(View.INVISIBLE);
                    advbdLayout.setVisibility(View.INVISIBLE);
                    mutbdLayout.setVisibility(View.INVISIBLE);
                    unitsLayout.setVisibility(View.INVISIBLE);
                    mutUnitsLayout.setVisibility(View.INVISIBLE);
                    shortcutsLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_options:
                    statsLayouts.setVisibility(View.INVISIBLE);
                    optionsLayouts.setVisibility(View.VISIBLE);
                    buildingsLayouts.setVisibility(View.INVISIBLE);
                    advbdLayout.setVisibility(View.INVISIBLE);
                    mutbdLayout.setVisibility(View.INVISIBLE);
                    unitsLayout.setVisibility(View.INVISIBLE);
                    mutUnitsLayout.setVisibility(View.INVISIBLE);
                    shortcutsLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_buildings:
                    statsLayouts.setVisibility(View.INVISIBLE);
                    optionsLayouts.setVisibility(View.INVISIBLE);
                    buildingsLayouts.setVisibility(View.VISIBLE);
                    advbdLayout.setVisibility(View.INVISIBLE);
                    mutbdLayout.setVisibility(View.INVISIBLE);
                    unitsLayout.setVisibility(View.INVISIBLE);
                    mutUnitsLayout.setVisibility(View.INVISIBLE);
                    shortcutsLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_units:
                    statsLayouts.setVisibility(View.INVISIBLE);
                    optionsLayouts.setVisibility(View.INVISIBLE);
                    buildingsLayouts.setVisibility(View.INVISIBLE);
                    advbdLayout.setVisibility(View.INVISIBLE);
                    mutbdLayout.setVisibility(View.INVISIBLE);
                    unitsLayout.setVisibility(View.VISIBLE);
                    mutUnitsLayout.setVisibility(View.INVISIBLE);
                    shortcutsLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_shortcuts:
                    statsLayouts.setVisibility(View.INVISIBLE);
                    optionsLayouts.setVisibility(View.INVISIBLE);
                    buildingsLayouts.setVisibility(View.INVISIBLE);
                    advbdLayout.setVisibility(View.INVISIBLE);
                    mutbdLayout.setVisibility(View.INVISIBLE);
                    unitsLayout.setVisibility(View.INVISIBLE);
                    mutUnitsLayout.setVisibility(View.INVISIBLE);
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
        statsLayouts = (GridLayout) findViewById(R.id.StatsLayout);
        optionsLayouts = (GridLayout) findViewById(R.id.OptionsLayout);
        buildingsLayouts = (GridLayout) findViewById(R.id.BuildingsLayout);
        advbdLayout = (GridLayout) findViewById(R.id.AdvBuildingsLayout);
        mutbdLayout = (GridLayout) findViewById(R.id.MutationBuildingsLayout);
        unitsLayout = (GridLayout) findViewById(R.id.UnitsLayout);
        mutUnitsLayout = (GridLayout) findViewById(R.id.MutationUnitsLayout);
        shortcutsLayout = (GridLayout) findViewById(R.id.ShortcutsLayout);

        // buttons
        buttons = new Button[34];
        stcButton34 = (Button) findViewById(R.id.stcButton34);
        stcButton22 = (Button) findViewById(R.id.stcButton22);
        stcButton21 = (Button) findViewById(R.id.stcButton21);
        unitButton11 = (Button) findViewById(R.id.unitButton11);
        unitButton12 = (Button) findViewById(R.id.unitButton12);
        unitButton13 = (Button) findViewById(R.id.unitButton13);
        unitButton14 = (Button) findViewById(R.id.unitButton14);
        unitButton21 = (Button) findViewById(R.id.unitButton21);
        unitButton22 = (Button) findViewById(R.id.unitButton22);
        unitButton23 = (Button) findViewById(R.id.unitButton23);
        unitButton24 = (Button) findViewById(R.id.unitButton24);
        unitButton31 = (Button) findViewById(R.id.unitButton31);
        unitButton32 = (Button) findViewById(R.id.unitButton32);
        unitButton33 = (Button) findViewById(R.id.unitButton33);
        mutunitButton11 = (Button) findViewById(R.id.mutunitButton11);
        mutunitButton12 = (Button) findViewById(R.id.mutunitButton12);
        mutunitButton21 = (Button) findViewById(R.id.mutunitButton21);
        mutunitButton22 = (Button) findViewById(R.id.mutunitButton22);
        mutunitButton31 = (Button) findViewById(R.id.mutunitButton31);
        bdButton11 = (Button) findViewById(R.id.bdButton11);
        bdButton12 = (Button) findViewById(R.id.bdButton12);
        bdButton21 = (Button) findViewById(R.id.bdButton21);
        bdButton22 = (Button) findViewById(R.id.bdButton22);
        bdButton23 = (Button) findViewById(R.id.bdButton23);
        bdButton24 = (Button) findViewById(R.id.bdButton24);
        bdButton31 = (Button) findViewById(R.id.bdButton31);
        bdButton32 = (Button) findViewById(R.id.bdButton32);
        advbdButton11 = (Button) findViewById(R.id.advbdButton11);
        advbdButton12 = (Button) findViewById(R.id.advbdButton12);
        advbdButton21 = (Button) findViewById(R.id.advbdButton21);
        advbdButton22 = (Button) findViewById(R.id.advbdButton22);
        advbdButton31 = (Button) findViewById(R.id.advbdButton31);
        mutbdButton11 = (Button) findViewById(R.id.mutbdButton11);
        mutbdButton12 = (Button) findViewById(R.id.mutbdButton12);
        mutbdButton21 = (Button) findViewById(R.id.mutbdButton21);
        mutbdButton22 = (Button) findViewById(R.id.mutbdButton22);
        mutbdButton31 = (Button) findViewById(R.id.mutbdButton31);
        organizeButtons();

        //game variables
        chronometerModified = (ChronometerModified) findViewById(R.id.chronometerModified);

        // player variables
        minTextView = (TextView) findViewById(R.id.MinTextView);
        gasTextView = (TextView) findViewById(R.id.GasTextView);
        supplyTextView = (TextView) findViewById(R.id.SupplyTextView);
        supplyMaxTextView = (TextView) findViewById(R.id.SupplyMaxTextView);
        larvaTextView = (TextView) findViewById(R.id.LarvaTextView);

        // handlers
        techHandler = new TechHandler(buttons);
        supplyHandler = new SupplyHandler(supplyTextView, supplyMaxTextView);
        resourcesHandler = new ResourcesHandler(minTextView, gasTextView);
        timeHandler = new TimeHandler(stcButton34, chronometerModified);
        unitHandler = new UnitHandler(resourcesHandler, timeHandler, supplyHandler, techHandler, larvaTextView);
        buildingHandler = new BuildingHandler(resourcesHandler, timeHandler, supplyHandler, unitHandler, techHandler, stcButton22, stcButton21);
        resourcesHandler.setBuildingHandler(buildingHandler);

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



    public void sendWorkerToGas(View view){
        resourcesHandler.sendWorkerToGas(timeHandler.getTime());
        System.out.println("time " + timeHandler.getTime());
    }

    public void takeWorkerOutOfGas(View view){
        resourcesHandler.takeWorkerOutOfGas(timeHandler.getTime());
        System.out.println("time " + timeHandler.getTime());
    }



    public void makeUnit(View view){
        unitHandler.makeUnit(view);
    }

    public void makeBuilding(View view){ buildingHandler.makeBuilding(view); }

    public void startTime(View view){
        timeHandler.startChrono(view);
    }

    public void resetMatch(View view) throws InterruptedException {
        techHandler = new TechHandler(buttons);
        timeHandler.resetChrono();
        supplyHandler = new SupplyHandler(supplyTextView, supplyMaxTextView);
        resourcesHandler = new ResourcesHandler(minTextView, gasTextView);
        unitHandler = new UnitHandler(resourcesHandler, timeHandler, supplyHandler, techHandler, larvaTextView);
        buildingHandler = new BuildingHandler(resourcesHandler, timeHandler, supplyHandler, unitHandler, techHandler, stcButton22, stcButton21);
        resourcesHandler.setBuildingHandler(buildingHandler);
    }

    public void showUnitsLayout(View view){
        unitsLayout.setVisibility(View.VISIBLE);
        mutUnitsLayout.setVisibility(View.INVISIBLE);
    }

    public void showMutationUnitsLayout (View view){
        unitsLayout.setVisibility(View.INVISIBLE);
        mutUnitsLayout.setVisibility(View.VISIBLE);
    }

    public void showBuildingsLayout(View view){
        buildingsLayouts.setVisibility(View.VISIBLE);
        advbdLayout.setVisibility(View.INVISIBLE);
        mutbdLayout.setVisibility(View.INVISIBLE);
    }

    public void showAdvBuildingsLayout(View view){
        buildingsLayouts.setVisibility(View.INVISIBLE);
        advbdLayout.setVisibility(View.VISIBLE);
        mutbdLayout.setVisibility(View.INVISIBLE);
    }

    public void showMutationBuildingsLayout(View view){
        buildingsLayouts.setVisibility(View.INVISIBLE);
        advbdLayout.setVisibility(View.INVISIBLE);
        mutbdLayout.setVisibility(View.VISIBLE);
    }

    public void organizeButtons(){
        buttons[0] = unitButton11; buttons[1] = unitButton12; buttons[2] = unitButton13;
        buttons[3] = unitButton14; buttons[4] = unitButton21; buttons[5] = unitButton22;
        buttons[6] = unitButton23; buttons[7] = unitButton24; buttons[8] = unitButton31;
        buttons[9] = unitButton32; buttons[10] = unitButton33; buttons[11] = mutunitButton11;
        buttons[12] = mutunitButton12; buttons[13] = mutunitButton21; buttons[14] = mutunitButton22;
        buttons[15] = mutunitButton31; buttons[16] = bdButton11; buttons[17] = bdButton12;
        buttons[18] = bdButton21; buttons[19] = bdButton22; buttons[20] = bdButton23;
        buttons[21] = bdButton24; buttons[22] = bdButton31; buttons[23] = bdButton32;
        buttons[24] = advbdButton11; buttons[25] = advbdButton12; buttons[26] = advbdButton21;
        buttons[27] = advbdButton22; buttons[28] = advbdButton31; buttons[29] = mutbdButton11;
        buttons[30] = mutbdButton12; buttons[31] = mutbdButton21; buttons[32] = mutbdButton22;
        buttons[33] = mutbdButton31;

    }



    public void printQueue(View view){
        System.out.println(resourcesHandler.getMinPriorityQueue()[0].size());
        System.out.println(resourcesHandler.getMinPriorityQueue()[1].size());
        System.out.println(resourcesHandler.getGasPriorityQueue()[0].size());
        System.out.println(resourcesHandler.getGasPriorityQueue()[1].size());
        System.out.println(resourcesHandler.getGasPriorityQueue()[2].size());
        System.out.println(resourcesHandler.getGasPriorityQueue()[3].size());

        while(resourcesHandler.getGasPriorityQueue()[0].size() != 0){
            System.out.println(resourcesHandler.getGasPriorityQueue()[0].peek().getReady());
            System.out.println(resourcesHandler.getGasPriorityQueue()[0].remove());
        }
    }

    public void printRequisites(View view){
        techHandler.printElemets();
    }

}

/*
    corrigir supplymax inicial, nao eh 14, eh 0 com 8 de suserano e 6 da hatch (acho q n precisa pq nunca unidades vao morrer)
    implementar estrutura de dados que possui todas as unidades vivas (precisa?)

    ver se precisa de fragmentos

    criar metodo de criacao inicial de drones e hatchery quando jogo inicia, ja incluindo nos requisites e supply
        sem depender do cronometro, ou seja, nao pode usar nenhum metodo ja existente dentro de unithandler
        ja q nao tem um sistema q mantem registro das unidades vivas (a nao ser as variaveis), pq n pode ser marretado nesse novo metodo?

    melhorar precisao do consumeDrone (drone andando) (dependendo da construcao)
        talvez cancelar uma iteração de mine após a construcao, hatch = 2

    pq ter um getter static? PODE AJUDAR COM 2 ACIMA

    handler para mineral e gas?
        resources handler ja faz esse papel, n tem problema ser dos dois ao mesmo tempo, soh deixar o codigo limpo

    implementar sistema de larva escalonavel
    implementar mining minerals e gas escalonavel
        implementar getnext (ao contrario de get last)
        fazer as PQ do resourcehandler nao ser static
    pensar em como resolver o problema de dual requisito
        fazer um metodo que usa o sistema antigo de requisitos pra analisar o dual?
        ou um metodo independente em techhandler que simplesmente faz essa checkagem?
 */