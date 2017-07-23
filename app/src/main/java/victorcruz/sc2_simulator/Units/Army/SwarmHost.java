package victorcruz.sc2_simulator.Units.Army;

import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.UnitAbility;


public class SwarmHost extends Unit {

    public SwarmHost(long orderedTime){
        name = "Swarm Host";
        life = 160;
        minCost = 100;
        gasCost = 75;
        supply = 3;
        supplyMax = 0;
        productionTime = 29000;
        speed = 4.13;
        armor = 1;
        armorScalability = 1;
        cargoSize = 4;
        sight = 10;
        attributes = new String[]{"Biological", "Armored"};
        requisites = new String[]{"Larva", "Infestation Pit"};
        abilities = new UnitAbility[1];

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }
}