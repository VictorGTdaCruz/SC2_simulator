package victorcruz.sc2_simulator.Units.Army;

import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.UnitAbility;
import victorcruz.sc2_simulator.Units.UnitAttackInfo;

public class Ravager extends Unit{

    public Ravager(long orderedTime){
        name = "Ravager";
        life = 120;
        minCost = 25;
        gasCost = 75;
        supply = 1;
        supplyMax = 0;
        productionTime = 9000;
        speed = 3.85;
        speedOnCreep = 5;
        armor = 1;
        armorScalability = 1;
        cargoSize = 4;
        sight = 9;
        attributes = new String[]{"Biological"};
        attacks[0] = new UnitAttackInfo();
        requisites = new String[]{"Roach", "Roach Warren"};
        abilities = new UnitAbility[1];

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }
}