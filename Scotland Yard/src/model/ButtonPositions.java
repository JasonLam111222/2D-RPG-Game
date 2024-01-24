package model;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

public class ButtonPositions {

    private Position p = new Position();
    public JButton[] buttons = new JButton[200];

    public ButtonPositions() {
        for (int i = 1; i < 200; i++) {
            buttons[i] = new JButton();
            buttons[i].setLocation(p.getPoint(i));
            buttons[i].setSize(30, 10);
            buttons[i].setFont(new Font("Comic Sans MS", Font.BOLD, 10));
            buttons[i].setMargin(new Insets(0, 0, 0, 0));
            buttons[i].setText((i) + "");
        }
    }

    public void showButtons(boolean flag) {
        for (int i = 1; i < 200; i++) {
            buttons[i].setVisible(flag);
        }
    }
}
