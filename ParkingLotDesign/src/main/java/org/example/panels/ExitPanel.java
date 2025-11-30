package org.example.panels;

import org.example.ParkingSpot;
import org.example.Vehicle;
import org.example.displayPanel.DisplayPanel;
import org.example.displayPanel.ExitDisplayPanel;
import org.example.parkingticket.ParkingTicket;
import org.example.payment.PaymentProcessor;


public class ExitPanel {
    private final DisplayPanel displayPanel;
    private final PaymentProcessor paymentProcessor;

    public ExitPanel(PaymentProcessor paymentProcessor, DisplayPanel displayPanel) {
        this.displayPanel = displayPanel;
        this.paymentProcessor = paymentProcessor;
    }

    public void unparkVehicle(ParkingTicket ticket) {
        if (ticket == null || ticket.getSpot() == null) return;

        long duration = (System.currentTimeMillis() - ticket.getEntryTime()) / 1000; // seconds
        double cost = 50.0 + duration * 0.1; // base rate
        ticket.getSpot().removeVehicle();

        paymentProcessor.processPayment(cost);
        if (displayPanel instanceof ExitDisplayPanel) {
            ((ExitDisplayPanel) displayPanel).displayCost(ticket.getVehicle(), cost);
        }
    }
}