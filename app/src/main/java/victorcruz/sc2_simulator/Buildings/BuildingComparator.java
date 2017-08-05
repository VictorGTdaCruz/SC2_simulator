package victorcruz.sc2_simulator.Buildings;

import java.util.Comparator;


public class BuildingComparator implements Comparator<Building> {

    @Override
    public int compare(Building o1, Building o2) {

        if (o1.ready > o2.ready) return 1;
        if (o1.ready < o2.ready) return -1;
        return 0;
    }
}
