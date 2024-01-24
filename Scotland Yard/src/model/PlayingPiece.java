package model;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public abstract class PlayingPiece extends JPanel {

    public PlayingPiece(Color color) {
        setBackground(color);
        setBounds(10, 10, 15, 15);
        setVisible(false);

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                setVisible(false);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                setVisible(true);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
            }
        });
    }

    public abstract void movePiece(int position);

}
