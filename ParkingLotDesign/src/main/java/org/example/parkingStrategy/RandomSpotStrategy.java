package org.example.parkingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.example.ParkingLot;
import org.example.floorStrategy.FloorSelectionStrategy;
import org.example.parkingFloor.ParkingFloor;
import org.example.ParkingSpot;
import org.example.Vehicle;


public class RandomSpotStrategy implements ParkingStrategy {
  private final Random random = new Random();

  @Override
  public ParkingSpot findSpot(Vehicle vehicle, ParkingLot parkingLot, FloorSelectionStrategy floorStrategy) {
    List<ParkingFloor> orderedFloors = floorStrategy.selectFloorOrder(parkingLot.getFloors());
    for (ParkingFloor floor : orderedFloors) {
      if (floor.isUnderMaintenance()) continue;
      List<ParkingSpot> availableSpots = new ArrayList<>();
      for (ParkingSpot spot : floor.getAllSpots()) {
        if (spot.canFitVehicle(vehicle)) {
          availableSpots.add(spot);
        }
      }
      if (!availableSpots.isEmpty()) {
        return availableSpots.get(random.nextInt(availableSpots.size()));
      }
    }
    return null;
  }
}