package org.example.panels;

import org.example.ParkingLot;
import org.example.ParkingSpot;
import org.example.Vehicle;
import org.example.displayPanel.DisplayPanel;
import org.example.displayPanel.EntryDisplayPanel;
import org.example.floorStrategy.FloorSelectionStrategy;
import org.example.parkingFloor.ParkingFloor;
import org.example.parkingStrategy.ParkingStrategy;
import org.example.parkingticket.ParkingTicket;


public class EntryPanel {
    private ParkingStrategy strategy;
    private FloorSelectionStrategy floorStrategy;
    private final DisplayPanel displayPanel;
    private int ticketCounter = 0;

    public EntryPanel(ParkingStrategy strategy, FloorSelectionStrategy floorStrategy, DisplayPanel displayPanel) {
        this.strategy = strategy;
        this.floorStrategy = floorStrategy;
        this.displayPanel = displayPanel;
    }

    public void changeStrategy(ParkingStrategy strategy) {
        this.strategy = strategy;
    }

    public void changeFloorStrategy(FloorSelectionStrategy floorStrategy) {
        this.floorStrategy = floorStrategy;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle, ParkingLot parkingLot) {
        ParkingFloor floor = floorStrategy.selectFloorOrder(parkingLot.getFloors()).get(0);
        ParkingSpot spot = strategy.findSpot(vehicle, parkingLot, floorStrategy);
        ParkingTicket ticket = null;
        if (spot != null) {
            spot.parkVehicle(vehicle);
            ticket = new ParkingTicket("TICKET-" + (++ticketCounter), vehicle, spot);
            parkingLot.issueTicket(ticket);
        }
        if (displayPanel instanceof EntryDisplayPanel) {
            ((EntryDisplayPanel) displayPanel).displayTicketIssued(ticket);
        }
        return ticket;
    }
}