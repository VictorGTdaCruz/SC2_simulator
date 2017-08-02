package victorcruz.sc2_simulator.Units;

import java.util.Comparator;

import victorcruz.sc2_simulator.Units.Unit;

public class UnitComparator implements Comparator<Unit>{

    @Override
    public int compare(Unit o1, Unit o2) {

        if (o1.ready > o2.ready) return 1;
        if (o1.ready < o2.ready) return -1;
        return 0;
    }
}
