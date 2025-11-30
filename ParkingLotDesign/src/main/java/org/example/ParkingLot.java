package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.displayPanel.DisplayPanel;
import org.example.displayPanel.EntryDisplayPanel;
import org.example.displayPanel.ExitDisplayPanel;
import org.example.floorStrategy.FloorSelectionStrategy;
import org.example.panels.EntryPanel;
import org.example.panels.ExitPanel;
import org.example.parkingFloor.ParkingFloor;
import org.example.parkingStrategy.ParkingStrategy;
import org.example.parkingticket.ParkingTicket;
import org.example.payment.CardPaymentProcessor;
import org.example.payment.PaymentProcessor;


public class ParkingLot {
    private final List<ParkingFloor> floors;
    private final EntryPanel entryPanel;
    private ExitPanel exitPanel;
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();

    public ParkingLot(ParkingStrategy strategy, FloorSelectionStrategy floorStrategy, DisplayPanel entryDisplayPanel, DisplayPanel exitDisplayPanel) {
        this.floors = new ArrayList<>();
        this.entryPanel = new EntryPanel(strategy, floorStrategy, entryDisplayPanel);
        this.exitPanel = new ExitPanel(new CardPaymentProcessor(), exitDisplayPanel);
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public EntryPanel getEntryPanel() {
        return entryPanel;
    }

    public ExitPanel getExitPanel() {
        return exitPanel;
    }

    public void changeStrategy(ParkingStrategy strategy) {
        entryPanel.changeStrategy(strategy);
    }

    public void changeFloorStrategy(FloorSelectionStrategy floorStrategy) {
        entryPanel.changeFloorStrategy(floorStrategy);
    }

    public void issueTicket(ParkingTicket ticket) {
        activeTickets.put(ticket.getTicketId(), ticket);
    }

    public ParkingTicket getTicket(String ticketId) {
        return activeTickets.get(ticketId);
    }

    public void setExitPanel(ExitPanel exitPanel) {
        this.exitPanel = exitPanel;
    }


    public void isParkingLotFull() {
        for (ParkingFloor floor : floors) {
            if (!floor.isFull()) {
                System.out.println("Parking lot is not full.");
                return;
            }
        }
        System.out.println("Parking lot is full.");
    }

}
