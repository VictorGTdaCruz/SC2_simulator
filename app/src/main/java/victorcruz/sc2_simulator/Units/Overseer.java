package victorcruz.sc2_simulator.Units;

import victorcruz.sc2_simulator.Unit;
import victorcruz.sc2_simulator.UnitAbility;

public class Overseer extends Unit {

    public Overseer(long orderedTime){
        name = "Overseer";
        life = 200;
        minCost = 50;
        gasCost = 50;
        supply = 0;
        supplyMax = 0;
        energyInitial = 50;
        energyMax = 200;
        productionTime = 12000;
        speed = 3.85;
        armor = 1;
        armorScalability = 1;
        sight = 11;
        attributes = new String[]{"Biological", "Armored", "Detector"};
        requisites = new String[]{"Overlord", "Lair"};
        abilities = new UnitAbility[2];

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }
}
