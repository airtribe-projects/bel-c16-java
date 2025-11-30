package org.example.floorStrategy;

import java.util.List;
import org.example.parkingFloor.ParkingFloor;


public interface FloorSelectionStrategy {
    List<ParkingFloor> selectFloorOrder(List<ParkingFloor> floors);
}
