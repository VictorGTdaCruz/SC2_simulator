package victorcruz.sc2_simulator.Units;


public class Unit {

    public String name;
    public int life, lifeUp, shield, minCost, gasCost, supply, supplyMax, energyInitial, energyMax, armor, armorScalability,
                shieldArmor, shieldArmorScalability, cargoSize, sight;
    public long productionTime, orderedTime, ready;
    public double speed, speedOnCreep, speedWithSpeedUpgrade;
    public String[] requisites, attributes;
    public UnitAttackInfo[] attacks;
    public UnitAbility[] abilities;

    public Unit(long orderedTime, String name, int life, int shield, int minCost, int gasCost,
                int supply, int supplyMax, int energyInitial, int energyMax, int armor, int armorScalability,
                int shieldArmor, int shieldArmorScalability, int cargoSize, int sight, long productionTime,
                double speed, double speedOnCreep, String[] requisites, String[] attributes,
                UnitAttackInfo[] attacks, UnitAbility[] abilities){

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


        this.orderedTime = orderedTime;
        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);

    }

    public Unit(Unit unit){
        this.name = unit.getName();
        this.life = unit.getLife();
        this.shield = unit.getShield();
        this.minCost = unit.getMinCost();
        this.gasCost = unit.getGasCost();
        this.supply = unit.getSupply();
        this.supplyMax = unit.getSupplyMax();
        this.energyInitial = unit.getEnergyInitial();
        this.energyMax = unit.getEnergyMax();
        this.armor = unit.getArmor();
        this.armorScalability = unit.getArmorScalability();
        this.shieldArmor = unit.getShieldArmor();
        this.shieldArmorScalability = unit.getShieldArmorScalability();
        this.cargoSize = unit.getCargoSize();
        this.sight = unit.getSight();
        this.productionTime = unit.getProductionTime();
        this.speed = unit.getSpeed();
        this.speedOnCreep = unit.getSpeedOnCreep();
        this.requisites = unit.getRequisites();
        this.attributes = unit.getAttributes();
        this.attacks = unit.getAttacks();
        this.abilities = unit.getAbilities();

    }


    // units not implemented:
    // Zerg: cocoon, locust, broodling, infested terran, changeling
    // Terran:
    // Protoss:

    public void setOrderedTime(long orderedTime){
        this.orderedTime = orderedTime;
        ready = orderedTime + productionTime;
        System.out.println("------------------------------------------------------------" + ready);

    }

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


    // those above are used only in the second constructor
    public int getLife() {
        return life;
    }

    public int getLifeUp() {
        return lifeUp;
    }

    public int getShield() {
        return shield;
    }

    public int getEnergyInitial() {
        return energyInitial;
    }

    public int getEnergyMax() {
        return energyMax;
    }

    public int getArmor() {
        return armor;
    }

    public int getArmorScalability() {
        return armorScalability;
    }

    public int getShieldArmor() {
        return shieldArmor;
    }

    public int getShieldArmorScalability() {
        return shieldArmorScalability;
    }

    public int getCargoSize() {
        return cargoSize;
    }

    public int getSight() {
        return sight;
    }

    public long getOrderedTime() {
        return orderedTime;
    }

    public double getSpeed() {
        return speed;
    }

    public double getSpeedOnCreep() {
        return speedOnCreep;
    }

    public double getSpeedWithSpeedUpgrade() {
        return speedWithSpeedUpgrade;
    }

    public String[] getRequisites() {
        return requisites;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public UnitAttackInfo[] getAttacks() {
        return attacks;
    }

    public UnitAbility[] getAbilities() {
        return abilities;
    }
}
