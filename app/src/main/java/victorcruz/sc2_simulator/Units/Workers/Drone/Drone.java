package victorcruz.sc2_simulator.Units.Workers.Drone;

import victorcruz.sc2_simulator.Units.UnitAttackInfo;
import victorcruz.sc2_simulator.Units.Workers.Worker;

public class Drone extends Worker {

    public Drone (long orderedTime){
        name = "Drone";
        life = 40;
        minCost = 50;
        gasCost = 0;
        supply = 1;
        supplyMax = 0;
        productionTime = 12000;
        speed = 3.94;
        speedOnCreep = speed;
        armor = 0;
        armorScalability = 1;
        cargoSize = 1;
        sight = 8;
        attributes = new String[]{"Biological", "Light"};

        attacks[0] = new UnitAttackInfo();

        //aux variables to make everything work
        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }

    public long getReady(){
        return ready;
    }


}
