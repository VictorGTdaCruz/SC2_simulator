package victorcruz.sc2_simulator.Units;


public class Unit {

    public String name;
    public int life, lifeUp, shield, minCost, gasCost, supply, supplyMax, energyInitial, energyMax, armor, armorScalability,
                shieldArmor, shieldArmorScalability, cargoSize, sight;
    public long productionTime, ready;
    public double speed, speedOnCreep, speedWithSpeedUpgrade;
    public String[] requisites, attributes;
    public UnitAttackInfo[] attacks;
    public UnitAbility[] abilities;

    public Unit(String name, int life, int shield, int minCost, int gasCost, int supply, int supplyMax, int energyInitial, int energyMax, int armor, int armorScalability,
               int shieldArmor, int shieldArmorScalability, int cargoSize, int sight, long productionTime, double speed, double speedOnCreep,
                String[] requisites, String[] attributes, UnitAttackInfo[] attacks, UnitAbility[] abilities){

        this.name = name;
        this.life = life;
        this.shield = shield;
        this.minCost = minCost;
        this.gasCost = gasCost;
        this.supply = supply;
        this.supplyMax = supplyMax;
        this.energyInitial = energyInitial;
        this.energyMax = energyMax;
        this.armor = armor;
        this.armorScalability = armorScalability;
        this.shieldArmor = shieldArmor;
        this.shieldArmorScalability = shieldArmorScalability;
        this.cargoSize = cargoSize;
        this.sight = sight;
        this.productionTime = productionTime;
        this.speed = speed;
        this.speedOnCreep = speedOnCreep;
        this.requisites = requisites;
        this.attributes = attributes;
        this.attacks = attacks;
        this.abilities = abilities;
    }

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
