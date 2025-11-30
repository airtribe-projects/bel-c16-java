package org.example.floorStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.parkingFloor.ParkingFloor;


public class RandomFloorStrategy implements FloorSelectionStrategy {
    @Override
    public List<ParkingFloor> selectFloorOrder(List<ParkingFloor> floors) {
        // Return floors in random order
        List<ParkingFloor> shuffledFloors = new ArrayList<>(floors);
        Collections.shuffle(shuffledFloors);
        return shuffledFloors;
    }
}
