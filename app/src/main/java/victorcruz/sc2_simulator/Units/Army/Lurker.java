package victorcruz.sc2_simulator.Units.Army;

import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.UnitAttackInfo;

public class Lurker extends Unit {

    public Lurker(long orderedTime){
        name = "Lurker";
        life = 200;
        minCost = 50;
        gasCost = 100;
        supply = 1;
        supplyMax = 0;
        productionTime = 18000;
        speed = 4.13;
        speedOnCreep = speed * 1.3;
        armor = 1;
        armorScalability = 1;
        cargoSize = 4;
        sight = 9;
        attributes = new String[]{"Biological", "Armored"};
        attacks[0] = new UnitAttackInfo();
        requisites = new String[]{"Hydralisk", "Lurker Den"};

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }
}