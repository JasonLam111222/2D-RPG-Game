package view;

import java.awt.Color;

import model.PlayingPiece;
import model.Position;

public class ShowMisterX extends PlayingPiece {

    private Position p = new Position();
    private int position = 0;

    public ShowMisterX(Color color) {
        super(color);
        setVisible(false);

    }

    @Override
    public void movePiece(int position) {
        this.position = position;
        setLocation(p.getPoint(position));
    }

    public int getPos() {
        return this.position;
    }
}
