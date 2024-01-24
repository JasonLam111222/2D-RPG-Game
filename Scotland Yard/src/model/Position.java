package model;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Position {

    private final int BOARDX = 339;
    private final int BOARDY = 126;

    private Point[] positions = new Point[200];

    public Position() {
        try {
            Scanner scanner = new Scanner(new File("src/text/positions.txt"));

            while (scanner.hasNextLine()) {
                String[] currentLine = scanner.nextLine().split(" ");

                // BOARD is to offset where the board would be
                // -7 is because our detectives are 15 pixels wide,
                // and we need to place it in the middle of the position.
                int x = Integer.parseInt(currentLine[1]) + BOARDX - 7;
                int y = Integer.parseInt(currentLine[2]) + BOARDY - 7;

                int pos = Integer.parseInt(currentLine[0]);

                positions[pos] = new Point(x, y);
            }
        } catch (FileNotFoundException f) {
            System.out.println(f);
        }

    }

    public Point getPoint(int position) {
        return positions[position];
    }
}
 