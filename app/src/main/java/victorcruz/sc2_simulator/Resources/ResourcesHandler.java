package victorcruz.sc2_simulator.Resources;

import android.os.Handler;
import android.widget.TextView;

import java.util.PriorityQueue;

import victorcruz.sc2_simulator.Buildings.BuildingHandler;


public class ResourcesHandler {

    private TextView  minTextView, gasTextView;

    private int minerals = 50, gas = 0, dronesInGas = 0;

    public static PriorityQueue<MiningDrone> minPriorityQueue;
    public static PriorityQueue<MiningDrone> gasPriorityQueue;
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

        gasPriorityQueue = new PriorityQueue<>(3, resourceMiningComparator);

        minPriorityQueue = new PriorityQueue<>(100, resourceMiningComparator);

        minPriorityQueue.add(new MiningDrone( -11450, true));
        minPriorityQueue.add(new MiningDrone( -11200, true));
        minPriorityQueue.add(new MiningDrone( -11100, true));
        minPriorityQueue.add(new MiningDrone( -10800, true));
        minPriorityQueue.add(new MiningDrone( -10200, true));
        minPriorityQueue.add(new MiningDrone( -9800, true));
        minPriorityQueue.add(new MiningDrone( -9400, true));
        minPriorityQueue.add(new MiningDrone( -8800, true));
        minPriorityQueue.add(new MiningDrone( -8400, true));
        minPriorityQueue.add(new MiningDrone( -7800, true));
        minPriorityQueue.add(new MiningDrone( -7600, true));
        minPriorityQueue.add(new MiningDrone( -7300, true));

    }

    public void resourceMining(final long currentTime){

        // mineral mining
        if (minPriorityQueue.peek() != null  && 150 >= minPriorityQueue.peek().getReady() - currentTime) {

            //System.out.print("--- DRONE MINED AT ");
            //System.out.print(currentTime + (miningPriorityQueue.peek().getReady() - currentTime));
            //System.out.println(" --- CurrentTime at Mining:" + currentTime);
            minPriorityQueue.add(new MiningDrone(minPriorityQueue.peek().getReady() + minPriorityQueue.peek().calcMineralDistance(), false));

            if (!(minPriorityQueue.peek().getIsFirstMineAction())) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        increaseMin(5);
                    }
                }, minPriorityQueue.peek().getReady() - currentTime);
            }
            minPriorityQueue.remove();
            resourceMining(currentTime);
        }

        // gas mining
        if (gasPriorityQueue.peek() != null && 150 >= gasPriorityQueue.peek().getReady() - currentTime){

            System.out.println("BLABLA");
            gasPriorityQueue.add(new MiningDrone(gasPriorityQueue.peek().getReady() + gasPriorityQueue.peek().calcGasDistance(), false));

            if (!(gasPriorityQueue.peek().getIsFirstMineAction())) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        increaseGas(4);
                        System.out.println("MINED GAS!");
                    }
                }, gasPriorityQueue.peek().getReady() - currentTime);
            }
            gasPriorityQueue.remove();
            resourceMining(currentTime);


        }


    }

    // used when zerg makebuilding
    public void miningPQPeekRemove(String buildingName){
        minPriorityQueue.remove();
        System.out.println("PQ size: " + minPriorityQueue.size());

        // Compensates the distance the drone has to walk before making a building:
        // basically it recreates the next mining drone with the flag true, making the next 5 min increase not happen
        // if the building is a hatchery, the next 2 cicles wont happen
        long auxReady = minPriorityQueue.peek().getReady();
        minPriorityQueue.remove();
        if (buildingName.equals("Hatchery")){
            long auxReady2 = minPriorityQueue.peek().getReady();
            minPriorityQueue.remove();
            minPriorityQueue.add(new MiningDrone(auxReady2, true));
        }
        minPriorityQueue.add(new MiningDrone(auxReady, true));
    }

    public void sendWorkerToGas(long currentTime){

        if (buildingHandler.getExtractorNumber() > dronesInGas / 3 ) {
            System.out.println("YEEEEAH");
            minPriorityQueue.remove();
            if (currentTime - lastCurrentTime > 2000) {
                System.out.println("-10000");
                gasPriorityQueue.add(new MiningDrone(currentTime - 10000, true));
                dronesInGas++;
            } else if (currentTime - lastlastCurrentTime < 2000) {
                System.out.println("-6000");
                gasPriorityQueue.add(new MiningDrone(currentTime - 6000, true));
                dronesInGas++;
            } else {
                System.out.println("-8000");
                gasPriorityQueue.add(new MiningDrone(currentTime - 8000, true));
                dronesInGas++;
            }
            System.out.println(dronesInGas);

            lastlastCurrentTime = lastCurrentTime;
            lastCurrentTime = currentTime;

        }
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

    public int getDronesInGas(){
        return dronesInGas;
    }

}
