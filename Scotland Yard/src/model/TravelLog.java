package model;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.ColorUIResource;

public class TravelLog extends JPanel {
    // TODO make it so you can see the postition if it's misterX's turn and if you
    // click on it
    // create our way to know the methods of transports
    // and the labels that will hold them
    private String[] methodsOfTransport = new String[25];
    private JLabel[] numbers = new JLabel[25];
    private JLabel[] transports = new JLabel[25];

    public TravelLog() {
        setLayout(null);
        // setting the box to be 200x100
        setBounds(1366 - 250, 708 - 300, 200, 280);
        // setting the background to a custom rgb value (light blue)
        setBackground(new ColorUIResource(150, 150, 255));

        // creating our title with a fancy font
        JLabel title = new JLabel("Travel Log", SwingConstants.CENTER);
        title.setBounds(0, 0, 200, 40);
        title.setFont(new Font("Century Gothic", Font.PLAIN, 30));

        // add the title
        add(title);

        // create our labels and update the text
        createLabels();
        updateText();
    }

    public void addMethodOfTransport(int round, String methodOfTransport) {
        // add the method of transport to the correct index (based on round number)
        methodsOfTransport[round] = methodOfTransport;

        // update the text so it will display
        System.out.println(methodOfTransport);
        updateText();
    }

    private void createLabels() {
        // for each label in the first column
        for (int i = 1; i <= 12; i++) {
            int y = 20 + (i * 13);

            numbers[i] = new JLabel(i + ".)");
            numbers[i].setBounds(10, y, 90, y + 10);
            add(numbers[i]);

            transports[i] = new JLabel();
            add(transports[i]);
        }
        // for each label in the second column
        for (int i = 13; i <= 24; i++) {
            int y = 20 + ((i - 12) * 13);

            numbers[i] = new JLabel(i + ".)");
            numbers[i].setBounds(110, y, 90, y + 10);
            add(numbers[i]);

            transports[i] = new JLabel();
            add(transports[i]);
        }
    }

    private void updateText() {
        // for each label in the first column
        for (int i = 1; i <= 12; i++) {
            int y = 20 + (i * 13);
            transports[i].setText(methodsOfTransport[i] + "");
            transports[i].setBounds(60, y, 90, y + 10);
            transports[i].repaint();
        }
        // for each label in the second column
        for (int i = 13; i <= 24; i++) {
            int y = 20 + ((i - 12) * 13);

            transports[i].setText(methodsOfTransport[i] + "");
            transports[i].setBounds(160, y, 90, y + 10);
            transports[i].repaint();
        }
    }
}