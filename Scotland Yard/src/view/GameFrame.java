package view;

import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import model.*;

/*
 * BIG BRAIN IDEA FOR MOVE:
 * Make a small button for every place that is shown and label it with the station number.
 * Once its clicked, we MAKE a new actionListener for that button alone and send it to a method or something with that number
 * Once it approves that its valid, set the visibility of the entire panel that we are making this on to false.
 */
public class GameFrame extends JFrame implements ActionListener {

    // create our visual objects
    private JButton helpButton = new JButton("i");
    private Detectives detectives = new Detectives();
    private Bobbies bobbies = new Bobbies();
    private MisterX mx = new MisterX();
    private JLabel title = new JLabel("Scotland Yard", SwingConstants.CENTER);
    private HelpFrame helpFrame = new HelpFrame();
    private TravelLog travelLog = new TravelLog();
    private JLabel map = new JLabel(" ");
    private ButtonPositions buttonPositions = new ButtonPositions();
    private TransportationMatrix matrix = new TransportationMatrix();
    private boolean alreadyMoving = false;
    private int movesMade = 0;
    private int turn = 1;
    private boolean ai;

    private int xDoubleMove = 0;
    JFrame changeFrame = new JFrame("Double Move Chooser");

    public GameFrame(boolean ai) {
        // set our ai to the value
        this.ai = ai;
        // set the layout to null
        setLayout(null);
        // set the size to 1366x768 (a bit below 1080p)
        setSize(1366, 768);
        // make it so the user cannot resize the window
        setResizable(false);
        // make the code stop once the window has been closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // HELP BUTTON
        helpButton.setBounds(1291, 5, 50, 50);
        helpButton.addActionListener(this);
        add(helpButton);
        // DETECTIVES
        add(detectives.getD1());
        add(detectives.getD2());
        add(detectives.ticketBoard1);
        add(detectives.ticketBoard2);

        // BOBBIES
        add(bobbies.getBobby1());
        add(bobbies.getBobby2());
        add(bobbies.ticketBoard1);
        add(bobbies.ticketBoard2);

        bobbies.moveBobby(1, 1);
        bobbies.moveBobby(2, 52);

        // MISTER X
        add(mx.board);
        mx.board.setVisible(false);
        add(mx.getX());
        mx.moveX(194, ' ');

        detectives.moveDetective(1, 169, ' ');
        detectives.moveDetective(2, 42, ' ');

        // TRAVEL LOG
        add(travelLog);

        // TITLE
        title.setBounds(0, 0, 1366, 60);
        title.setOpaque(true);
        title.setBackground(Color.GRAY);
        title.setFont(new Font("Century Gothic", Font.BOLD, 50));
        add(title);

        // BUTTON POSITIONS
        for (JButton currentButton : buttonPositions.buttons) {
            if (currentButton != null) {
                add(currentButton);
            }
        }
        buttonPositions.showButtons(false);

        // MAP
        try {
            BufferedImage board = ImageIO.read(new File("src/image/map.png"));
            map.setIcon(new ImageIcon(board));
            map.setBounds(339, 126, 688, 516);
            setVisible(true);
            add(map);
        } catch (Exception e) {
            System.out.println(e);
        }

        // ADD BUTTONS TO LISTENER
        // first detective
        detectives.ticketBoard1.getTaxi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDetective(1, 'T');
            }
        });
        detectives.ticketBoard1.getBus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDetective(1, 'B');
            }
        });
        detectives.ticketBoard1.getSubway().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDetective(1, 'U');
            }
        });

        // second detective
        detectives.ticketBoard2.getTaxi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDetective(2, 'T');
            }
        });
        detectives.ticketBoard2.getBus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDetective(2, 'B');
            }
        });
        detectives.ticketBoard2.getSubway().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDetective(2, 'U');
            }
        });

        // mister x
        mx.board.getTaxi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveX('T');
            }
        });

        mx.board.getBus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveX('B');
            }
        });

        mx.board.getSubway().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveX('U');
            }
        });

        mx.board.getBlackticket().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveX('H');
            }
        });

        mx.board.getDoublemove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveX('D');
            }
        });

        // bobby 1
        bobbies.ticketBoard1.getTaxi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBobby(1, 'T');
            }
        });
        bobbies.ticketBoard1.getBus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBobby(1, 'B');
            }
        });
        bobbies.ticketBoard1.getSubway().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBobby(1, 'U');
            }
        });

        // second bobby
        bobbies.ticketBoard2.getTaxi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBobby(2, 'T');
            }
        });
        bobbies.ticketBoard2.getBus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBobby(2, 'B');
            }
        });
        bobbies.ticketBoard2.getSubway().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBobby(2, 'U');
            }
        });

        setVisible(true);
    }

    public void moveX(char typeOfTransportation) {

        removeActionListeners();

        switch (typeOfTransportation) {
            case 'T':
                if (mx.getTaxiTickets() <= 0) {
                    JOptionPane.showMessageDialog(this, "You don't have any taxi tickets!", "Error", 2);
                    return;
                }
                break;
            case 'B':
                if (mx.getBusTickets() <= 0) {
                    JOptionPane.showMessageDialog(this, "You don't have any bus tickets!", "Error", 2);
                    return;
                }
                break;
            case 'U':
                if (mx.getUndergroundTickets() <= 0) {
                    JOptionPane.showMessageDialog(this, "You don't have any underground tickets!", "Error", 2);
                    return;
                }
                break;
            case 'H':
                if (mx.getBlackTickets() <= 0) {
                    JOptionPane.showMessageDialog(this, "You don't have any black tickets!", "Error", 2);
                    return;
                }
                break;
            case 'D':
                if (mx.getDoubleMoveTickets() <= 0) {
                    JOptionPane.showMessageDialog(this, "You don't have any double move tickets!", "Error", 2);
                    return;
                }
                showDoubleMoveUI();
                return;
        }

        if (!checkTransport(typeOfTransportation, mx.getX().getPos())) {
            JOptionPane.showMessageDialog(this, "You can't use that here!", "Error", 2);
            alreadyMoving = false;
            return;
        }


        buttonPositions.showButtons(true);
        for (JButton currentButton : buttonPositions.buttons) {
            if (currentButton == null) {
                continue;
            }
            currentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int clickedPosition = Integer.parseInt(e.getActionCommand());
                    if (!matrix.isValid(mx.getX().getPos(), clickedPosition, typeOfTransportation)) {
                        JOptionPane.showMessageDialog(mx.board, "You can't move here!", "Error", 2);
                        return;
                    }
                    mx.moveX(clickedPosition, typeOfTransportation);
                    alreadyMoving = false;
                    buttonPositions.showButtons(false);

                    xDoubleMove++;
                    if (changeFrame.isVisible()) {
                        
                        if (xDoubleMove != 2) {
                            return;
                        }
                        
                    }

                    travelLog.addMethodOfTransport(turn, String.valueOf(typeOfTransportation));
                    xDoubleMove = 0;
                    changeFrame.setVisible(false);
                    mx.board.setVisible(false);
                    // TODO get travel log working for double moves
                    

                    turn++;
                    detectives.ticketBoard1.showButtons(true);
                    detectives.ticketBoard2.showButtons(true);
                    bobbies.ticketBoard1.showButtons(true);
                    bobbies.ticketBoard2.showButtons(true);
                    mx.getX().setVisible(false);
                }

            });
        }
    }

    public void showDoubleMoveUI() {
        changeFrame.setLayout(null);
        changeFrame.setSize(500, 500);
        int pixelSize = 500 / 6;

        JButton[] buttons = new JButton[4];

        buttons[0] = new JButton("taxi");
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveX('T');
            }
        });
        buttons[1] = new JButton("bus");
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveX('B');
            }
        });
        buttons[2] = new JButton("underground");
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveX('U');
            }
        });
        buttons[3] = new JButton("hidden");
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveX('H');
            }
        });

        for (int i = 0; i < 4; i++) {
            buttons[i].setBounds((i + 1) * pixelSize, 300, pixelSize, 100);
            changeFrame.add(buttons[i]);
        }

        changeFrame.setVisible(true);
    }

    public void moveDetective(int detective, char typeOfTransportation) {
        if (alreadyMoving) {
            JOptionPane.showMessageDialog(this, "You are already moving something!", "Error", 2);
            return;
        }
        removeActionListeners();
        alreadyMoving = true;
        ShowDetective currentShowDetective;
        TicketBoardDetective currentTicketBoard;
        if (detective == 1) {
            currentTicketBoard = detectives.ticketBoard1;
            currentShowDetective = detectives.getD1();
        } else {
            currentTicketBoard = detectives.ticketBoard2;
            currentShowDetective = detectives.getD2();
        }

        switch (typeOfTransportation) {
            case 'T':
                if (detectives.getTaxiTickets() <= 0) {
                    JOptionPane.showMessageDialog(this, "You don't have any taxi tickets!", "Error", 2);
                    alreadyMoving = false;
                    return;
                }
                break;
            case 'B':
                if (detectives.getBusTickets() <= 0) {
                    JOptionPane.showMessageDialog(this, "You don't have any bus tickets!", "Error", 2);
                    alreadyMoving = false;
                    return;
                }

                break;
            case 'U':
                if (detectives.getUndergroundTickets() <= 0) {
                    JOptionPane.showMessageDialog(this, "You don't have any underground tickets!", "Error", 2);
                    alreadyMoving = false;
                    return;
                }
                break;
        }
        if (!checkTransport(typeOfTransportation, currentShowDetective.getPos())) {
            JOptionPane.showMessageDialog(this, "You can't use that here!", "Error", 2);
            alreadyMoving = false;
            return;
        }
        buttonPositions.showButtons(true);
        for (JButton currentButton : buttonPositions.buttons) {
            if (currentButton == null) {
                continue;
            }
            currentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int clickedPosition = Integer.parseInt(e.getActionCommand());
                    if (!matrix.isValid(currentShowDetective.getPos(), clickedPosition, typeOfTransportation)) {
                        JOptionPane.showMessageDialog(currentTicketBoard, "You can't move here!", "Error", 2);
                        return;
                    }
                    detectives.moveDetective(detective, clickedPosition, typeOfTransportation);
                    alreadyMoving = false;
                    buttonPositions.showButtons(false);
                    checkChangeTurn();
                }

            });
        }

        currentTicketBoard.showButtons(false);
        mx.increaseTransportation(typeOfTransportation);

    }

    // MR X TURN CHANGE
    public void checkChangeTurn() {
        movesMade++;
        if (movesMade == 4) {
            movesMade = 0;
            if (ai) {
                mrXAi();	
                return;
            }
            mx.board.setVisible(true);
            mx.getX().setVisible(true);
        }
    }

    public void mrXAi() {
        // TODO your code goes here
        mx.moveX(24, 'T');
        matrix.isValid(24, 26, 'B');
        
 
        
        
    }

    public void removeActionListeners() {
        for (JButton currentButton : buttonPositions.buttons) {
            if (currentButton == null) {
                continue;
            }
            for (ActionListener a : currentButton.getActionListeners()) {
                currentButton.removeActionListener(a);
            }
        }
    }

    public boolean checkTransport(char currentTransport, int pos) {
        char bestTransport = matrix.bestTransport(pos);

        if (currentTransport == 'H') {
            switch (pos) {
                case 108:
                    return true;
                case 115:
                    return true;
                case 157:
                    return true;
                case 194:
                    return true;
            }
            return checkTransport('B', pos) || checkTransport('U', pos) || checkTransport('T', pos);
        }
        if (currentTransport == 'B' && bestTransport == 'T') {
            return false;
        }
        if (currentTransport == 'U' && (bestTransport == 'T' || bestTransport == 'B')) {
            return false;
        }
        if (currentTransport == 'S' && bestTransport != 'S') {
            return false;
        }
        return true;
    }

    public void moveBobby(int bobby, char typeOfTransportation) {
        if (alreadyMoving) {
            JOptionPane.showMessageDialog(this, "You are already moving something!", "Error", 2);
            return;
        }
        removeActionListeners();
        alreadyMoving = true;
        TicketBoardBobbie currentTicketBoard;
        ShowBobby currentShowBobby;
        if (bobby == 1) {
            currentTicketBoard = bobbies.ticketBoard1;
            currentShowBobby = bobbies.getBobby1();
        } else {
            currentTicketBoard = bobbies.ticketBoard2;
            currentShowBobby = bobbies.getBobby2();
        }

        if (!checkTransport(typeOfTransportation, currentShowBobby.getPos())) {
            JOptionPane.showMessageDialog(this, "You can't use that here!", "Error", 2);
            alreadyMoving = false;
            return;
        }
        // show buttons
        buttonPositions.showButtons(true);
        for (JButton currentButton : buttonPositions.buttons) {
            if (currentButton == null) {
                continue;
            }
            // add our listener
            currentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int clickedPosition = Integer.parseInt(e.getActionCommand());
                    if (!matrix.isValid(currentShowBobby.getPos(), clickedPosition, typeOfTransportation)) {
                        JOptionPane.showMessageDialog(currentTicketBoard, "You can't move here!", "Error", 2);
                        return;
                    }
                    bobbies.moveBobby(bobby, clickedPosition);
                    alreadyMoving = false;
                    buttonPositions.showButtons(false);
                    checkChangeTurn();
                }

            });
        }
        currentTicketBoard.showButtons(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "i") {
            helpFrame.setVisible(true);
            return;
        }
    }

}
