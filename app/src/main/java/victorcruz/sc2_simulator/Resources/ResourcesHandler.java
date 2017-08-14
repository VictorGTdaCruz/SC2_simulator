package victorcruz.sc2_simulator.Resources;

import android.os.Handler;
import android.widget.TextView;

import java.util.PriorityQueue;

import victorcruz.sc2_simulator.Resources.MiningDrone;
import victorcruz.sc2_simulator.Resources.ResourceMiningComparator;


public class ResourcesHandler {

    private TextView  minTextView, gasTextView;

    private int minerals = 50, gas = 100;

    public static PriorityQueue<MiningDrone> minPriorityQueue;
    private ResourceMiningComparator resourceMiningComparator = new ResourceMiningComparator();

    // handles the creation of unit in the right millisecond
    private Handler handler; // used on resourceMining

    public ResourcesHandler(TextView minTextView, TextView gasTextView){

        this.minTextView = minTextView;
        this.gasTextView = gasTextView;
        setMinerals(minerals);
        setGas(gas);


        handler = new Handler();

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

        if (minPriorityQueue.peek() != null  && 150 >= minPriorityQueue.peek().getReady() - currentTime) {

            //System.out.print("--- DRONE MINED AT ");
            //System.out.print(currentTime + (miningPriorityQueue.peek().getReady() - currentTime));
            //System.out.println(" --- CurrentTime at Mining:" + currentTime);
            minPriorityQueue.add(new MiningDrone(minPriorityQueue.peek().getReady() + minPriorityQueue.peek().calcDistance(), false));

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
    }

    public void miningPQPeekRemove(String buildingName){ // used when zerg makebuilding
        minPriorityQueue.remove();
        System.out.println("PQ size: " + minPriorityQueue.size());

        // Compensates the distance the dorne has to walk before making a building:
        // basically it recreates the next mining drone with the flag true, making the next 5 min increase not happen
        // if the building is a hatchery, the next 2 cicles dont happen
        long auxReady = minPriorityQueue.peek().getReady();
        minPriorityQueue.remove();
        if (buildingName.equals("Hatchery")){
            long auxReady2 = minPriorityQueue.peek().getReady();
            minPriorityQueue.remove();
            minPriorityQueue.add(new MiningDrone(auxReady2, true));
        }
        minPriorityQueue.add(new MiningDrone(auxReady, true));
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

}
