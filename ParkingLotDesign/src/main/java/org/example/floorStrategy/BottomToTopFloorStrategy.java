package org.example.floorStrategy;

import java.util.ArrayList;
import java.util.List;
import org.example.parkingFloor.ParkingFloor;


public class BottomToTopFloorStrategy implements FloorSelectionStrategy {
    @Override
    public List<ParkingFloor> selectFloorOrder(List<ParkingFloor> floors) {
        // Return floors in their original order (bottom to top)
        return new ArrayList<>(floors);
    }
}
