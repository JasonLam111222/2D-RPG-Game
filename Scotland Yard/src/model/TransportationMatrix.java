package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TransportationMatrix {
    boolean[][] validTransportation = new boolean[200][200];
    char[][] transportationMethod = new char[200][200];

    public TransportationMatrix() {
        try {
            Scanner fileScanner = new Scanner(new File("src/text/routes.txt"));
            while (fileScanner.hasNextLine()) {
                String[] args = fileScanner.nextLine().split(" ");
                int from = Integer.parseInt(args[0]);
                int to = Integer.parseInt(args[1]);
                validTransportation[from][to] = true;
                validTransportation[to][from] = true;

                transportationMethod[from][to] = args[2].charAt(0);
                transportationMethod[to][from] = args[2].charAt(0);

            }
        } catch (FileNotFoundException f) {
            System.out.println(f);
        }

    }

    public boolean isValid(int from, int to, char tm) {
        if (from == to) {
            return false;
        }
        if (tm == 'H') {
            switch (to) {
                case 108:
                    return true;
                case 115:
                    return true;
                case 157:
                    return true;
                case 194:
                    return true;
            }
            return isValid(from, to, 'T') || isValid(from, to, 'B') || isValid(from, to, 'U');
        }
        if (validTransportation[from][to] && transportationMethod[from][to] == tm) {
            return true;
        }
        return false;
    }

    public char typeOfTransport(int from, int to) {
        return transportationMethod[from][to];
    }

    public char bestTransport(int pos) {
        char bestChar = 'T';
        for (char currentChar : transportationMethod[pos]) {
            if (currentChar == 'U') {
                bestChar = 'U';
            }
            if (currentChar == 'B' && bestChar != 'U') {
                bestChar = 'B';
            }
        }
        return bestChar;
    }
}
