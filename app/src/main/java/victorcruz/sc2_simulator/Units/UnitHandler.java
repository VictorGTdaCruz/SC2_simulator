package victorcruz.sc2_simulator.Units;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Comparator;
import java.util.PriorityQueue;

import victorcruz.sc2_simulator.Requisites.RequisitesHandler;
import victorcruz.sc2_simulator.Resources.MiningDrone;
import victorcruz.sc2_simulator.Resources.ResourcesHandler;
import victorcruz.sc2_simulator.Supply.SupplyHandler;
import victorcruz.sc2_simulator.Time.TimeHandler;
import victorcruz.sc2_simulator.Units.LarvaMechanics.LarvaHandler;

public class UnitHandler {

    // android components
    private TextView supplyTextView, supplyMaxTextView, larvaTextView;

    // handlers
    private SupplyHandler supplyHandler;
    private ResourcesHandler resourcesHandler;
    private TimeHandler timeHandler;
    private LarvaHandler larvaHandler;
    private RequisitesHandler requisitesHandler;

    // handlers to use postDelayed method
    private Handler prodHandler = new Handler(); // used on unitProduction
//    private Handler larvaHandler = new Handler(); // used on growLarva

    // Unit array to use as mold
    private Unit[] xUnit;

    // zerg units
    private int overlordNumber = 1, queenNumber = 0, lingNumber = 0, banelingNumber = 0, roachNumber = 0,
                ravagerNumber = 0, overseerNumber = 0, hydraliskNumber = 0, lurkerNumber = 0;
    private int workerNumber = 12;

    // unit production mechanic variables
    public PriorityQueue<Unit> unitPriorityQueue;
    private Comparator<Unit> comparator = new UnitComparator();



    public UnitHandler(ResourcesHandler resourcesHandler, TimeHandler timeHandler,
                       SupplyHandler supplyHandler, RequisitesHandler requisitesHandler,
                       TextView larvaTextView){
        unitPriorityQueue = new PriorityQueue<>(10, comparator);
        this.supplyHandler = supplyHandler;
        this.resourcesHandler = resourcesHandler;
        this.timeHandler = timeHandler;
        this.requisitesHandler = requisitesHandler;

        this.larvaTextView = larvaTextView;
        larvaTextView.setText(Integer.toString(3));

        larvaHandler = new LarvaHandler(larvaTextView);


        //xUnit[X] = new Unit(-1, "+LARVA", 25, -1, 0, 0, 0, 0, -1, -1, 10, 1, -1, -1, 1, -1, 11000, 0, 0, new String[]{"Larva"}, new String[]{"Biological", "Light"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit = new Unit[17];
        xUnit[0] = new Unit(-1, "Drone", 40, 50, 0, 1, 0, 0, 1, 1, 8, 12000, 3.94, 3.94, new String[]{}, new String[]{"Biological", "Light"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[1] = new Unit(-1, "Overlord", 200, 100, 0, 0, 8, 0, 1, -1, 11, 18000, 0.82, -1, new String[]{}, new String[]{"Biological", "Armored"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[2] = new Unit(-1, "Queen", 175, 150, 0, 2, 0, 25, 200, 1, 1, 2, 9, 36000, 1.31, 3.5, new String[]{"Hatchery", "SpawningPool"}, new String[]{"Biological", "Psionic"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[3] = new Unit(-1, "Zergling", 35, 50, 0, 1, 0, 0, 1, 1, 8, 17000, 4.13, 5.37, new String[]{"SpawningPool"}, new String[]{"Biological", "Light"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[4] = new Unit(-1, "Baneling", 30, 25, 25, 0, 0, 0, 1, 2, 8, 14000, 3.5, 4.55, new String[]{"Zergling", "BanelingNest"}, new String[]{"Biological"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[5] = new Unit(-1, "Roach", 145, 75, 25, 2, 0, 1, 1, 2, 9, 19000, 3.15, 4.09, new String[]{"RoachWarren"}, new String[]{"Biological", "Armored"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[6] = new Unit(-1, "Ravager", 120, 25, 75, 1, 0, 1, 1, 4, 9, 9000, 3.85, 5, new String[]{"Roach", "RoachWarren"}, new String[]{"Biological"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[7] = new Unit(-1, "Overseer", 200, 50, 50, 1, 0, 50, 200, 1, 1, -1, 11, 12000, 2.62, -1, new String[]{"Overlord", "Lair"}, new String[]{"Biological", "Armored", "Detector"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[8] = new Unit(-1, "Hydralisk", 90, 100, 50, 2, 0, 0, 1, 2, 9, 24000, 3.15, 4.09, new String[]{"HydraliskDen"}, new String[]{"Biological", "Light"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[9] = new Unit(-1, "Lurker", 200, 50, 100, 1, 0, 1, 1, 4, -1, 18000, 4.13, 5.37, new String[]{"Hydralisk", "LurkerDen"}, new String[]{"Biological", "Armored"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[10] = new Unit(-1, "Mutalisk", 120, 100, 100, 2, 0, 0, 1, -1, 11, 24000, 5.6, -1, new String[]{"Spire"}, new String[]{"Biological", "Light"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[11] = new Unit(-1, "Corruptor", 200, 150, 100, 2, 0, 2, 1, -1, 10, 29000, 4.725, -1, new String[]{"Spire"}, new String[]{"Biological", "Armored"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[12] = new Unit(-1, "Infestor", 90,  100, 150, 2, 0, 50, 200, 0, 1, 2, 10, 36000, 3.15, 4.09, new String[]{"InfestationPit"}, new String[]{"Biological", "Armored", "Psionic"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[13] = new Unit(-1, "SwarmHost", 160, 100, 75, 3, 0, 1, 1, 4, 10, 29000, 4.13, 5.37, new String[]{"InfestationPit"}, new String[]{"Biological", "Armored"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[14] = new Unit(-1, "Viper", 150, 100, 200, 3, 0, 50, 200, 1, 1, -1, 11, 29000, 4.13, -1, new String[]{"Hive"}, new String[]{"Biological", "Armored", "Psionic"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[15] = new Unit(-1, "Ultralisk", 500, 300, 200, 6, 0, 2, 1, 8, 9, 39000, 4.13, 5.37, new String[]{"UltraliskCavern"}, new String[]{"Biological", "Armored", "Massive"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[16] = new Unit(-1, "BroodLord", 25, 150, 150, 2, 0, 1, 1, -1, 12, 24000, 1.97, -1, new String[]{"Corruptor", "Greater Spire"}, new String[]{"Biological", "Armored", "Massive"}, new UnitAttackInfo[]{}, new UnitAbility[]{} );

    }
    /*
     I had a class dedicated to every unit in the game, but then i had to write a bunch of
     code to create each different unit, as you can see on the comments of the makeUnit method.
     The way i figured out not to have a giant method with every unit specified, was to make
     "Unit" the universal class and the NAME variable is what compares each unit ingame.
    */



    public void growLarva(long currentTimeModified){
        larvaHandler.growLarva(currentTimeModified);
    }


    public void unitProduction(final long currentTime) {
        if (unitPriorityQueue.peek() != null && 150 > unitPriorityQueue.peek().getReady() - currentTime) {

            final String name = unitPriorityQueue.peek().getName();
            final long ready = unitPriorityQueue.peek().getReady();
            final long productionTime = unitPriorityQueue.peek().getProductionTime();

            prodHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.out.println("UNIT IS READY: " + name + " " + (ready - currentTime));

                    if (name.equals("Drone")){
                        ResourcesHandler.minPriorityQueue.add(new MiningDrone(ready - productionTime, true));
                        workerNumber++;
                    }
                    else if (name.equals("Overlord")) {
                        overlordNumber++;
                        supplyHandler.increaseSupplyMax(8);
                    }
                    else if (name.equals("Queen")) queenNumber++;
                    else if (name.equals("Zergling")) lingNumber = lingNumber + 2;
                    else if (name.equals("Baneling")) banelingNumber++;
                    else if (name.equals("Roach")) roachNumber++;
                    else if (name.equals("Ravager")) ravagerNumber++;
                    else if (name.equals("Overseer")) overseerNumber++;
                    else if (name.equals("Hydralisk")) hydraliskNumber++;
                    else if (name.equals("Lurker")) lurkerNumber++;


                }
            }, unitPriorityQueue.peek().getReady() - currentTime);
            unitPriorityQueue.remove();
            unitProduction(currentTime);

        }
    }

    // only for zerg makebuilding
    public boolean hasDrone(){
        if (workerNumber > 0)
            return true;
        else return false;
    }

    // only for zerg makebuilding
    public void consumeDrone(){
        workerNumber--;
        resourcesHandler.decreaseMin(5);
        resourcesHandler.miningPQPeekRemove();
        supplyHandler.decreaseSupply(xUnit[0].getSupply());
    }

    public int checkUnitIndex(String buttonTag){
        for (int i = 0; i < xUnit.length; i++){
            if (buttonTag.equals(xUnit[i].getName())){
                return i;
            }
        }
        return -1;
    }


    public void makeUnit(View view){

        Button button = (Button) view;

        int index = checkUnitIndex(button.getTag().toString());
        Unit unit = new Unit(xUnit[index]);

        requisitesHandler.printElemets();

        if (timeHandler.isGameStarted()){
            if (requisitesHandler.containsInControl(unit.getRequisites()) &&
                    resourcesHandler.getMinerals() >= unit.getMinCost() && resourcesHandler.getGas() >= unit.getGasCost()
                    && supplyHandler.getSupply() + xUnit[index].getSupply() <= supplyHandler.getSupplyMax()) {

                if (timeHandler.isTimeRunning()) {
                    //set consumedLarva, a flow control variable that checks if the player had a larva to use and used it
                    larvaHandler.setConsumedLarva(larvaHandler.useLarva(timeHandler.getTime()));
                    unit.setOrderedTime(timeHandler.getTime());
                } else {
                    //set consumedLarva, a flow control variable that checks if the player had a larva to use and used it
                    larvaHandler.setConsumedLarva(larvaHandler.useLarva(timeHandler.getTime()));
                    unit.setOrderedTime(-timeHandler.getTimeWhenStopped());
                }
                if (larvaHandler.getConsumedLarva()) {
                    resourcesHandler.decreaseMin(unit.getMinCost());
                    resourcesHandler.decreaseGas(unit.getGasCost());
                    supplyHandler.increaseSupply(unit.getSupply());
                    unitPriorityQueue.add(unit);
                    requisitesHandler.addToControl(unit.getName());
                    System.out.println("UNIT ORDERED: " + unit.getName());
                }
            }
        }


        /*if(button.getText().equals("+DRONE")){
            // the ifelse inside is to pass the correct time when the button is clicked
            // the problem is that elapsedRealtime() keeps counting even if the chronometer is paused, so we can`t always use it
            // the if outside is to check if the player has enough resources to pay for the unit
            if (resourcesHandler.getMinerals() >= xDrone.getMinCost() && resourcesHandler.getGas() >= xDrone.getGasCost()) {
                resourcesHandler.decreaseMin(xDrone.getMinCost());
                resourcesHandler.decreaseGas(xDrone.getGasCost());
                supply = supply + xDrone.getSupply();
                supplyMax = supplyMax + xDrone.getSupplyMax();
                if (timeHandler.isTimeRunning())
                    unitPriorityQueue.add(new Drone(timeHandler.getTime()));
                else
                    unitPriorityQueue.add(new Drone(-timeHandler.getTimeWhenStopped()));
            }
        }
        if (button.getText().equals("+LING")){
            if (resourcesHandler.getMinerals() >= xZergling.getMinCost() && resourcesHandler.getGas() >= xZergling.getGasCost()) {
                resourcesHandler.decreaseMin(xZergling.getMinCost());
                resourcesHandler.decreaseGas(xZergling.getGasCost());
                supply = supply + xZergling.getSupply();
                supplyMax = supplyMax + xZergling.getSupplyMax();
                if (timeHandler.isTimeRunning())
                    unitPriorityQueue.add(new Zergling(timeHandler.getTime()));
                else
                    unitPriorityQueue.add(new Zergling(-timeHandler.getTimeWhenStopped()));
            }
        }
        if (button.getText().equals("+ROACH")){
            if (resourcesHandler.getMinerals() >= xRoach.getMinCost() && resourcesHandler.getGas() >= xRoach.getGasCost()) {
                resourcesHandler.decreaseMin(xRoach.getMinCost());
                resourcesHandler.decreaseGas(xRoach.getGasCost());
                supply = supply + xRoach.getSupply();
                supplyMax = supplyMax + xRoach.getSupplyMax();
                if (timeHandler.isTimeRunning())
                    unitPriorityQueue.add(new Roach(timeHandler.getTime()));
                else
                    unitPriorityQueue.add(new Roach(-timeHandler.getTimeWhenStopped()));
                //roachNumber++;
            }
        }*/

    }


}
