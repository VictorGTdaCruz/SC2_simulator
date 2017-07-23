package victorcruz.sc2_simulator.Units.Army;

import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.UnitAttackInfo;

public class Overlord extends Unit {

    public Overlord(long orderedTime){
        name = "Overlord";
        life = 200;
        minCost = 100;
        gasCost = 0;
        supply = 0;
        supplyMax = 8;
        productionTime = 18000;
        speed = 0.82;
        armor = 0;
        armorScalability = 1;
        sight = 11;
        attributes = new String[]{"Biological", "Armored"};
        requisites = new String[]{"Larva"};

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }

}
