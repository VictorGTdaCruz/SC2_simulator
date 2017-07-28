package victorcruz.sc2_simulator.Units;

import java.util.Random;

import victorcruz.sc2_simulator.Unit;

public class MiningDrone extends Unit {

    public boolean isFirstMineAction, isMiningHandlered;

    private Random randomGenerator;

    public MiningDrone(long orderedTime, boolean isFirstMineAction){
        super();
        //variables of a drone
        name = "Drone";
        minCost = 50;
        gasCost = 0;
        supply = 1;
        supplyMax = 0;
        productionTime = 12000;
        speed = 3.94;

        //aux variable to make everything work
        ready = orderedTime + productionTime;

        // variables used on mining logic
        this.isFirstMineAction = isFirstMineAction;
        isMiningHandlered = false;

        randomGenerator = new Random(); // used on calcDistance
    }

    public boolean getIsFirstMineAction(){
        return isFirstMineAction;
    }

    public long getReady(){
        return ready;
    }

    public void setIsMiningHandlered(boolean isMiningHandlered){this.isMiningHandlered = isMiningHandlered;}

    public boolean getIsMiningHandlered(){return isMiningHandlered;}

    public long calcDistance(){

        long random = randomGenerator.nextInt(101);

        // the longs returned are added to the production time of the drone
        // in other words, the result should be the duration of the cycle of mining 5 minerals
        // Ex: 12000 + (-7000) = 5000 for close mineral patches
        if (random >= 35) return -7000;
        else if (random <= 80 ) return -6300;
        else return -6100;

        /*if (random >= 37.5) return -7100;
        else if (random <= 80 ) return -6200;
        else return -6200;
*/
    }

}
