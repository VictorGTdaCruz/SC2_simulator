package victorcruz.sc2_simulator;

import android.os.Handler;
import android.widget.TextView;

import java.util.PriorityQueue;

import victorcruz.sc2_simulator.Units.MiningDrone;


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

        minPriorityQueue.add(new MiningDrone( -11950, true));
        minPriorityQueue.add(new MiningDrone( -11700, true));
        minPriorityQueue.add(new MiningDrone( -11700, true));
        minPriorityQueue.add(new MiningDrone( -11400, true));
        minPriorityQueue.add(new MiningDrone( -11400, true));
        minPriorityQueue.add(new MiningDrone( -10800, true));
        minPriorityQueue.add(new MiningDrone( -10300, true));
        minPriorityQueue.add(new MiningDrone( -9900, true));
        minPriorityQueue.add(new MiningDrone( -9500, true));
        minPriorityQueue.add(new MiningDrone( -9100, true));
        minPriorityQueue.add(new MiningDrone( -8900, true));
        minPriorityQueue.add(new MiningDrone( -8800, true));

    }

    public void resourceMining(final long currentTime){

        if (minPriorityQueue.peek() != null  && 1000 >= minPriorityQueue.peek().getReady() - currentTime) {

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
