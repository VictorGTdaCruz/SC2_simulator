package victorcruz.sc2_simulator.Units.Army;

import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.UnitAttackInfo;

public class Hydralisk extends Unit {

    public Hydralisk(long orderedTime){
        name = "Hydralisk";
        life = 90;
        minCost = 100;
        gasCost = 50;
        supply = 2;
        supplyMax = 0;
        productionTime = 24000;
        speed = 3.15;
        speedOnCreep = speed * 1.3;
        armor = 0;
        armorScalability = 1;
        cargoSize = 2;
        sight = 9;
        attributes = new String[]{"Biological", "Light"};
        attacks[0] = new UnitAttackInfo();
        requisites = new String[]{"Larva", "Hydralisk Den"};

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }
}