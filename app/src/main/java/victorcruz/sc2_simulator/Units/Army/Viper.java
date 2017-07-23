package victorcruz.sc2_simulator.Units.Army;

import victorcruz.sc2_simulator.Units.Unit;
import victorcruz.sc2_simulator.Units.UnitAbility;

public class Viper extends Unit {

    public Viper(long orderedTime){
        name = "Viper";
        life = 150;
        minCost = 100;
        gasCost = 200;
        supply = 3;
        supplyMax = 0;
        energyInitial = 50;
        energyMax = 200;
        productionTime = 29000;
        speed = 4.13;
        armor = 1;
        armorScalability = 1;
        sight = 11;
        attributes = new String[]{"Biological", "Armored", "Psionic"};
        requisites = new String[]{"Larva", "Hive"};
        abilities = new UnitAbility[3];

        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }
}