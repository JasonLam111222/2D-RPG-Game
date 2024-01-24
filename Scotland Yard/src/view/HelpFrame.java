//package
package view;

//imports
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

/*Description: this class when they click the button it
 * should run this class. 
 * 
 *  This class is the button - set dimensions, etc
 */

public class HelpFrame extends JFrame implements ActionListener {

	// create visuals - instructions
	private JLabel instructions = new JLabel(" ");

	public HelpFrame() {

		// size
		setSize(700, 900);
		setVisible(false);
		setAlwaysOnTop(true);

		// instructions - image
		try {
			BufferedImage information = ImageIO.read(new File("src/image/Rules.png"));
			instructions.setIcon(new ImageIcon(information));
			instructions.setBounds(100, 50, 200, 50);
			setVisible(true);
			add(instructions);

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	public void actionPerformed(ActionEvent e) {

	}
}