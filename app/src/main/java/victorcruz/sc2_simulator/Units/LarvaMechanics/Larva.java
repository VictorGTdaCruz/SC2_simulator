package victorcruz.sc2_simulator.Units.LarvaMechanics;

public class Larva {

    private String name;
    public long ready;
    private long productionTime;

    public Larva(long orderedTime){
        name = "Larva";

        productionTime = 11000;

        this.ready =  orderedTime + productionTime;
        System.out.println(name + " ready:" + ready );
    }

    public long getReady(){
        return ready;
    }
}