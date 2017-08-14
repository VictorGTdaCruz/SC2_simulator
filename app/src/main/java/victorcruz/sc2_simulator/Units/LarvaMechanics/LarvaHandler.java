package victorcruz.sc2_simulator.Units.LarvaMechanics;

import android.os.Handler;
import android.widget.TextView;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

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


public class LarvaHandler {

    private TextView larvaTextView;

    private Handler larvaHandler = new Handler(); // used on growLarva

    // larva mechanic variables
    private LinkedList<PriorityQueue<Larva>> larvaSystem;
    private Comparator<Larva> larvaComparator = new LarvaComparator();
    private int[] larvaCount;
    private boolean[] isProducingLarva;

    //aux variables
    private boolean consumedLarva = false; // used on UnitHandler.makeUnit method


    public LarvaHandler(TextView larvaTextView){

        this.larvaTextView = larvaTextView;

        // Linked list to manage all PriorityQueues
        larvaSystem = new LinkedList<>();

        // first 2 PriorityQueues representing the first hatchery
        // the first represents the available larva
        // the second represents the larva production cycle
        larvaSystem.add(new PriorityQueue<Larva>(19, larvaComparator));
        larvaSystem.add(new PriorityQueue<Larva>(4, larvaComparator));

        // it starts with 3 larva
        larvaSystem.get(0).add(new Larva(-11000));
        larvaSystem.get(0).add(new Larva(-11000));
        larvaSystem.get(0).add(new Larva(-11000));

        larvaCount = new int[2];
        larvaCount[0] = 3;

        isProducingLarva = new boolean[2];
        isProducingLarva[1] = false;

    }

    public boolean useLarva(long currentTime){

        // Chooses a larva to be used/destroyed and then calls scheduleLarva
        System.out.println("First current time of all" + currentTime);
        int larvaSystemIndex = 0;
        for (;larvaSystemIndex < larvaSystem.size(); larvaSystemIndex = larvaSystemIndex + 2){

            if (larvaSystem.get(larvaSystemIndex).peek() != null){
                larvaSystem.get(larvaSystemIndex).remove();
                larvaCount[larvaSystemIndex]--;
                larvaTextView.setText(Integer.toString(larvaCount[larvaSystemIndex]));
                //System.out.println(larvaSystem.get(larvaSystemIndex).size());
                scheduleLarva(currentTime, larvaSystemIndex);
                return true;

            }
        }

        return false;

    }


    public void scheduleLarva(final long currentTime, final int larvaSystemIndex){

        // Creates a larva in the "hatchery is producing larva" PriorityQueue
        if (larvaSystem.get(larvaSystemIndex).size() < 3 && !(isProducingLarva[larvaSystemIndex + 1])) {
            //System.out.println("Larva number: " + larvaSystem.get(larvaSystemIndex).size());
            isProducingLarva[larvaSystemIndex + 1] = true;
            System.out.println("Producing larva..." + currentTime);
            larvaSystem.get(larvaSystemIndex + 1).add(new Larva(currentTime));
        }

    }

    public void growLarva(final long currentTime){

        // Checks if its the right second to remove the larva scheduled and add it to the
        // available larva PriorityQueue
        int larvaSystemIndex = 0;
        for (; larvaSystemIndex < larvaSystem.size(); larvaSystemIndex = larvaSystemIndex + 2){
            if (larvaSystem.get(larvaSystemIndex + 1).peek() != null &&
                    150 > larvaSystem.get(larvaSystemIndex + 1).peek().getReady() - currentTime){

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

    public boolean getConsumedLarva(){
        return consumedLarva;
    }

    public void setConsumedLarva(boolean value){
        consumedLarva = value;
    }

}
