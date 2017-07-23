package victorcruz.sc2_simulator.Units.Army;

import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.UnitAttackInfo;

public class Mutalisk extends Unit {

    public Mutalisk(long orderedTime){
        name = "Mutalisk";
        life = 120;
        minCost = 100;
        gasCost = 100;
        supply = 2;
        supplyMax = 0;
        productionTime = 24000;
        speed = 5.6;
        armor = 0;
        armorScalability = 1;
        sight = 11;
        attributes = new String[]{"Biological", "Light"};
        attacks[0] = new UnitAttackInfo();
        requisites = new String[]{"Larva", "Spire"};

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }
}
