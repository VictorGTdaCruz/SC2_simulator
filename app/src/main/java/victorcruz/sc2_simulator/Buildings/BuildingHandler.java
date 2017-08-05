package victorcruz.sc2_simulator.Buildings;


import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Comparator;
import java.util.PriorityQueue;

import victorcruz.sc2_simulator.Resources.ResourcesHandler;
import victorcruz.sc2_simulator.Time.TimeHandler;

public class BuildingHandler {

    // android components
    private TextView supplyMaxTextView;

    // handlers
    private ResourcesHandler resourcesHandler;
    private TimeHandler timeHandler;

    // handlers to use postDelayed method
    private Handler prodHandler = new Handler(); // used on buildingProduction

    // Unit array to use as mold
    private Building[] xBuilding;

    //zerg buildings
    private int hatcheryNumber = 1, spawningPoolNumber = 0, banelingNestNumner = 0, RoachWarrenNumber = 0;

    //building production mechanic variables
    private PriorityQueue<Building> buildingPriorityQueue;
    private Comparator<Building> buildingComparator = new BuildingComparator();


    public BuildingHandler(ResourcesHandler resourcesHandler, TimeHandler timeHandler, TextView supplyMaxTextView){

        this.resourcesHandler = resourcesHandler;
        this.timeHandler = timeHandler;
        this.supplyMaxTextView = supplyMaxTextView;

        buildingPriorityQueue = new PriorityQueue<>(10, buildingComparator);

        xBuilding = new Building[15];

        xBuilding[0] = new Building(-1, "+HATCH", 1500, -1, 300, 0, 6, -1, -1, 1, -1, 10, 71000, new String[]{"Drone"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[1] = new Building(-1, "+POOL", 1000, -1, 200, 0, 0, -1, -1, 1, -1, -1, 46000, new String[]{"Drone", "Hatchery"}, new String[]{"Structure", "Armored", "Biological"});
    }

    public void buildingProduction(final long currentTime) {
        if (buildingPriorityQueue.peek() != null && 150 > buildingPriorityQueue.peek().getReady() - currentTime) {

            final String name = buildingPriorityQueue.peek().getName();
            final long ready = buildingPriorityQueue.peek().getReady();
            final long productionTime = buildingPriorityQueue.peek().getProductionTime();

            prodHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.out.println("BUILDING IS READY:" + name);

                    if (name.equals("+HATCH")){
                        // new hatchery larva
                        hatcheryNumber++;
                    }
                    else if (name.equals("+POOL")) {

                        // xbuildingBoolean[1] = true
                        // ling.setClickable(true)
                    }
//                    else if (name.equals("+QUEEN")) queenNumber++;
//                    else if (name.equals("+LING")) lingNumber = lingNumber + 2;
//                    else if (name.equals("+BANE")) banelingNumber++;
//                    else if (name.equals("+ROACH")) roachNumber++;
//                    else if (name.equals("+RAVAG")) ravagerNumber++;
//                    else if (name.equals("+OVERS")) overseerNumber++;
//                    else if (name.equals("+HYDRA")) hydraliskNumber++;
//                    else if (name.equals("+LURKER")) lurkerNumber++;


                }
            }, buildingPriorityQueue.peek().getReady() - currentTime);
            buildingPriorityQueue.remove();
            buildingProduction(currentTime);

        }
    }



    public int checkBuildingIndex(String buttonText){
        for (int i = 0; i < xBuilding.length; i++){
            if (buttonText.equals(xBuilding[i].getName())){
                return i;
            }
        }
        return -1;
    }

    public void makeBuilding(View view) {

        Button button = (Button) view;

        int index = checkBuildingIndex(button.getText().toString());
        Building building = new Building(xBuilding[index]);

        if (timeHandler.isGameStarted()) {
            if (resourcesHandler.getMinerals() >= building.getMinCost() && resourcesHandler.getGas() >= building.getGasCost()) {
                if (timeHandler.isTimeRunning()) {
                    //consume drone method
                    building.setOrderedTime(timeHandler.getTime());
                } else {
                    //consume drone method
                    building.setOrderedTime(-timeHandler.getTimeWhenStopped());
                }
                if (true) { // consumed drone
                    resourcesHandler.decreaseMin(building.getMinCost());
                    resourcesHandler.decreaseGas(building.getGasCost());
                    buildingPriorityQueue.add(building);
                    System.out.println("BUILDING ORDERED: " + building.getName());
                }
            }
        }

    }
}