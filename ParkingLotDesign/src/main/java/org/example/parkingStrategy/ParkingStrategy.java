package org.example.parkingStrategy;

import org.example.ParkingLot;
import org.example.ParkingSpot;
import org.example.Vehicle;
import org.example.floorStrategy.FloorSelectionStrategy;


public interface ParkingStrategy {
    ParkingSpot findSpot(Vehicle vehicle, ParkingLot parkingLot, FloorSelectionStrategy floorStrategy);
}