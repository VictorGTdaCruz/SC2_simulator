package victorcruz.sc2_simulator.Units;

import victorcruz.sc2_simulator.Unit;
import victorcruz.sc2_simulator.UnitAbility;
import victorcruz.sc2_simulator.UnitAttackInfo;

public class Drone extends Unit {
    public Drone(long orderedTime, String name, int life, int shield, int minCost, int gasCost, int supply, int supplyMax, int energyInitial, int energyMax, int armor, int armorScalability, int shieldArmor, int shieldArmorScalability, int cargoSize, int sight, long productionTime, double speed, double speedOnCreep, String[] requisites, String[] attributes, UnitAttackInfo[] attacks, UnitAbility[] abilities) {
        super(orderedTime, name, life, shield, minCost, gasCost, supply, supplyMax, energyInitial, energyMax, armor, armorScalability, shieldArmor, shieldArmorScalability, cargoSize, sight, productionTime, speed, speedOnCreep, requisites, attributes, attacks, abilities);
    }
/*
    public Drone (long orderedTime){
        name = "Drone";
        life = 40;
        minCost = 50;
        gasCost = 0;
        supply = 1;
        supplyMax = 0;
        productionTime = 12000;
        speed = 3.94;
        speedOnCreep = speed;
        armor = 0;
        armorScalability = 1;
        cargoSize = 1;
        sight = 8;
        attributes = new String[]{"Biological", "Light"};

        attacks[0] = new UnitAttackInfo();

        //aux variables to make everything work
        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);
    }

    public long getReady(){
        return ready;
    }
*/

}
