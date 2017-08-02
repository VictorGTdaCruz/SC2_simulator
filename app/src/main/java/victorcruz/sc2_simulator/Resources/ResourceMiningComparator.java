package victorcruz.sc2_simulator.Resources;

import java.util.Comparator;

import victorcruz.sc2_simulator.Resources.MiningDrone;

public class ResourceMiningComparator implements Comparator<MiningDrone>{


    @Override
    public int compare(MiningDrone d1, MiningDrone d2) {

        if (d1.getReady() > d2.getReady()) return 1;
        if (d1.getReady() < d2.getReady()) return -1;
        return 0;
    }
}
