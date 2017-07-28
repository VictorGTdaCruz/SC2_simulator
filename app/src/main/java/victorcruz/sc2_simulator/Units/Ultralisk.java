package victorcruz.sc2_simulator.Units;

import victorcruz.sc2_simulator.Unit;
import victorcruz.sc2_simulator.UnitAttackInfo;

public class Ultralisk extends Unit {

    public Ultralisk(long orderedTime){
        name = "Ultralisk";
        life = 500;
        minCost = 300;
        gasCost = 200;
        supply = 6;
        supplyMax = 0;
        productionTime = 39000;
        speed = 4.13;
        speedOnCreep = speed * 1.3;
        armor = 2;
        armorScalability = 1;
        cargoSize = 8;
        sight = 9;
        attributes = new String[]{"Biological", "Armored", "Massive"};
        attacks[0] = new UnitAttackInfo();
        requisites = new String[]{"Larva", "Ultralisk Den"};

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }
}
