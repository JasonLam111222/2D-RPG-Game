package model;

import java.awt.event.*;

import view.GameFrame;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;

public class TicketBoardMrX extends JPanel {

	// create visual objects
	private JButton taxi = new JButton("Use");
	private JButton bus = new JButton("Use");
	private JButton subway = new JButton("Use");
	private JButton doublemove = new JButton("Use");
	private JButton blackticket = new JButton("Use");

	private JLabel taxiCards = new JLabel("0x");
	private JLabel busCards = new JLabel("0x");
	private JLabel undergroundCards = new JLabel("0x");
	private JLabel doubleCards = new JLabel("2x");
	private JLabel blackCards = new JLabel("5x");

	// create panel for Mr. X ticket board
	public TicketBoardMrX() {
		setLayout(null); // set layout to null
		setBounds(1366 - 250, 100, 200, 200);
		setBackground(new ColorUIResource(70, 70, 70));

		// Taxi Card Label
		taxiCards.setForeground(Color.WHITE);
		taxiCards.setLocation(100, 25);
		taxiCards.setSize(100, 25);
		add(taxiCards);

		// Bus Card Label
		busCards.setForeground(Color.WHITE);
		busCards.setLocation(100, 60);
		busCards.setSize(100, 25);
		add(busCards);

		// Underground Card Label
		undergroundCards.setForeground(Color.WHITE);
		undergroundCards.setLocation(100, 95);
		undergroundCards.setSize(100, 25);
		add(undergroundCards);

		// Black Card Label
		blackCards.setForeground(Color.WHITE);
		blackCards.setLocation(100, 130);
		blackCards.setSize(100, 25);
		add(blackCards);

		// Double Card Label
		doubleCards.setForeground(Color.WHITE);
		doubleCards.setLocation(100, 165);
		doubleCards.setSize(100, 25);
		add(doubleCards);

		// Taxi Label
		JLabel taxiLabel = new JLabel("taxi");
		taxiLabel.setIcon(new ImageIcon("src/images/Scotland Yard - Taxi Ticket.png"));
		taxiLabel.setBounds(5, 25, 90, 25);
		add(taxiLabel);

		// Bus Label
		JLabel busLabel = new JLabel("bus");
		busLabel.setIcon(new ImageIcon("src/images/Scotland Yard - Bus Ticket.png"));
		busLabel.setBounds(5, 60, 90, 25);
		add(busLabel);

		// Subway Label
		JLabel subwayLabel = new JLabel("underground");
		subwayLabel.setIcon(new ImageIcon("src/images/Scotland Yard - Underground Ticket.png"));
		subwayLabel.setBounds(5, 95, 90, 25);
		add(subwayLabel);

		// Black Ticket Label
		JLabel blackticketLabel = new JLabel("black");
		blackticketLabel.setIcon(new ImageIcon("src/images/Scotland Yard - Black Ticket.png"));
		blackticketLabel.setBounds(5, 130, 90, 25);
		add(blackticketLabel);

		// Double Move Label
		JLabel doublemoveLabel = new JLabel("double");
		doublemoveLabel.setIcon(new ImageIcon("src/images/Scotland Yard - Double Move.png"));
		doublemoveLabel.setBounds(5, 165, 90, 25);
		add(doublemoveLabel);

		// Ticket board label: Mr. X
		JLabel mrXTitle = new JLabel("Mr. X");
		mrXTitle.setBounds(5, 3, 100, 20);
		mrXTitle.setFont(new Font("Century Gothic", Font.BOLD, 15));
		mrXTitle.setForeground(Color.WHITE);
		add(mrXTitle);

		// Taxi Use Button
		taxi.setFont(taxi.getFont().deriveFont(9f));
		taxi.setBounds(130, 30, 52, 10);
		add(taxi);

		// Bus Use Button
		bus.setFont(bus.getFont().deriveFont(9f));
		bus.setBounds(130, 65, 52, 10);
		add(bus);

		// Subway Use Button
		subway.setFont(subway.getFont().deriveFont(9f));
		subway.setBounds(130, 100, 52, 10);
		add(subway);

		// Double Move Use Button
		doublemove.setFont(doublemove.getFont().deriveFont(9f));
		doublemove.setBounds(130, 170, 52, 10);
		add(doublemove);

		// Black Ticket Use Button
		blackticket.setFont(blackticket.getFont().deriveFont(9f));
		blackticket.setBounds(130, 135, 52, 10);
		add(blackticket);

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

	public JButton getDoublemove() {
		return this.doublemove;
	}

	public JButton getBlackticket() {
		return this.blackticket;
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
			case 'H':
				blackCards.setText(num + "x");
				break;
			case 'D':
				doubleCards.setText(num + "x");
				break;
		}
	}

	public void showButtons(Boolean flag) {
		getTaxi().setVisible(flag);
		getBus().setVisible(flag);
		getSubway().setVisible(flag);
		getBlackticket().setVisible(flag);
		getDoublemove().setVisible(flag);
	}

}
