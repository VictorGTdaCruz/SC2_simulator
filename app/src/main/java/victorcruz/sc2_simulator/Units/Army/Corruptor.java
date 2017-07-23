package victorcruz.sc2_simulator.Units.Army;

import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.UnitAbility;
import victorcruz.sc2_simulator.Units.UnitAttackInfo;

public class Corruptor extends Unit {

    public Corruptor(long orderedTime){
        name = "Corruptor";
        life = 200;
        minCost = 150;
        gasCost = 100;
        supply = 2;
        supplyMax = 0;
        productionTime = 29000;
        speed = 4.725;
        armor = 2;
        armorScalability = 1;
        sight = 10;
        attributes = new String[]{"Biological", "Armored"};
        attacks[0] = new UnitAttackInfo();
        requisites = new String[]{"Larva", "Spire"};
        abilities[0] = new UnitAbility();

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }
}