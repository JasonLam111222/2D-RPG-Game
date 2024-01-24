package model;

import java.awt.Color;

import javax.swing.JOptionPane;

import view.ShowDetective;

public class Detectives {
    public TicketBoardDetective ticketBoard1 = new TicketBoardDetective(80, Color.YELLOW);
    public TicketBoardDetective ticketBoard2 = new TicketBoardDetective(220, Color.MAGENTA);

    private ShowDetective d1 = new ShowDetective(Color.YELLOW);
    private ShowDetective d2 = new ShowDetective(Color.MAGENTA);

    private int taxiTickets = 20;
    private int busTickets = 16;
    private int undergroundTickets = 8;

    public Detectives() {

    }

    public void moveDetective(int detective, int position, char transportationUsed) {
        System.out.println("code working");
        if (detective == 1) {
            d1.movePiece(position);
        } else {
            d2.movePiece(position);
        }

        deductTransportation(transportationUsed);
        switch (transportationUsed) {
            case 'T':
                ticketBoard1.setRemainingCardsLabel(this.taxiTickets, transportationUsed);
                ticketBoard2.setRemainingCardsLabel(this.taxiTickets, transportationUsed);
                break;
            case 'B':
                ticketBoard1.setRemainingCardsLabel(this.busTickets, transportationUsed);
                ticketBoard2.setRemainingCardsLabel(this.busTickets, transportationUsed);
                break;
            case 'U':
                ticketBoard1.setRemainingCardsLabel(this.undergroundTickets, transportationUsed);
                ticketBoard2.setRemainingCardsLabel(this.undergroundTickets, transportationUsed);
                break;
        }

    }

    private void deductTransportation(char transportationUsed) {
        switch (transportationUsed) {
            case 'T':
                this.taxiTickets--;
                break;
            case 'B':
                this.busTickets--;
                break;
            case 'U':
                this.undergroundTickets--;
                break;
        }
    }

    public ShowDetective getD1() {
        return this.d1;
    }

    public ShowDetective getD2() {
        return this.d2;
    }

    public int getTaxiTickets() {
        return this.taxiTickets;
    }

    public int getBusTickets() {
        return this.busTickets;
    }

    public int getUndergroundTickets() {
        return this.undergroundTickets;
    }

}