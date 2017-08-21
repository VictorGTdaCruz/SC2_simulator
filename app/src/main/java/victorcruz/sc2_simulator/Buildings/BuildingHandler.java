package victorcruz.sc2_simulator.Buildings;


import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Comparator;
import java.util.PriorityQueue;

import victorcruz.sc2_simulator.Requisites.TechHandler;
import victorcruz.sc2_simulator.Resources.ResourcesHandler;
import victorcruz.sc2_simulator.Supply.SupplyHandler;
import victorcruz.sc2_simulator.Time.TimeHandler;
import victorcruz.sc2_simulator.Units.UnitHandler;

public class BuildingHandler {

    private Button stcButton22, stcButton21;

    // handlers
    private SupplyHandler supplyHandler;
    private ResourcesHandler resourcesHandler;
    private TimeHandler timeHandler;
    private UnitHandler unitHandler;
    private TechHandler techHandler;

    // handlers to use postDelayed method
    private Handler prodHandler = new Handler(); // used on buildingProduction

    // Unit array to use as mold
    private Building[] xBuilding;

    //zerg buildings
    private int hatcheryNumber = 1, extractorNumber = 0, spawningPoolNumber = 0, banelingNestNumner = 0, RoachWarrenNumber = 0;

    //building production mechanic variables
    private PriorityQueue<Building> buildingPriorityQueue;
    private Comparator<Building> buildingComparator = new BuildingComparator();


    public BuildingHandler(ResourcesHandler resourcesHandler, TimeHandler timeHandler,
                           SupplyHandler supplyHandler, UnitHandler unitHandler,
                           TechHandler techHandler, Button stcButton22, Button stcButton21){

        this.resourcesHandler = resourcesHandler;
        this.timeHandler = timeHandler;
        this.supplyHandler = supplyHandler;
        this.unitHandler = unitHandler;
        this.techHandler = techHandler;

        this.stcButton22 = stcButton22;
        this.stcButton21 = stcButton21;
        setWorkerToGasButtonAlpha();

        buildingPriorityQueue = new PriorityQueue<>(10, buildingComparator);

        xBuilding = new Building[18];

//        xBuilding[0] = new Building(-1, "Hatchery", 1500, 300, 0, 6, 71000, 1, new String[]{}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[1] = new Building(-1, "SpawningPool", 1000, 200, 0, 0, 46000, 1, new String[]{"Hatchery"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[2] = new Building(-1, "Extractor", 500, 25, 0, 0, 21000, 1, new String[]{}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[3] = new Building(-1, "EvolutionChamber", 750, 75, 0, 0, 25000, 1, new String[]{"Hatchery"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[4] = new Building(-1, "RoachWarren", 850, 150, 0, 0, 39000, 1, new String[]{"SpawningPool"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[5] = new Building(-1, "BanelingNest", 850, 100, 50, 0, 43000, 1, new String[]{"SpawningPool"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[6] = new Building(-1, "SpineCrawler", 300, 100, 0, 0, 36000, 2, new String[]{"SpawningPool"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[7] = new Building(-1, "SporeCrawler", 400, 75, 0, 0, 21000, 1, new String[]{"SpawningPool"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[8] = new Building(-1, "Lair", 2000, 150, 100, 0, 57000, 1, new String[]{"Hatchery", "SpawningPool"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[9] = new Building(-1, "HydraliskDen", 850, 100, 100, 0, 29000, 1, new String[]{"Lair"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[10] = new Building(-1, "LurkerDen", 850, 150, 150, 0, 86000, 1, new String[]{"HydraliskDen", "Lair"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[11] = new Building(-1, "Spire", 850, 200, 200, 0, 71000, 1, new String[]{"Lair"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[12] = new Building(-1, "NydusNetwork", 850, 150, 200, 0, 36000, 1, new String[]{"Lair"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[13] = new Building(-1, "NydusWorm", 200, 100, 100, 0, 14000, 1, new String[]{"NydusNetwork"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[14] = new Building(-1, "InfestationPit", 850, 100, 100, 0, 36000, 1, new String[]{"Lair"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[15] = new Building(-1, "Hive", 2500, 200, 150, 0, 71000, 1, new String[]{"InfestationPit", "Lair"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[16] = new Building(-1, "UltraliskCavern", 850, 150, 200, 0, 46000, 1, new String[]{"Hive"}, new String[]{"Structure", "Armored", "Biological"});
//        xBuilding[17] = new Building(-1, "GreaterSpire", 1000, 100, 150, 0, 71000, 1, new String[]{"Spire", "Hive"}, new String[]{"Structure", "Armored", "Biological"});
//

        xBuilding[0] = new Building(-1, "Hatchery", 1500, 300, 0, 6, 71000, 1, new String[]{"SpawningPool","EvolutionChamber"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[1] = new Building(-1, "SpawningPool", 1000, 200, 0, 0, 46000, 1, new String[]{"Lair", "RoachWarren", "BanelingNest", "SpineCrawler", "SporeCrawler", "Zergling", "Queen"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[2] = new Building(-1, "Extractor", 500, 25, 0, 0, 21000, 1, new String[]{}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[3] = new Building(-1, "EvolutionChamber", 750, 75, 0, 0, 25000, 1, new String[]{"Hatchery"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[4] = new Building(-1, "RoachWarren", 850, 150, 0, 0, 39000, 1, new String[]{"Roach"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[5] = new Building(-1, "BanelingNest", 850, 100, 50, 0, 43000, 1, new String[]{"Baneling"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[6] = new Building(-1, "SpineCrawler", 300, 100, 0, 0, 36000, 2, new String[]{}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[7] = new Building(-1, "SporeCrawler", 400, 75, 0, 0, 21000, 1, new String[]{}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[8] = new Building(-1, "Lair", 2000, 150, 100, 0, 57000, 1, new String[]{"HydraliskDen", "InfestationPit", "Spire", "NydusNetwork"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[9] = new Building(-1, "HydraliskDen", 850, 100, 100, 0, 29000, 1, new String[]{"Hydralisk", "LurkerDen"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[10] = new Building(-1, "LurkerDen", 850, 150, 150, 0, 86000, 1, new String[]{"Lurker"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[11] = new Building(-1, "Spire", 850, 200, 200, 0, 71000, 1, new String[]{"Mutalisk", "Corruptor"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[12] = new Building(-1, "NydusNetwork", 850, 150, 200, 0, 36000, 1, new String[]{"NydusWorm"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[13] = new Building(-1, "NydusWorm", 200, 100, 100, 0, 14000, 1, new String[]{}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[14] = new Building(-1, "InfestationPit", 850, 100, 100, 0, 36000, 1, new String[]{"Infestor", "SwarmHost", "Hive"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[15] = new Building(-1, "Hive", 2500, 200, 150, 0, 71000, 1, new String[]{"UltraliskCavern", "GreaterSpire", "Viper"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[16] = new Building(-1, "UltraliskCavern", 850, 150, 200, 0, 46000, 1, new String[]{"Ultralisk"}, new String[]{"Structure", "Armored", "Biological"});
        xBuilding[17] = new Building(-1, "GreaterSpire", 1000, 100, 150, 0, 71000, 1, new String[]{"BroodLord"}, new String[]{"Structure", "Armored", "Biological"});

    }

    public void buildingProduction(final long currentTime) {
        if (buildingPriorityQueue.peek() != null && 150 > buildingPriorityQueue.peek().getReady() - currentTime) {

            final String name = buildingPriorityQueue.peek().getName();
            final long ready = buildingPriorityQueue.peek().getReady();
            final long productionTime = buildingPriorityQueue.peek().getProductionTime();
            final int supplyMax = buildingPriorityQueue.peek().getSupplyMax();

            prodHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.out.println("BUILDING IS READY:" + name);

                    if (name.equals("Hatchery")){
                        // new hatchery larva
                        supplyHandler.increaseSupplyMax(supplyMax);
                        System.out.println("hatch ready............");
                        hatcheryNumber++;
                    }
                    else if (name.equals("SpawningPool")) {

                        // xbuildingBoolean[1] = true
                        // ling.setClickable(true)
                    }
                    else if (name.equals("Extractor")){
                        extractorNumber++;
                        setWorkerToGasButtonAlpha();
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



    public int checkBuildingIndex(String buttonTag){
        for (int i = 0; i < xBuilding.length; i++){
            if (buttonTag.equals(xBuilding[i].getName())){
                return i;
            }
        }
        return -1;
    }

    public void makeBuilding(View view) {

        Button button = (Button) view;

        int index = checkBuildingIndex(button.getTag().toString());
        Building building = new Building(xBuilding[index]);

        if (timeHandler.isGameStarted()) {
            if (techHandler.containsInControl(view.getTag().toString()) &&
                    resourcesHandler.getMinerals() >= building.getMinCost() &&
                    resourcesHandler.getGas() >= building.getGasCost() && unitHandler.hasDrone()) {
                if (timeHandler.isTimeRunning()) {
                    unitHandler.consumeDrone(building.getName());
                    building.setOrderedTime(timeHandler.getTime());
                } else {
                    unitHandler.consumeDrone(building.getName());
                    building.setOrderedTime(-timeHandler.getTimeWhenStopped());
                }
                if (true) { // consumed drone
                    resourcesHandler.decreaseMin(building.getMinCost());
                    resourcesHandler.decreaseGas(building.getGasCost());
                    buildingPriorityQueue.add(building);
                    techHandler.addToControl(building.getRequisites());
                    System.out.println("BUILDING ORDERED: " + building.getName());
                }
            }
        }

    }

    public int getExtractorNumber(){
        return extractorNumber;
    }


    public void setWorkerToGasButtonAlpha(){
        if (extractorNumber != 0) {
            if (resourcesHandler.getWorkersInGas() / 3 < extractorNumber) {
                if (resourcesHandler.getWorkersInGas() == 0){
                    stcButton21.setAlpha(0.3f);
                    stcButton22.setAlpha(1);
                } else{
                    stcButton21.setAlpha(1);
                    stcButton22.setAlpha(1);
                }
            } else {
                stcButton21.setAlpha(1);
                stcButton22.setAlpha(0.3f);
            }
        } else {
                stcButton21.setAlpha(0.3f);
                stcButton22.setAlpha(0.3f);
            }
    }
}