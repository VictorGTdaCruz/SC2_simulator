package victorcruz.sc2_simulator.Units.Army;

import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.UnitAbility;
import victorcruz.sc2_simulator.Units.UnitAttackInfo;

public class Queen extends Unit{

    public Queen(long orderedTime){
        name = "Queen";
        life = 175;
        minCost = 150;
        gasCost = 0;
        supply = 2;
        supplyMax = 0;
        energyInitial = 25;
        energyMax = 200;
        productionTime = 36000;
        speed = 1.31;
        speedOnCreep = 3.5;
        armor = 1;
        armorScalability = 1;
        cargoSize = 2;
        sight = 9;
        attributes = new String[]{"Biological", "Psionic"};
        attacks[0] = new UnitAttackInfo();
        attacks[1] = new UnitAttackInfo();
        requisites[0] = new String("Spawning Pool");
        abilities = new UnitAbility[3];

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);

    }
}
