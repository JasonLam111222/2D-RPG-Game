package model;

//imports
import java.awt.event.*;
import view.GameFrame;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class TicketBoardBobbie extends JPanel {

	// create visual objects
	private JButton taxi = new JButton("Use");
	private JButton bus = new JButton("Use");
	private JButton subway = new JButton("Use");

	// Create JPanel for the ticket board
	public TicketBoardBobbie(int y) {
		setLayout(null); // set layout to null
		setBounds(40, y, 200, 120);
		setBackground(new ColorUIResource(100, 100, 100));

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

		// Ticket board label: Bobbie
		JLabel bobbieTitle = new JLabel("Bobbie");
		bobbieTitle.setBounds(5, 3, 100, 20);
		bobbieTitle.setFont(new Font("Century Gothic", Font.BOLD, 15));
		bobbieTitle.setForeground(Color.WHITE);
		add(bobbieTitle);

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

	public void showButtons(Boolean flag) {
		getTaxi().setVisible(flag);
        getBus().setVisible(flag);
		getSubway().setVisible(flag);
	}

}