package victorcruz.sc2_simulator.Resources;

import android.os.Handler;
import android.widget.TextView;

import java.util.PriorityQueue;

import victorcruz.sc2_simulator.Buildings.BuildingHandler;


public class ResourcesHandler {

    private TextView  minTextView, gasTextView;

    private int minerals = 50, gas = 0, workersInMinerals = 12, workersInGas = 0, idleWorkers = 0;

    public static PriorityQueue<MiningDrone>[] minPriorityQueue = new PriorityQueue[2];
    public static PriorityQueue<MiningDrone>[] gasPriorityQueue = new PriorityQueue[1];
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

        minPriorityQueue[0] = new PriorityQueue<>(16, resourceMiningComparator);

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

    // 3 gas methods
    public void setCorrectTimeinGasPQ (long currentTime, long correctTime){

        PriorityQueue nextGasPriorityQueue = gasPriorityQueue[getNextGasPriorityQueueIndex()];

        if (nextGasPriorityQueue.size() == 0) { // NO DRONES MINING

            System.out.println("NAO TINHA DRONE!");
            nextGasPriorityQueue.add(new MiningDrone(currentTime - 11000, true));

        } else if (nextGasPriorityQueue.size() == 1){ // ONE DRONE MINING GAS

            System.out.println("TINHA 1 DRONE!");
            if ( gasPriorityQueue[getLastGasPriorityQueueIndex()].peek().getReady() - currentTime < (4200 / 3)){ // if the chosen drone is not going to be on peek
                System.out.println("2 IS NOT ON PEEK");
                nextGasPriorityQueue.add(new MiningDrone(gasPriorityQueue[getNextGasPriorityQueueIndex()].peek().getReady() + (4000/3) - 12000, true));
            } else { // if the chosen drone IS going to be on peek
                System.out.println("2 IS ON PEEK");
                nextGasPriorityQueue.remove();
                nextGasPriorityQueue.add(new MiningDrone(currentTime - 12000, true)); // precisa ver um caso do tempo ainda
                nextGasPriorityQueue.add(new MiningDrone(gasPriorityQueue[getNextGasPriorityQueueIndex()].peek().getReady() + (4000 / 3) - 12000, false));
            }

        } else if (gasPriorityQueue[getLastGasPriorityQueueIndex()].size() == 2){ // TWO DRONES MINING GAS

            System.out.println("TINHA 2 DRONE!");
            if ( gasPriorityQueue[getLastGasPriorityQueueIndex()].peek().getReady() - currentTime < (4200 / 3) * 2){
                System.out.println("3 IS NOT ON PEEK");
                nextGasPriorityQueue.add(new MiningDrone(gasPriorityQueue[getNextGasPriorityQueueIndex()].peek().getReady() + ((4000 / 3) * 2) - 12000, true));
            } else {
                System.out.println("3 IS ON PEEK");
                nextGasPriorityQueue.remove();
                nextGasPriorityQueue.remove();
                nextGasPriorityQueue.add(new MiningDrone(currentTime - 12000, true));
                nextGasPriorityQueue.add(new MiningDrone(gasPriorityQueue[getNextGasPriorityQueueIndex()].peek().getReady() + (4000 / 3) - 12000, false));
                nextGasPriorityQueue.add(new MiningDrone(gasPriorityQueue[getNextGasPriorityQueueIndex()].peek().getReady() + ((4000 / 3) * 2) - 12000, false));

            }
        }

    }

    public void sendWorkerToGas(long currentTime){

        if (buildingHandler.getGasNumber() > workersInGas / 3 ) {
            System.out.println("Extractor number:" + buildingHandler.getGasNumber());
            if (idleWorkers > 0){
                decreaseIdleWorkers();
            } else {
                minPriorityQueue[getLastMinPriorityQueueIndex()].remove();
            }

            setCorrectTimeinGasPQ(currentTime, -10000);
            workersInGas++;
            System.out.println(workersInGas);

            lastlastCurrentTime = lastCurrentTime;
            lastCurrentTime = currentTime;
            buildingHandler.setWorkerToGasButtonAlpha();

        }
    }

    public void takeWorkerOutOfGas(long currentTime){
        if (workersInGas > 0) {
            gasPriorityQueue[getLastGasPriorityQueueIndex()].remove();
            minPriorityQueue[getNextMinPriorityQueueIndex()].add(new MiningDrone(currentTime - 11000, true));
            workersInGas--;
            buildingHandler.setWorkerToGasButtonAlpha();
            System.out.println("took drone out!");
            System.out.println(workersInGas);
        }
    }

    // used on new Drone
    public void newWorker(long ready, long productionTime) {
        if (getNextMinPriorityQueueIndex() == -1) {
            increaseIdleWorkers();
        } else {
            minPriorityQueue[getNextMinPriorityQueueIndex()].add(new MiningDrone(ready - productionTime, true));
        }
    }

    // used when zerg makebuilding
    public void miningPQPeekRemove(String buildingName){
        if (idleWorkers > 0){
            decreaseIdleWorkers();
        } else {
            minPriorityQueue[getLastMinPriorityQueueIndex()].remove();
        }

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

    // getNext and get last for PQs
    // getNext will return the first PQ that is not full
    // getLast will return the last PQ that has an element
    public static int getLastMinPriorityQueueIndex(){
        for (int i = minPriorityQueue.length; i >= 0; i--){
            if (minPriorityQueue[i].size() > 0)
                return i;
        }
        return -1;
    }

    public int getNextMinPriorityQueueIndex(){
        for (int i = 0; i < minPriorityQueue.length; i++){
            if (minPriorityQueue[i].size() < 16)
                return i;
        }
        return -1;
    }

    public static int getLastGasPriorityQueueIndex(){
        for (int i = gasPriorityQueue.length; i >= 0; i--){
            if (gasPriorityQueue[i].size() > 0)
                return i;
        }
        return -1;
    }

    public static int getNextGasPriorityQueueIndex(){
        for (int i = 0; i < gasPriorityQueue.length; i++){
            if (gasPriorityQueue[i].size() < 3)
                return i;
        }
        return -1;
    }


    // aux methods
    public void increaseIdleWorkers(){
        idleWorkers++;
        System.out.println("Worker is idle!");
    }

    public void decreaseIdleWorkers(){
        idleWorkers--;
        System.out.println("-1 worker idle!");
    }

    public void increaseMinPQSize(int hatcheryNumber){
        minPriorityQueue[hatcheryNumber - 1] = new PriorityQueue<>(16, resourceMiningComparator);
    }

    public void increaseGasPQSize(int gasNumber){
        gasPriorityQueue[gasNumber - 1] = new PriorityQueue<>(3, resourceMiningComparator);
    }

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
