package victorcruz.sc2_simulator.Units.Army;


import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.UnitAttackInfo;

public class Larva extends Unit {

    public Larva(long orderedTime){
        name = "Larva";
        life = 25;
        minCost = 0;
        gasCost = 0;
        supply = 0;
        supplyMax = 0;
        productionTime = 11000;
        speed = 0;
        armor = 10;
        armorScalability = 1;
        cargoSize = 1;
        sight = 5;
        attributes = new String[]{"Biological", "Light"};

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }
}