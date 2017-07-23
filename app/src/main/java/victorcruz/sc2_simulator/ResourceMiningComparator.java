package victorcruz.sc2_simulator;

import java.util.Comparator;

import victorcruz.sc2_simulator.Units.Workers.Drone.Drone;
import victorcruz.sc2_simulator.Units.Workers.Drone.MiningDrone;

public class ResourceMiningComparator implements Comparator<MiningDrone>{


    @Override
    public int compare(MiningDrone d1, MiningDrone d2) {

        if (d1.getReady() > d2.getReady()) return 1;
        if (d1.getReady() < d2.getReady()) return -1;
        return 0;
    }
}
