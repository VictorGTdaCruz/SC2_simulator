package victorcruz.sc2_simulator;

import android.os.Handler;
import android.widget.TextView;

import java.util.PriorityQueue;

import victorcruz.sc2_simulator.Units.Workers.Drone.MiningDrone;


public class ResourcesHandler {

    private TextView  minTextView, gasTextView;

    private int minerals = 50, gas = 0;

    public static PriorityQueue<MiningDrone> miningPriorityQueue;
    private ResourceMiningComparator resourceMiningComparator = new ResourceMiningComparator();

    // handles the creation of unit in the right millisecond
    private Handler handler; // used on resourceMining

    public ResourcesHandler(TextView minTextView, TextView gasTextView){

        this.minTextView = minTextView;
        this.gasTextView = gasTextView;

        handler = new Handler();

        miningPriorityQueue = new PriorityQueue<>(100, resourceMiningComparator);

        miningPriorityQueue.add(new MiningDrone( -11950, true));
        miningPriorityQueue.add(new MiningDrone( -11700, true));
        miningPriorityQueue.add(new MiningDrone( -11700, true));
        miningPriorityQueue.add(new MiningDrone( -11400, true));
        miningPriorityQueue.add(new MiningDrone( -11400, true));
        miningPriorityQueue.add(new MiningDrone( -10800, true));
        miningPriorityQueue.add(new MiningDrone( -10300, true));
        miningPriorityQueue.add(new MiningDrone( -9900, true));
        miningPriorityQueue.add(new MiningDrone( -9500, true));
        miningPriorityQueue.add(new MiningDrone( -9100, true));
        miningPriorityQueue.add(new MiningDrone( -8900, true));
        miningPriorityQueue.add(new MiningDrone( -8800, true));

    }

    public void resourceMining(final long currentTime){

        if (miningPriorityQueue.peek() != null  && 1000 >= miningPriorityQueue.peek().getReady() - currentTime) {

            //System.out.print("--- DRONE MINED AT ");
            //System.out.print(currentTime + (miningPriorityQueue.peek().getReady() - currentTime));
            //System.out.println(" --- CurrentTime at Mining:" + currentTime);
            miningPriorityQueue.add(new MiningDrone(miningPriorityQueue.peek().getReady() + miningPriorityQueue.peek().calcDistance(), false));

            if (!(miningPriorityQueue.peek().getIsFirstMineAction())) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        increaseMin(5);
                    }
                }, miningPriorityQueue.peek().getReady() - currentTime);
            }
            miningPriorityQueue.remove();
            resourceMining(currentTime);
        }
    }

    public int getMinerals(){
        return  minerals;
    }

    public int getGas(){
        return gas;
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
/*
    public void resetResources(){
        minerals = 50;
        minTextView.setText(Integer.toString(minerals));
        gas = 0;
        gasTextView.setText(Integer.toString(gas));
    }
*/

}
