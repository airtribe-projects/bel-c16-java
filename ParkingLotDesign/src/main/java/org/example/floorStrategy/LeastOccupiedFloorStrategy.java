package org.example.floorStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.example.ParkingSpot;
import org.example.parkingFloor.ParkingFloor;


public class LeastOccupiedFloorStrategy implements FloorSelectionStrategy {
    @Override
    public List<ParkingFloor> selectFloorOrder(List<ParkingFloor> floors) {
        // Sort floors by number of available spots (descending)
        List<ParkingFloor> sortedFloors = new ArrayList<>(floors);
        sortedFloors.sort(Comparator.comparingLong(this::getAvailableSpotCount).reversed());
        return sortedFloors;
    }

    private long getAvailableSpotCount(ParkingFloor floor) {
        if (floor.isUnderMaintenance()) {
            return 0;
        }
        return floor.getAllSpots().stream()
            .filter(spot -> !spot.isOccupied())
            .count();
    }
}
