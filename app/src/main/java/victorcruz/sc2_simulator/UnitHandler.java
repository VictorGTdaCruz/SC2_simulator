package victorcruz.sc2_simulator;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import victorcruz.sc2_simulator.Units.Larva;
import victorcruz.sc2_simulator.Units.MiningDrone;

public class UnitHandler {

    // android components
    private TextView supplyTextView, supplyMaxTextView, larvaTextView;

    // handlers
    private ResourcesHandler resourcesHandler;
    private TimeHandler timeHandler;

    // handlers to use postDelayed method
    private Handler prodHandler = new Handler(); // used on unitProduction
    private Handler larvaHandler = new Handler(); // used on growLarva

    // Unit array to use as mold
    private Unit[] xUnit;

    // zerg units
    private int overlordNumber = 0, queenNumber = 0, lingNumber = 0, banelingNumber = 0, roachNumber = 0,
                ravagerNumber = 0, overseerNumber = 0, hydraliskNumber = 0, lurkerNumber = 0;
    private int workerNumber = 12, supply = 12, supplyMax = 14;

    // unit production mechanic variables
    private PriorityQueue<Unit> priorityQueue;
    private Comparator<Unit> comparator = new UnitComparator();

    // larva mechanic variables
    private LinkedList<PriorityQueue<Larva>> larvaSystem;
    private int[] larvaCount;
    private boolean[] isProducingLarva;


    public UnitHandler(ResourcesHandler resourcesHandler, TimeHandler timeHandler, TextView supplyTextView,
                       TextView supplyMaxTextView, TextView larvaTextView){
        priorityQueue = new PriorityQueue<>(10, comparator);
        this.resourcesHandler = resourcesHandler;
        this.timeHandler = timeHandler;

        this.supplyTextView = supplyTextView;
        this.supplyMaxTextView = supplyMaxTextView;
        supplyTextView.setText(Integer.toString(supply));
        supplyMaxTextView.setText(Integer.toString(supplyMax));

        this.larvaTextView = larvaTextView;
        larvaTextView.setText(Integer.toString(3));

        // Linked list to manage all PriorityQueues
        larvaSystem = new LinkedList<>();

        // first 2 PriorityQueues representing the first hatchery
        // the first represents the available larva
        // the second represents the larva production cycle
        larvaSystem.add(new PriorityQueue<Larva>(19, comparator));
        larvaSystem.add(new PriorityQueue<Larva>(4, comparator));

        // it starts with 3 larva
        larvaSystem.get(0).add(new Larva(-11000));
        larvaSystem.get(0).add(new Larva(-11000));
        larvaSystem.get(0).add(new Larva(-11000));

        larvaCount = new int[2];
        larvaCount[0] = 3;

        isProducingLarva = new boolean[2];
        isProducingLarva[1] = false;


        xUnit = new Unit[15];
        xUnit[0] = new Unit(-1, "+DRONE", 50, -1, 50, 0, 1, 0, -1, -1, 0, 1, -1, -1, 1, 8, 12000, 3.94, 3.94, new String[]{"Larva"}, new String[]{"Biological", "Light"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[1] = new Unit(-1, "+OVERL", 200, -1, 100, 0, 0, 8, -1, -1, 0, 1, -1, -1, -1, 11, 18000, 0.82, -1, new String[]{"Larva"}, new String[]{"Biological", "Armored"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[2] = new Unit(-1, "+QUEEN", 175, -1, 150, 0, 2, 0, 25, 200, 1, 1, -1, -1, 2, 9, 36000, 1.31, 3.5, new String[]{"Hatchery", "Spawning Pool"}, new String[]{"Biological", "Psionic"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[3] = new Unit(-1, "+LING", 35, -1, 50, 0, 1, 0, -1, -1, 0, 1, -1, -1, 1, 8, 17000, 4.13, 5.37, new String[]{"Larva", "Spawning Pool"}, new String[]{"Biological", "Light"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[4] = new Unit(-1, "+BANE", 30, -1, 25, 25, 0, 0, -1, -1, 0, 1, -1, -1, 2, 8, 14000, 3.5, 4.55, new String[]{"Zergling", "Baneling Nest"}, new String[]{"Biological"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[5] = new Unit(-1, "+ROACH", 145, -1, 75, 25, 2, 0, -1, -1, 1, 1, -1, -1, 2, 9, 19000, 3.15, 4.09, new String[]{"Larva", "Roach Warren"}, new String[]{"Biological", "Armored"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[6] = new Unit(-1, "+RAVAG", 120, -1, 25, 75, 1, 0, -1, -1, 1, 1, -1, -1, 4, 9, 9000, 3.85, 5, new String[]{"Roach", "Roach Warren"}, new String[]{"Biological"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[7] = new Unit(-1, "+OVERS", 200, -1, 50, 50, 1, 0, -1, -1, 1, 1, -1, -1, -1, 11, 12000, 2.62, -1, new String[]{"Overlord", "Lair"}, new String[]{"Biological", "Armored", "Detector"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[8] = new Unit(-1, "+HYDRA", 90, -1, 100, 50, 2, 0, -1, -1, 0, 1, -1, -1, 2, 9, 24000, 3.15, 4.09, new String[]{"Larva", "Hydralisk Den"}, new String[]{"Biological", "Light"}, new UnitAttackInfo[]{}, new UnitAbility[]{});
        xUnit[9] = new Unit(-1, "+LURKER", 200, -1, 50, 100, 1, 0, -1, -1, 1, 1, -1, -1, 4, -1, 18000, 4.13, 5.37, new String[]{"Hydralisk", "Lurker Den"}, new String[]{"Biological", "Armored"}, new UnitAttackInfo[]{}, new UnitAbility[]{});

    }
    /*
     I had a class dedicated to every unit in the game, but then i had to write a bunch of
     code to create each different unit, as you can see on the comments of the makeUnit method.
     The way i figured out not to have a giant method with every unit specified, was to make
     "Unit" the universal class and the NAME variable is what compares each unit ingame.
    */

    /*
     Larva Mechanics: The ideal larva number on a hatchery is 3. When the user clicks on a unit,
     one larva is spent and scheduleLarva is triggered. It only creates a larva object on the
     correct PriorityQueue. The growLarva runs every second, checking if a larva is scheduled to
     the current second. If it is, it runs postDelayed to precisely create a new Larva object on the
     "available to spend" PriorityQueue (Now thinking, this didnt need to be a PQ, but its cool).
     If the available larva number is now 3, the cycle will end by the ScheduleLarva if, being
     triggered if a larva is spent once again.

     If a hachery is created in the future with less than 3 larva, wich it will, I should trigger
     the scheduleLarva method manually.
    */
    public void useLarva(long currentTime){

        // Chooses a larva to be used/destroyed and then calls scheduleLarva
        int larvaSystemIndex = 0;
        for (;larvaSystemIndex < larvaSystem.size(); larvaSystemIndex = larvaSystemIndex + 2){

            if (larvaSystem.get(larvaSystemIndex).peek() != null){
                larvaSystem.get(larvaSystemIndex).remove();
                larvaCount[larvaSystemIndex]--;
                larvaTextView.setText(Integer.toString(larvaCount[larvaSystemIndex]));
                scheduleLarva(currentTime, larvaSystemIndex);
                break;
            }

        }
    }


    public void scheduleLarva(final long currentTime, final int larvaSystemIndex){

        // Creates a larva in the "hatchery is producing larva" PriorityQueue
        if (larvaSystem.get(larvaSystemIndex).size() < 3 && !(isProducingLarva[larvaSystemIndex + 1])) {
            System.out.println("Larva number: " + larvaSystem.get(larvaSystemIndex).size());
            isProducingLarva[larvaSystemIndex + 1] = true;
            System.out.println("Producing larva...");
            larvaSystem.get(larvaSystemIndex + 1).add(new Larva(currentTime));
        }

    }

    public void growLarva(final long currentTime){

        // Checks if its the right second to remove the larva scheduled and add it to the
        // available larva PriorityQueue
        int larvaSystemIndex = 0;
        for (; larvaSystemIndex < larvaSystem.size(); larvaSystemIndex = larvaSystemIndex + 2){
            if (larvaSystem.get(larvaSystemIndex + 1).peek() != null &&
                    1000 > larvaSystem.get(larvaSystemIndex + 1).peek().getReady() - currentTime){

                final int finalLarvaSystemIndex = larvaSystemIndex;
                final long timeofPostDelayed =  larvaSystem.get(finalLarvaSystemIndex + 1).peek().getReady() - currentTime;

                larvaHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        larvaSystem.get(finalLarvaSystemIndex).add(new Larva(currentTime + timeofPostDelayed - 11000));
                        larvaCount[finalLarvaSystemIndex]++;
                        larvaTextView.setText(Integer.toString(larvaCount[finalLarvaSystemIndex]));
                        isProducingLarva[finalLarvaSystemIndex + 1] = false;
                        scheduleLarva(currentTime + timeofPostDelayed, finalLarvaSystemIndex);
                    }
                }, larvaSystem.get(larvaSystemIndex + 1).peek().getReady() - currentTime);

                larvaSystem.get(larvaSystemIndex + 1).remove();

            }
        }

    }


    public void unitProduction(final long currentTime) {
        if (priorityQueue.peek() != null && 1000 > priorityQueue.peek().getReady() - currentTime) {

            final String name = priorityQueue.peek().getName();
            final long ready = priorityQueue.peek().getReady();
            final long productionTime = priorityQueue.peek().getProductionTime();
            final int supplyMax = priorityQueue.peek().getSupplyMax();

            prodHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.out.println("UNIT IS READY:" + name);

                    if (name.equals("+DRONE")){
                        ResourcesHandler.minPriorityQueue.add(new MiningDrone(ready - productionTime, true));
                        workerNumber++;
                    }
                    else if (name.equals("+OVERL")) {
                        overlordNumber++;
                        increaseSupplyMax(8);
                    }
                    else if (name.equals("+QUEEN")) queenNumber++;
                    else if (name.equals("+LING")) lingNumber = lingNumber + 2;
                    else if (name.equals("+BANE")) banelingNumber++;
                    else if (name.equals("+ROACH")) roachNumber++;
                    else if (name.equals("+RAVAG")) ravagerNumber++;
                    else if (name.equals("+OVERS")) overseerNumber++;
                    else if (name.equals("+HYDRA")) hydraliskNumber++;
                    else if (name.equals("+LURKER")) lurkerNumber++;


                }
            }, priorityQueue.peek().getReady() - currentTime);
            priorityQueue.remove();
            unitProduction(currentTime);

        }
    }

    public int getWorkerNumber(){
        return workerNumber;
    }

    public void increaseSupply(int amount){
        supply = supply + amount;
        supplyTextView.setText(Integer.toString(supply));
    }

    public void increaseSupplyMax(int amount){
        supplyMax = supplyMax + amount;
        supplyMaxTextView.setText(Integer.toString(supplyMax));

    }

    public int checkUnitIndex(String buttonText){
        for (int i = 0; i < xUnit.length; i++){
            if (buttonText.equals(xUnit[i].getName())){
                return i;
            }
        }
        return -1;
    }


    public void makeUnit(View view){

        Button button = (Button) view;

        int index = checkUnitIndex(button.getText().toString());
        Unit unit = xUnit[index];


        if (resourcesHandler.getMinerals() >= unit.getMinCost() && resourcesHandler.getGas() >= unit.getGasCost()
                && supply + xUnit[index].getSupply() <= supplyMax) {
            if (timeHandler.isTimeRunning()) {
                unit.setOrderedTime(timeHandler.getTime());
                useLarva(timeHandler.getTime());
            } else{
                unit.setOrderedTime(-timeHandler.getTimeWhenStopped());
                useLarva(-timeHandler.getTimeWhenStopped());
            }
            resourcesHandler.decreaseMin(unit.getMinCost());
            resourcesHandler.decreaseGas(unit.getGasCost());
            increaseSupply(unit.getSupply());
            priorityQueue.add(unit);
            System.out.println("UNIT ORDERED: " + unit.getName());

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
                    priorityQueue.add(new Drone(timeHandler.getTime()));
                else
                    priorityQueue.add(new Drone(-timeHandler.getTimeWhenStopped()));
            }
        }
        if (button.getText().equals("+LING")){
            if (resourcesHandler.getMinerals() >= xZergling.getMinCost() && resourcesHandler.getGas() >= xZergling.getGasCost()) {
                resourcesHandler.decreaseMin(xZergling.getMinCost());
                resourcesHandler.decreaseGas(xZergling.getGasCost());
                supply = supply + xZergling.getSupply();
                supplyMax = supplyMax + xZergling.getSupplyMax();
                if (timeHandler.isTimeRunning())
                    priorityQueue.add(new Zergling(timeHandler.getTime()));
                else
                    priorityQueue.add(new Zergling(-timeHandler.getTimeWhenStopped()));
            }
        }
        if (button.getText().equals("+ROACH")){
            if (resourcesHandler.getMinerals() >= xRoach.getMinCost() && resourcesHandler.getGas() >= xRoach.getGasCost()) {
                resourcesHandler.decreaseMin(xRoach.getMinCost());
                resourcesHandler.decreaseGas(xRoach.getGasCost());
                supply = supply + xRoach.getSupply();
                supplyMax = supplyMax + xRoach.getSupplyMax();
                if (timeHandler.isTimeRunning())
                    priorityQueue.add(new Roach(timeHandler.getTime()));
                else
                    priorityQueue.add(new Roach(-timeHandler.getTimeWhenStopped()));
                //roachNumber++;
            }
        }*/

    }


}
