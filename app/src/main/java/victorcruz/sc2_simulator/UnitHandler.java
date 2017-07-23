package victorcruz.sc2_simulator;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.IntSupplier;

import victorcruz.sc2_simulator.Units.Army.Roach;
import victorcruz.sc2_simulator.Units.Army.Zergling;
import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.Workers.Drone.Drone;
import victorcruz.sc2_simulator.Units.Workers.Drone.MiningDrone;

public class UnitHandler {

    private TextView supplyTextView;

    private PriorityQueue<Unit> priorityQueue;
    private Comparator<Unit> comparator = new UnitComparator();

    private ResourcesHandler resourcesHandler;
    private TimeHandler timeHandler;

    private Handler handler = new Handler(); // used on unitProduction

    private int workerNumber = 12, lingNumber = 0, roachNumber = 0, supply = 12, supplyMax = 14;

    // The unit objects with "x" are just so the handler can have access to info like resource cost
    private Drone xDrone;
    private Zergling xZergling;
    private Roach xRoach;


    public UnitHandler(ResourcesHandler resourcesHandler, TimeHandler timeHandler, TextView supplyTextView){
        priorityQueue = new PriorityQueue<>(10, comparator);
        this.resourcesHandler = resourcesHandler;
        this.timeHandler = timeHandler;
        this.supplyTextView = supplyTextView;
        supplyTextView.setText(Integer.toString(supply));

        xDrone = new Drone(0);
        xZergling = new Zergling(0);
        xRoach = new Roach(0);

        
    }

    public void unitProduction(final long currentTime) {
        if (priorityQueue.peek() != null && 1000 > priorityQueue.peek().getReady() - currentTime) {

            final String name = priorityQueue.peek().getName();
            final long ready = priorityQueue.peek().getReady();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.out.println("UNIT IS READY:" + name);
                    // Add content here
                    if (name.equals("Drone")){
                        ResourcesHandler.miningPriorityQueue.add(new MiningDrone(ready - 12000, true));
                        workerNumber++;
                    }
                    else if (name.equals("Zergling")) lingNumber = lingNumber + 2;
                    else if (name.equals("Roach")) roachNumber++;


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

    public void makeUnit(View view){

        Button button = (Button) view;


        if (button.getText().equals("+DRONE")){
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
        }

    }


}
