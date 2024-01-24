package view;

import java.awt.Color;

import model.PlayingPiece;
import model.Position;

public class ShowBobby extends PlayingPiece {

    private Position p = new Position();
    private int position = 0;

    public ShowBobby(Color color) {
        super(color);
    }

    @Override
    public void movePiece(int position) {
        this.position = position;
        setLocation(p.getPoint(position));
        setVisible(true);
    }

    public int getPos() {
        return this.position;
    }
}
