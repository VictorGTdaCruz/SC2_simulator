package victorcruz.sc2_simulator.Units.LarvaMechanics;

import java.util.Comparator;

public class LarvaComparator implements Comparator<Larva>{

    @Override
    public int compare(Larva o1, Larva o2) {

        if (o1.ready > o2.ready) return 1;
        if (o1.ready < o2.ready) return -1;
        return 0;
    }
}