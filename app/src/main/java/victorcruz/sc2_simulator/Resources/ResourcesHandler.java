package victorcruz.sc2_simulator.Resources;

import android.os.Handler;
import android.widget.TextView;

import java.util.PriorityQueue;

import victorcruz.sc2_simulator.Buildings.BuildingHandler;


public class ResourcesHandler {

    private TextView  minTextView, gasTextView;

    private int minerals = 50, gas = 0, workersInGas = 0;

    public static PriorityQueue<MiningDrone>[] minPriorityQueue = new PriorityQueue[5];
    public static PriorityQueue<MiningDrone>[] gasPriorityQueue = new PriorityQueue[10];
    private ResourceMiningComparator resourceMiningComparator = new ResourceMiningComparator();

    private BuildingHandler buildingHandler;

    private long lastCurrentTime = 0, lastlastCurrentTime = 0; // used on resourceMining -> gas

    // handles the creation of unit in the right millisecond
    private Handler handler; // used on resourceMining

    public ResourcesHandler(TextView minTextView, TextView gasTextView){

        this.minTextView = minTextView;
        this.gasTextView = gasTextView;
        setMinerals(minerals);
        setGas(gas);

        handler = new Handler();

        gasPriorityQueue[0] = new PriorityQueue<>(3, resourceMiningComparator);

        minPriorityQueue[0] = new PriorityQueue<>(16, resourceMiningComparator);
        minPriorityQueue[1] = new PriorityQueue<>(16, resourceMiningComparator);


        minPriorityQueue[0].add(new MiningDrone( -11450, true));
        minPriorityQueue[0].add(new MiningDrone( -11200, true));
        minPriorityQueue[0].add(new MiningDrone( -11100, true));
        minPriorityQueue[0].add(new MiningDrone( -10800, true));
        minPriorityQueue[0].add(new MiningDrone( -10200, true));
        minPriorityQueue[0].add(new MiningDrone( -9800, true));
        minPriorityQueue[0].add(new MiningDrone( -9400, true));
        minPriorityQueue[0].add(new MiningDrone( -8800, true));
        minPriorityQueue[0].add(new MiningDrone( -8400, true));
        minPriorityQueue[0].add(new MiningDrone( -7800, true));
        minPriorityQueue[0].add(new MiningDrone( -7600, true));
        minPriorityQueue[0].add(new MiningDrone( -7300, true));

    }


    //main method
    public void resourceMining(final long currentTime){

        for (int i = 1; i < minPriorityQueue.length; i++) {
            // mineral mining
            if (minPriorityQueue[i].peek() != null && 150 >= minPriorityQueue[i].peek().getReady() - currentTime) {

                //System.out.print("--- DRONE MINED AT ");
                //System.out.print(currentTime + (miningPriorityQueue.peek().getReady() - currentTime));
                //System.out.println(" --- CurrentTime at Mining:" + currentTime);
                minPriorityQueue[i].add(new MiningDrone(minPriorityQueue[i].peek().getReady() +
                                            minPriorityQueue[i].peek().calcMineralDistance(), false));

                if (!(minPriorityQueue[i].peek().getIsFirstMineAction())) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            increaseMin(5);
                        }
                    }, minPriorityQueue[i].peek().getReady() - currentTime);
                }
                minPriorityQueue[i].remove();
                resourceMining(currentTime);
            }
        }

        for (int i = 0; i < gasPriorityQueue.length; i++) {
            // gas mining
            if (gasPriorityQueue[i].peek() != null && 150 >= gasPriorityQueue[i].peek().getReady() - currentTime) {

                gasPriorityQueue[i].add(new MiningDrone(gasPriorityQueue[i].peek().getReady()
                                            + gasPriorityQueue[i].peek().calcGasDistance(), false));

                if (!(gasPriorityQueue[i].peek().getIsFirstMineAction())) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            increaseGas(4);
                            System.out.println("MINED GAS!");
                        }
                    }, gasPriorityQueue[i].peek().getReady() - currentTime);
                }
                gasPriorityQueue[i].remove();
                resourceMining(currentTime);

            }

        }

    }


    // used when zerg makebuilding
    public void miningPQPeekRemove(String buildingName){
        minPriorityQueue[getLastMinPriorityQueueIndex()].remove();

        System.out.println("PQ size: " + minPriorityQueue[0].size());

        // Compensates the distance the drone has to walk before making a building:
        // basically it recreates the next mining drone with the flag true, making the next 5 min increase not happen
        // if the building is a hatchery, the next 2 cicles wont happen
        //
        // Here the minPQ[0] is always used so i wont need to check if there are drones on other PQs
        long auxReady = minPriorityQueue[0].peek().getReady();
        minPriorityQueue[0].remove();
        if (buildingName.equals("Hatchery")){
            long auxReady2 = minPriorityQueue[0].peek().getReady();
            minPriorityQueue[0].remove();
            minPriorityQueue[0].add(new MiningDrone(auxReady2, true));
        }
        minPriorityQueue[0].add(new MiningDrone(auxReady, true));
    }


    // 4 gas methods
    public void setCorrectTimeinGasPQ (long currentTime, long correctTime){

        PriorityQueue lastGasPriorityQueue = gasPriorityQueue[getLastGasPriorityQueueIndex()];

        if (lastGasPriorityQueue.size() == 0) { // NO DRONES MINING

            System.out.println("NAO TINHA DRONE!");
            lastGasPriorityQueue.add(new MiningDrone(currentTime - 11000, true));

        } else if (lastGasPriorityQueue.size() == 1){ // ONE DRONE MINING GAS

            System.out.println("TINHA 1 DRONE!");
            if ( gasPriorityQueue[getLastGasPriorityQueueIndex()].peek().getReady() - currentTime < (4200 / 3)){ // if the chosen drone is not going to be on peek
                System.out.println("2 IS NOT ON PEEK");
                lastGasPriorityQueue.add(new MiningDrone(gasPriorityQueue[getLastGasPriorityQueueIndex()].peek().getReady() + (4000/3) - 12000, true));
            } else { // if the chosen drone IS going to be on peek
                System.out.println("2 IS ON PEEK");
                lastGasPriorityQueue.remove();
                lastGasPriorityQueue.add(new MiningDrone(currentTime - 12000, true)); // precisa ver um caso do tempo ainda
                lastGasPriorityQueue.add(new MiningDrone(gasPriorityQueue[getLastGasPriorityQueueIndex()].peek().getReady() + (4000 / 3) - 12000, false));
            }

        } else if (gasPriorityQueue[getLastGasPriorityQueueIndex()].size() == 2){ // TWO DRONES MINING GAS

            System.out.println("TINHA 2 DRONE!");
            if ( gasPriorityQueue[getLastGasPriorityQueueIndex()].peek().getReady() - currentTime < (4200 / 3) * 2){
                System.out.println("3 IS NOT ON PEEK");
                lastGasPriorityQueue.add(new MiningDrone(gasPriorityQueue[getLastGasPriorityQueueIndex()].peek().getReady() + ((4000 / 3) * 2) - 12000, true));
            } else {
                System.out.println("3 IS ON PEEK");
                lastGasPriorityQueue.remove();
                lastGasPriorityQueue.remove();
                lastGasPriorityQueue.add(new MiningDrone(currentTime - 12000, true));
                lastGasPriorityQueue.add(new MiningDrone(gasPriorityQueue[getLastGasPriorityQueueIndex()].peek().getReady() + (4000 / 3) - 12000, false));
                lastGasPriorityQueue.add(new MiningDrone(gasPriorityQueue[getLastGasPriorityQueueIndex()].peek().getReady() + ((4000 / 3) * 2) - 12000, false));

            }
        }

    }

    public void sendWorkerToGas(long currentTime){

        if (buildingHandler.getExtractorNumber() > workersInGas / 3 ) {
            System.out.println("Extractor number:" + buildingHandler.getExtractorNumber());
            minPriorityQueue[getLastMinPriorityQueueIndex()].remove();

            //if (currentTime - lastCurrentTime > 2000) { // first click
                setCorrectTimeinGasPQ(currentTime, -10000);
                workersInGas++;
            /*} else if (currentTime - lastlastCurrentTime < 2000) { // third click
                setCorrectTimeinGasPQ(currentTime, - 6000);
                workersInGas++;
            } else { // second click
                setCorrectTimeinGasPQ(currentTime, - 8000);
                workersInGas++;
            }*/
            System.out.println(workersInGas);

            lastlastCurrentTime = lastCurrentTime;
            lastCurrentTime = currentTime;
            buildingHandler.setWorkerToGasButtonAlpha();

        }
    }

    public void takeWorkerOutOfGas(long currentTime){
        if (workersInGas > 0) {
            gasPriorityQueue[getLastGasPriorityQueueIndex()].remove();
            //if (minPriorityQueue[getLastMinPriorityQueueIndex()].size() == 16) {
                minPriorityQueue[getLastMinPriorityQueueIndex()].add(new MiningDrone(currentTime - 11000, true));
            //}
            workersInGas--;
            buildingHandler.setWorkerToGasButtonAlpha();
            System.out.println("took drone out!");
            System.out.println(workersInGas);
        }
    }

    public static int getLastMinPriorityQueueIndex(){
        for (int i = minPriorityQueue.length; i >= 0; i--){
            if (minPriorityQueue[i].size() > 0)
                return i;
        }
        return 0;
    }

    public static int getLastGasPriorityQueueIndex(){
        for (int i = gasPriorityQueue.length; i >= 0; i--){
            if (gasPriorityQueue[i].size() > 0)
                return i;
        }
        return 0;
    }


    // aux methods
    public int getWorkersInGas(){
        return workersInGas;
    }

    public int getMinerals(){
        return  minerals;
    }

    public int getGas(){
        return gas;
    }

    public void setMinerals(int minerals){
        this.minerals = minerals;
        minTextView.setText(Integer.toString(minerals));
    }

    public void setGas(int gas){
        this.gas = gas;
        gasTextView.setText(Integer.toString(gas));
    }

    public void increaseMin(int amount){
        minerals = minerals + amount;
        minTextView.setText(Integer.toString(minerals));
    }

    public void increaseGas(int amount){
        gas = gas + amount;
        gasTextView.setText(Integer.toString(gas));
    }

    public void decreaseMin(int amount){
        if (minerals >= amount) {
            minerals = minerals - amount;
            minTextView.setText(Integer.toString(minerals));
        }
    }

    public void decreaseGas(int amount){
        if (gas >= amount) {
            gas = gas - amount;
            gasTextView.setText(Integer.toString(gas));
        }
    }

    public void setBuildingHandler(BuildingHandler buildingHandler){
        this.buildingHandler = buildingHandler;
    }

    public PriorityQueue<MiningDrone>[] getMinPriorityQueue() {
        return minPriorityQueue;
    }

    public PriorityQueue<MiningDrone>[] getGasPriorityQueue() {
        return gasPriorityQueue;
    }
}
