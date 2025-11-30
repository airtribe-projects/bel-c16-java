package org.example.floorStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.parkingFloor.ParkingFloor;


public class TopToBottomFloorStrategy implements FloorSelectionStrategy {
    @Override
    public List<ParkingFloor> selectFloorOrder(List<ParkingFloor> floors) {
        // Return floors in reverse order (top to bottom)
        List<ParkingFloor> reversedFloors = new ArrayList<>(floors);
        Collections.reverse(reversedFloors);
        return reversedFloors;
    }
}
