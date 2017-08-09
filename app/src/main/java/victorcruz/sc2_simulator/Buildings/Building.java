package victorcruz.sc2_simulator.Buildings;


public class Building {

    public String name;
    public int life, shield, minCost, gasCost, supplyMax, energyInitial, energyMax, armor, shieldArmor;
    public long productionTime, orderedTime, ready;
    public String[] requisites, attributes;


    // Normal building
    public Building(long orderedTime, String name, int life, int minCost, int gasCost,
                    int supplyMax, long productionTime, int armor, String[] requisites,
                    String[] attributes){

        this.name = name;
        this.life = life;
        this.shield = -1;
        this.minCost = minCost;
        this.gasCost = gasCost;
        this.supplyMax = supplyMax;
        this.energyInitial = -1;
        this.energyMax = -1;
        this.armor = armor;
        this.shieldArmor = -1;
        this.productionTime = productionTime;
        this.requisites = requisites;
        this.attributes = attributes;


        this.orderedTime = orderedTime;
        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);

    }

    // Protoss building
    public Building(long orderedTime, String name, int life, int shield, int minCost, int gasCost,
                    int supplyMax, long productionTime, int armor, int shieldArmor,
                    String[] requisites, String[] attributes){

        this.name = name;
        this.life = life;
        this.shield = shield;
        this.minCost = minCost;
        this.gasCost = gasCost;
        this.supplyMax = supplyMax;
        this.energyInitial = -1;
        this.energyMax = -1;
        this.armor = armor;
        this.shieldArmor = shieldArmor;
        this.productionTime = productionTime;
        this.requisites = requisites;
        this.attributes = attributes;


        this.orderedTime = orderedTime;
        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);

    }

    // Caster building
    public Building(long orderedTime, String name,int life, int minCost, int gasCost, int supplyMax,
                    int energyInitial, int energyMax, long productionTime, int armor,
                    String[] requisites, String[] attributes){

        this.name = name;
        this.life = life;
        this.shield = -1;
        this.minCost = minCost;
        this.gasCost = gasCost;
        this.supplyMax = supplyMax;
        this.energyInitial = energyInitial;
        this.energyMax = energyMax;
        this.armor = armor;
        this.shieldArmor = -1;
        this.productionTime = productionTime;
        this.requisites = requisites;
        this.attributes = attributes;


        this.orderedTime = orderedTime;
        ready = orderedTime + productionTime;
        System.out.println(name + " ordered:" + ready + " " + orderedTime);

    }


    // Protoss caster building
    public Building(long orderedTime, String name, int life, int shield, int minCost, int gasCost,
                    int supplyMax, int energyInitial, int energyMax, long productionTime,  int armor,
                    int shieldArmor, String[] requisites, String[] attributes){

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
