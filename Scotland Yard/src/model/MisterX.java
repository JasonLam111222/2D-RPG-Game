package model;

import java.awt.Color;

import javax.swing.JPanel;

import view.ShowMisterX;

public class MisterX {

    public TicketBoardMrX board = new TicketBoardMrX();
    private ShowMisterX x = new ShowMisterX(Color.GRAY);

    private int taxiTickets = 0;
    private int busTickets = 0;
    private int undergroundTickets = 0;
    private int blackTickets = 5;
    private int doubleMoveTickets = 2;

    public MisterX() {

    }

    public ShowMisterX getX() {
        return this.x;
    }

    public void moveX(int position, char transportationUsed) {
        x.movePiece(position);
        deductTransportation(transportationUsed);
        switch (transportationUsed) {
            case 'T':
                board.setRemainingCardsLabel(this.taxiTickets, transportationUsed);
                break;
            case 'B':
                board.setRemainingCardsLabel(this.busTickets, transportationUsed);
                break;
            case 'U':
                board.setRemainingCardsLabel(this.undergroundTickets, transportationUsed);
                break;
            case 'H':
                board.setRemainingCardsLabel(this.blackTickets, transportationUsed);
                break;
            case 'D':
                board.setRemainingCardsLabel(this.doubleMoveTickets, transportationUsed);
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
            case 'H':
                this.blackTickets--;
                break;
            case 'D':
                this.doubleMoveTickets--;
                break;
        }
    }

    public void increaseTransportation(char transportationUsed) {
        switch (transportationUsed) {
            case 'T':
                this.taxiTickets++;
                board.setRemainingCardsLabel(this.taxiTickets, 'T');
                break;
            case 'B':
                this.busTickets++;
                board.setRemainingCardsLabel(this.busTickets, 'B');
                break;
            case 'U':
                this.undergroundTickets++;
                board.setRemainingCardsLabel(this.undergroundTickets, 'U');
                break;
        }

    }

    public int getTaxiTickets() {
        return this.taxiTickets;
    }

    public void setTaxiTickets(int taxiTickets) {
        this.taxiTickets = taxiTickets;
    }

    public int getBusTickets() {
        return this.busTickets;
    }

    public void setBusTickets(int busTickets) {
        this.busTickets = busTickets;
    }

    public int getUndergroundTickets() {
        return this.undergroundTickets;
    }

    public void setUndergroundTickets(int undergroundTickets) {
        this.undergroundTickets = undergroundTickets;
    }

    public int getBlackTickets() {
        return this.blackTickets;
    }

    public void setBlackTickets(int blackTickets) {
        this.blackTickets = blackTickets;
    }

    public int getDoubleMoveTickets() {
        return this.doubleMoveTickets;
    }

    public void setDoubleMoveTickets(int doubleMoveTickets) {
        this.doubleMoveTickets = doubleMoveTickets;
    }
}
