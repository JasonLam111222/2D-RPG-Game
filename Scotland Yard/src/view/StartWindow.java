//package
package view;

//imports	
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.SYGameController;

public class StartWindow extends JFrame implements ActionListener {

	// visuals
	private JButton playerButton = new JButton("Player vs Player");
	private JButton AIButton = new JButton("Player vs AI");
	private JLabel title = new JLabel("Scotland Yard", SwingConstants.CENTER);

	public StartWindow() {

		// title
		title.setBounds(0, 0, 600, 150);
		title.setOpaque(true);
		title.setBackground(Color.GRAY);
		title.setFont(new Font("Century Gothic", Font.BOLD, 50));
		add(title);

		// Main frame
		setLayout(null);
		setSize(600, 700);
		setResizable(false);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		// player play button
		playerButton.setBounds(200, 300, 200, 50);
		playerButton.addActionListener(this);
		add(playerButton);

		// AI play button
		AIButton.setBounds(200, 450, 200, 50);
		AIButton.addActionListener(this);
		add(AIButton);

	}

	// actions
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Player vs Player") {
			new SYGameController(false);
		} else {
			new SYGameController(true);
		}
		setVisible(false);
	}

}
