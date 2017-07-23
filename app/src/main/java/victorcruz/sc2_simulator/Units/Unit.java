package victorcruz.sc2_simulator.Units;


public abstract class Unit {

    public String name;
    public int life, lifeUp, shield, minCost, gasCost, supply, supplyMax, energyInitial, energyMax, armor, armorScalability,
                shieldArmor, ShieldArmorScalability, cargoSize, sight;
    public long productionTime, ready;
    public double speed, speedOnCreep, speedWithSpeedUpgrade;
    public String[] requisites, attributes;
    public UnitAttackInfo[] attacks;
    public UnitAbility[] abilities;

    // requisites, attack, attributes, armor, attack and armor after upgrades, cargo size, abilities

    // units not implemented: Zerg(cocoon, locust, broodling, infested terran, changeling), Terran(), Protoss()


    public String getName(){
        return name;
    }

    public int getMinCost (){
        return minCost;
    }

    public int getGasCost(){
        return gasCost;
    }

    public int getSupply(){
        return supply;
    }

    public int getSupplyMax(){
        return supplyMax;
    }

    public long getProductionTime(){
        return productionTime;
    }

    public long getReady(){return ready;}

}
