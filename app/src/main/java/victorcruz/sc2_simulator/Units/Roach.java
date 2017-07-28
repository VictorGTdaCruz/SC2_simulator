package victorcruz.sc2_simulator.Units;

import victorcruz.sc2_simulator.Unit;
import victorcruz.sc2_simulator.UnitAttackInfo;

public class Roach extends Unit {

    public Roach (long orderedTime){
        name = "Roach";
        life = 145;
        minCost = 75;
        gasCost = 25;
        supply = 2;
        supplyMax = 0;
        productionTime = 19000;
        speed = 3.15;
        speedOnCreep = 4.09;
        speedWithSpeedUpgrade = speed + (speed / 3);
        armor = 1;
        armorScalability = 1;
        cargoSize = 2;
        sight = 9;
        attributes = new String[]{"Biological", "Armored"};
        attacks[0] = new UnitAttackInfo();
        requisites = new String[]{"Larva", "Roach Warren"};

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);

    }

}
