package model;

import java.awt.Color;

import view.ShowBobby;

public class Bobbies {
    public TicketBoardBobbie ticketBoard1 = new TicketBoardBobbie(360);
    public TicketBoardBobbie ticketBoard2 = new TicketBoardBobbie(500);

    private ShowBobby bobby1 = new ShowBobby(Color.CYAN);
    private ShowBobby bobby2 = new ShowBobby(Color.BLUE);

    public Bobbies() {

    }

    public void moveBobby(int bobby, int position) {
        if (bobby == 1) {
            bobby1.movePiece(position);
        } else {
            bobby2.movePiece(position);
        }
    }

    public ShowBobby getBobby1() {
        return this.bobby1;
    }

    public ShowBobby getBobby2() {
        return this.bobby2;
    }

}