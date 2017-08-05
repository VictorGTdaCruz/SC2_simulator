package victorcruz.sc2_simulator.Buildings;


public class Building {

    public String name;
    public int life, shield, minCost, gasCost, supplyMax, energyInitial, energyMax, armor, shieldArmor, sight;
    public long productionTime, orderedTime, ready;
    public String[] requisites, attributes;


    public Building(long orderedTime, String name, int life, int shield, int minCost, int gasCost,
                    int supplyMax, int energyInitial, int energyMax, int armor, int shieldArmor,
                    int sight, long productionTime, String[] requisites, String[] attributes){

        this.name = name;
        this.life = life;
        this.shield = shield;
        this.minCost = minCost;
        this.gasCost = gasCost;
        this.supplyMax = supplyMax;
        this.energyInitial = energyInitial;
        this.energyMax = energyMax;
        this.armor = armor;
        this.shieldArmor = shieldArmor;
        this.sight = sight;
        this.productionTime = productionTime;
        this.requisites = requisites;
        this.attributes = attributes;


        this.orderedTime = orderedTime;
        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);

    }

    public Building(Building building){
        this.name = building.getName();
        this.life = building.getLife();
        this.shield = building.getShield();
        this.minCost = building.getMinCost();
        this.gasCost = building.getGasCost();
        this.supplyMax = building.getSupplyMax();
        this.energyInitial = building.getEnergyInitial();
        this.energyMax = building.getEnergyMax();
        this.armor = building.getArmor();
        this.shieldArmor = building.getShieldArmor();
        this.sight = building.getSight();
        this.productionTime = building.getProductionTime();
        this.requisites = building.getRequisites();
        this.attributes = building.getAttributes();

    }

    public void setOrderedTime(long orderedTime){
        this.orderedTime = orderedTime;
        ready = orderedTime + productionTime;

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

    public int getSupplyMax(){
        return supplyMax;
    }

    public long getProductionTime(){
        return productionTime;
    }

    public long getReady(){return ready;}

    // those above are only for the constructor
    public int getLife() {
        return life;
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

    public int getShieldArmor() {
        return shieldArmor;
    }

    public int getSight() {
        return sight;
    }

    public long getOrderedTime() {
        return orderedTime;
    }

    public String[] getRequisites() {
        return requisites;
    }

    public String[] getAttributes() {
        return attributes;
    }
}
