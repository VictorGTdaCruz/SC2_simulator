package victorcruz.sc2_simulator.Units;


import victorcruz.sc2_simulator.Unit;

public class Larva extends Unit {

    public Larva(long ready){
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

        this.ready = ready; // orderedTime + productionTime;
        System.out.println(name + " ready:" + ready );
    }
}