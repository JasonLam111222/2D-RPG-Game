package model;

//imports

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

import view.GameFrame;

import javax.swing.*;
import java.awt.*;

public class TicketBoardDetective extends JPanel {

	// create visual objects
	private JButton taxi = new JButton("Use");
	private JButton bus = new JButton("Use");
	private JButton subway = new JButton("Use");

	private JLabel taxiCards = new JLabel("20x");
	private JLabel busCards = new JLabel("16x");
	private JLabel undergroundCards = new JLabel("8x");

	// Create JPanel for the ticket board
	public TicketBoardDetective(int y, Color c) {
		setLayout(null); // set layout to null
		setBounds(40, y, 200, 120);
		setBackground(new ColorUIResource(70, 70, 70));

		// taxi use button
		taxi.setFont(taxi.getFont().deriveFont(9f));
		taxi.setBounds(140, 30, 52, 10);
		add(taxi);

		// bus use button
		bus.setFont(bus.getFont().deriveFont(9f));
		bus.setBounds(140, 60, 52, 10);
		add(bus);

		// subway use button
		subway.setFont(subway.getFont().deriveFont(9f));
		subway.setBounds(140, 90, 52, 10);
		add(subway);

		// taxi remaining cards label
		taxiCards.setForeground(Color.WHITE);
		taxiCards.setLocation(100, 25);
		taxiCards.setSize(100, 25);
		add(taxiCards);

		busCards.setForeground(Color.WHITE);
		busCards.setLocation(100, 55);
		busCards.setSize(100, 25);
		add(busCards);

		undergroundCards.setForeground(Color.WHITE);
		undergroundCards.setLocation(100, 85);
		undergroundCards.setSize(100, 25);
		add(undergroundCards);

		// Taxi Label 1
		JLabel taxiLabel1 = new JLabel("taxi");
		taxiLabel1.setIcon(new ImageIcon("src/images/Scotland Yard - Taxi Ticket.png"));
		taxiLabel1.setBounds(5, 25, 90, 25);
		add(taxiLabel1);

		// Bus Label 1
		JLabel busLabel1 = new JLabel("bus");
		busLabel1.setIcon(new ImageIcon("src/images/Scotland Yard - Bus Ticket.png"));
		busLabel1.setBounds(5, 55, 90, 25);
		add(busLabel1);

		// Subway Label 1
		JLabel subwayLabel1 = new JLabel("subway");
		subwayLabel1.setIcon(new ImageIcon("src/images/Scotland Yard - Underground Ticket.png"));
		subwayLabel1.setBounds(5, 85, 90, 25);
		add(subwayLabel1);

		// Ticket board label: Detective
		JLabel detectiveTitle = new JLabel("Detective");
		detectiveTitle.setBounds(5, 3, 100, 20);
		detectiveTitle.setFont(new Font("Century Gothic", Font.BOLD, 15));
		detectiveTitle.setForeground(c);
		add(detectiveTitle);
	}

	public JButton getTaxi() {
		return this.taxi;
	}

	public JButton getBus() {
		return this.bus;
	}

	public JButton getSubway() {
		return this.subway;
	}

	public void setRemainingCardsLabel(int num, char transportationUsed) {
		switch (transportationUsed) {
			case 'T':
				taxiCards.setText(num + "x");
				break;
			case 'B':
				busCards.setText(num + "x");
				break;
			case 'U':
				undergroundCards.setText(num + "x");
				break;
		}
	}

	public void showButtons(Boolean flag) {
		getTaxi().setVisible(flag);
		getBus().setVisible(flag);
		getSubway().setVisible(flag);
	}

}
